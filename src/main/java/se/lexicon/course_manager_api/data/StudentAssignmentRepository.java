package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.course_manager_api.model.Rating;
import se.lexicon.course_manager_api.model.entity.StudentAssignment;

import java.util.List;

public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, String> {
    @Query("SELECT a FROM StudentAssignment a WHERE a.courseEnrollment.id = :id")
    List<StudentAssignment> findByCourseEnrollmentId(@Param("id") String enrollmentId);

    @Query("SELECT a FROM StudentAssignment a WHERE a.grade = :rating")
    List<StudentAssignment> findByGrade(@Param("rating") Rating rating);
}