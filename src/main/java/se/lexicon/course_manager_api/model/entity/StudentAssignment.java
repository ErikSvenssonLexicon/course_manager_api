package se.lexicon.course_manager_api.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import se.lexicon.course_manager_api.model.Rating;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="student_assignments")
public class StudentAssignment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private String id;
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "assignment_id", table = "student_assignments")
    private CourseAssignment assignment;
    @Column(name = "dead_line")
    private LocalDateTime deadLine;
    @Column(name = "grade")
    private Rating grade;
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "course_student_id", table = "student_assignments")
    private CourseStudent courseStudent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAssignment that = (StudentAssignment) o;
        return Objects.equals(id, that.id) && Objects.equals(deadLine, that.deadLine) && grade == that.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deadLine, grade);
    }
}
