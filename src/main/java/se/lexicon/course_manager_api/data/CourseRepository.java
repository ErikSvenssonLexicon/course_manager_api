package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.course_manager_api.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, String> {
}