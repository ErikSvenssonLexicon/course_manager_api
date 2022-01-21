package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.course_manager_api.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    @Query("SELECT c FROM Course c WHERE UPPER(c.courseName) = UPPER(:courseName)")
    Optional<Course> findByCourseName(@Param("courseName") String courseName);

    @Query("SELECT c FROM Course c WHERE UPPER(c.courseTopic) LIKE UPPER(CONCAT('%',:courseTopic,'%'))")
    List<Course> findByCourseTopic(@Param("courseTopic") String courseTopic);

}