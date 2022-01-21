package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.course_manager_api.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
}