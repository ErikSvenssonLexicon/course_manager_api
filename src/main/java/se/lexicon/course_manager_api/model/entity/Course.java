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
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private String id;
    @Column(name = "course_name", unique = true)
    private String courseName;
    @Column(name = "course_topic")
    private String courseTopic;
    @Column(name = "duration_days")
    private Integer courseDuration;
    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "courses_course_assignments",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "course_assignment_id")
    )
    private Set<CourseAssignment> courseAssignments;


    public Set<CourseAssignment> getCourseAssignments() {
        if(courseAssignments == null) courseAssignments = new HashSet<>();
        return courseAssignments;
    }

    public void setCourseAssignments(Set<CourseAssignment> courseAssignments) {
        if(courseAssignments == null) courseAssignments = new HashSet<>();
        if(courseAssignments.isEmpty()){
            if(this.courseAssignments != null){
                for(CourseAssignment assignment : this.courseAssignments){
                    assignment.getCourses().remove(this);
                }
            }
        }else{
            for(CourseAssignment assignment : courseAssignments){
                assignment.getCourses().add(this);
            }
        }
        this.courseAssignments = courseAssignments;
    }

    public void addCourseAssignments(CourseAssignment...courseAssignments){
        if(courseAssignments == null) return;
        if(this.courseAssignments == null) this.courseAssignments = new HashSet<>();
        if(courseAssignments.length > 0){
            for(CourseAssignment courseAssignment : courseAssignments){
                if(courseAssignment != null){
                    this.courseAssignments.add(courseAssignment);
                    courseAssignment.getCourses().add(this);
                }
            }
        }
    }

    public void removeCourseAssignments(CourseAssignment...courseAssignments){
        if(courseAssignments == null) return;
        if(this.courseAssignments == null) this.courseAssignments = new HashSet<>();
        if(courseAssignments.length > 0){
            for(CourseAssignment courseAssignment : courseAssignments){
                if(courseAssignment != null){
                    this.courseAssignments.remove(courseAssignment);
                    courseAssignment.getCourses().remove(this);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(courseName, course.courseName) && Objects.equals(courseTopic, course.courseTopic) && Objects.equals(courseDuration, course.courseDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, courseTopic, courseDuration);
    }
}
