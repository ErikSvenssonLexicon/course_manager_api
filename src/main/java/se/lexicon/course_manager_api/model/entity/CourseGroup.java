package se.lexicon.course_manager_api.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course_groups")
public class CourseGroup {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private String id;
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "course_id", table = "course_groups")
    private Course course;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "courseGroup"
    )
    private List<CourseStudent> courseStudents;

    public List<CourseStudent> getCourseStudents() {
        if(courseStudents == null) courseStudents = new ArrayList<>();
        return courseStudents;
    }

    public void setCourseStudents(List<CourseStudent> courseStudents) {
        if(courseStudents == null) courseStudents = new ArrayList<>();
        if(courseStudents.isEmpty()){
            if(this.courseStudents != null){
                for(CourseStudent courseStudent : this.courseStudents){
                    courseStudent.setCourseGroup(null);
                }
            }
        }else {
            for(CourseStudent courseStudent : courseStudents){
                if(courseStudent != null){
                    courseStudent.setCourseGroup(this);
                }
            }
        }
        this.courseStudents = courseStudents;
    }

    public void addCourseStudents(CourseStudent...courseStudents){
        if(courseStudents == null) return;
        if(this.courseStudents == null) this.courseStudents = new ArrayList<>();
        if(courseStudents.length > 0){
            for(CourseStudent courseStudent : courseStudents){
                if(courseStudent != null && !this.courseStudents.contains(courseStudent)){
                    this.courseStudents.add(courseStudent);
                    courseStudent.setCourseGroup(this);
                }
            }
        }
    }

    public void removeCourseStudents(CourseStudent...courseStudents){
        if(courseStudents == null) return;
        if(this.courseStudents == null) this.courseStudents = new ArrayList<>();
        if(courseStudents.length > 0){
            for(CourseStudent courseStudent : courseStudents){
                if(courseStudent != null && this.courseStudents.contains(courseStudent)){
                    this.courseStudents.remove(courseStudent);
                    courseStudent.setCourseGroup(null);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseGroup that = (CourseGroup) o;
        return Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate);
    }
}
