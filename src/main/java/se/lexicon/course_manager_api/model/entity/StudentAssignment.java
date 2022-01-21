package se.lexicon.course_manager_api.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import se.lexicon.course_manager_api.model.Rating;
import se.lexicon.course_manager_api.model.entity.CourseAssignment;
import se.lexicon.course_manager_api.model.entity.CourseStudent;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
