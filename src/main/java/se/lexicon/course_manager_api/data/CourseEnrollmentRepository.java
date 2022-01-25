package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.course_manager_api.model.entity.CourseEnrollment;

import java.time.LocalDate;
import java.util.List;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, String> {

    @Query("SELECT e FROM CourseEnrollment e INNER JOIN FETCH e.courseGroup g INNER JOIN FETCH g.course GROUP BY e")
    List<CourseEnrollment> findAllCourseEnrollments();

    @Query("SELECT e FROM CourseEnrollment e INNER JOIN FETCH  e.courseGroup g INNER JOIN FETCH g.course c WHERE UPPER(c.courseName) = :courseName")
    List<CourseEnrollment> findByCourseName(@Param("courseName") String courseName);

    @Query("SELECT e FROM CourseEnrollment e INNER JOIN FETCH e.courseGroup g INNER JOIN FETCH g.course WHERE e.regDate = :regDate")
    List<CourseEnrollment> findByRegDate(@Param("regDate") LocalDate regDate);

    @Query("SELECT e FROM CourseEnrollment e INNER JOIN FETCH e.courseGroup g INNER JOIN FETCH g.course WHERE e.courseGroup.id = :id")
    List<CourseEnrollment> findByCourseGroupId(@Param("id") String courseGroupId);

    @Query("SELECT e FROM CourseEnrollment e INNER JOIN FETCH e.courseGroup g INNER JOIN FETCH g.course WHERE e.student.id = :id")
    List<CourseEnrollment> findByStudentId(@Param("id") String studentId);
}