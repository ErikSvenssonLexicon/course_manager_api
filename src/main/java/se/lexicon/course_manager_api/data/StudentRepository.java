package se.lexicon.course_manager_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.course_manager_api.model.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s WHERE UPPER(s.email) = UPPER(:email)")
    Optional<Student> findByEmail(@Param("email") String email);

    @Query("SELECT s FROM Student s WHERE UPPER(CONCAT(s.firstName, ' ', s.lastName)) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<Student> findByName(@Param("name") String name);
}