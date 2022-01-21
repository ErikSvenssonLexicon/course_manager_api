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
    private List<CourseEnrollment> courseEnrollments;

    public List<CourseEnrollment> getCourseEnrollments() {
        if(courseEnrollments == null) courseEnrollments = new ArrayList<>();
        return courseEnrollments;
    }

    public void setCourseEnrollments(List<CourseEnrollment> courseEnrollments) {
        if(courseEnrollments == null) courseEnrollments = new ArrayList<>();
        if(courseEnrollments.isEmpty()){
            if(this.courseEnrollments != null){
                for(CourseEnrollment courseEnrollment : this.courseEnrollments){
                    courseEnrollment.setCourseGroup(null);
                }
            }
        }else {
            for(CourseEnrollment courseEnrollment : courseEnrollments){
                if(courseEnrollment != null){
                    courseEnrollment.setCourseGroup(this);
                }
            }
        }
        this.courseEnrollments = courseEnrollments;
    }

    public void addCourseStudents(CourseEnrollment... courseEnrollments){
        if(courseEnrollments == null) return;
        if(this.courseEnrollments == null) this.courseEnrollments = new ArrayList<>();
        if(courseEnrollments.length > 0){
            for(CourseEnrollment courseEnrollment : courseEnrollments){
                if(courseEnrollment != null && !this.courseEnrollments.contains(courseEnrollment)){
                    this.courseEnrollments.add(courseEnrollment);
                    courseEnrollment.setCourseGroup(this);
                }
            }
        }
    }

    public void removeCourseStudents(CourseEnrollment... courseEnrollments){
        if(courseEnrollments == null) return;
        if(this.courseEnrollments == null) this.courseEnrollments = new ArrayList<>();
        if(courseEnrollments.length > 0){
            for(CourseEnrollment courseEnrollment : courseEnrollments){
                if(courseEnrollment != null && this.courseEnrollments.contains(courseEnrollment)){
                    this.courseEnrollments.remove(courseEnrollment);
                    courseEnrollment.setCourseGroup(null);
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
