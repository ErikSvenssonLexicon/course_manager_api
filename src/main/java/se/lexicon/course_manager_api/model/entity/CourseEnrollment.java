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
@Table(name = "enrollments")
public class CourseEnrollment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private String id;
    @Column(name = "register_date")
    private LocalDate regDate;
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "group_id", table = "enrollments")
    private CourseGroup courseGroup;
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "student_id", table = "enrollments")
    private Student student;
    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "courseEnrollment",
            orphanRemoval = true
    )
    private List<StudentAssignment> assignmentList;

    public List<StudentAssignment> getAssignmentList() {
        if(assignmentList == null) assignmentList = new ArrayList<>();
        return assignmentList;
    }

    public void setAssignmentList(List<StudentAssignment> assignmentList) {
        if(assignmentList == null) assignmentList = new ArrayList<>();
        if(assignmentList.isEmpty()){
            if(this.assignmentList != null){
                for(StudentAssignment studentAssignment : this.assignmentList){
                    if(studentAssignment != null){
                        studentAssignment.setCourseEnrollment(null);
                    }
                }
            }
        }else{
            for(StudentAssignment studentAssignment : assignmentList){
                if(studentAssignment != null){
                    studentAssignment.setCourseEnrollment(this);
                }
            }
        }
        this.assignmentList = assignmentList;
    }

    public void addStudentAssignments(StudentAssignment...studentAssignments){
        if(studentAssignments == null) return;
        if(this.assignmentList == null) this.assignmentList = new ArrayList<>();
        if(studentAssignments.length > 0){
            for(StudentAssignment studentAssignment : studentAssignments){
                if(studentAssignment != null && !this.assignmentList.contains(studentAssignment)){
                    this.assignmentList.add(studentAssignment);
                    studentAssignment.setCourseEnrollment(this);
                }
            }
        }
    }

    public void removeStudentAssignments(StudentAssignment...studentAssignments){
        if(studentAssignments == null) return;
        if(this.assignmentList == null) this.assignmentList = new ArrayList<>();
        if(studentAssignments.length > 0){
            for(StudentAssignment studentAssignment : studentAssignments){
                if(studentAssignment != null && this.assignmentList.contains(studentAssignment)){
                    this.assignmentList.remove(studentAssignment);
                    studentAssignment.setCourseEnrollment(null);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEnrollment that = (CourseEnrollment) o;
        return Objects.equals(id, that.id) && Objects.equals(regDate, that.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regDate);
    }

    @PrePersist
    void prePersist(){
        if(regDate == null){
            regDate = LocalDate.now();
        }
    }
}
