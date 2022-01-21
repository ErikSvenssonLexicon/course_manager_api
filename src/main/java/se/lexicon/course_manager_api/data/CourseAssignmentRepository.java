package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.course_manager_api.model.entity.CourseAssignment;

import java.util.List;
import java.util.Optional;

public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment, String> {
    @Query("SELECT a FROM CourseAssignment a WHERE UPPER(a.assignmentName) = UPPER(:assignmentName)")
    Optional<CourseAssignment> findByAssignmentName(@Param("assignmentName") String assignmentName);

    @Query("SELECT a FROM CourseAssignment a WHERE UPPER(a.assignmentName) LIKE UPPER(CONCAT('%',:assignmentName,'%'))")
    List<CourseAssignment> findByAssignmentNameLike(@Param("assignmentName") String assignmentName);
}