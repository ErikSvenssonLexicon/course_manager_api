package se.lexicon.course_manager_api.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course_assignments")
public class CourseAssignment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private String id;
    @Column(name = "assignment_name", unique = true)
    private String assignmentName;
    @Column(name = "description", length = 1500)
    private String description;
    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "courseAssignments"
    )
    private Set<Course> courses;

    public Set<Course> getCourses() {
        if(courses == null) courses = new HashSet<>();
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        if(courses == null) courses = new HashSet<>();
        if(courses.isEmpty()){
            if(this.courses != null){
                for(Course course : this.courses){
                    course.getCourseAssignments().remove(this);
                }
            }
        }else {
            for(Course course : courses){
                course.getCourseAssignments().add(this);
            }
        }
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseAssignment that = (CourseAssignment) o;
        return Objects.equals(id, that.id) && Objects.equals(assignmentName, that.assignmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignmentName);
    }
}
