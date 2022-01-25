package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.course_manager_api.model.entity.CourseGroup;

import java.time.LocalDate;
import java.util.List;

public interface CourseGroupRepository extends JpaRepository<CourseGroup, String> {

    @Query("SELECT c FROM CourseGroup c INNER JOIN FETCH c.course")
    List<CourseGroup> findAllCourseGroups();

    @Query("SELECT c FROM CourseGroup c INNER JOIN FETCH  c.course WHERE c.startDate > current_date")
    List<CourseGroup> findNotYetStarted();

    @Query("SELECT c FROM CourseGroup c INNER JOIN FETCH c.course WHERE c.startDate > current_date AND c.course.id = :id")
    List<CourseGroup> findNotYetStarted(@Param("id") String courseId);

    @Query("SELECT c FROM CourseGroup c INNER JOIN FETCH c.course WHERE current_date BETWEEN c.startDate AND c.endDate")
    List<CourseGroup> findCurrentCourseGroups();

    @Query("SELECT c FROM CourseGroup c INNER JOIN FETCH c.course WHERE :date BETWEEN c.startDate AND c.endDate")
    List<CourseGroup> findCourseGroupsBy(@Param("date") LocalDate date);

    @Query("SELECT c FROM CourseGroup c INNER JOIN FETCH c.course WHERE :date > c.endDate")
    List<CourseGroup> findByEndDateAfter(@Param("date") LocalDate date);

    @Query("SELECT c FROM CourseGroup c INNER JOIN FETCH c.course WHERE c.course.id = :id")
    List<CourseGroup> findAllByCourseId(@Param("id") String courseId);
}