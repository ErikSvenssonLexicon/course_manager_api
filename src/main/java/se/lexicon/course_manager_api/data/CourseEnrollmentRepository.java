package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.course_manager_api.model.entity.CourseEnrollment;

import java.time.LocalDate;
import java.util.List;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, String> {
    List<CourseEnrollment> findByRegDate(LocalDate regDate);

    @Query("SELECT c FROM CourseEnrollment c WHERE c.courseGroup.id = :id")
    List<CourseEnrollment> findByCourseGroupId(@Param("id") String courseGroupId);

    @Query("SELECT c FROM CourseEnrollment c WHERE c.student.id = :id")
    List<CourseEnrollment> findByStudentId(@Param("id") String studentId);
}