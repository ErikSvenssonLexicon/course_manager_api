package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.course_manager_api.model.entity.CourseGroup;

public interface CourseGroupRepository extends JpaRepository<CourseGroup, String> {
}