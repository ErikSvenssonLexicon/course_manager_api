package se.lexicon.course_manager_api.service.entity;

import se.lexicon.course_manager_api.model.dto.StudentDto;
import se.lexicon.course_manager_api.model.entity.Student;
import se.lexicon.course_manager_api.service.GenericCRUD;

import java.util.List;

public interface StudentEntityService extends GenericCRUD<Student, StudentDto, String> {
    Student findByEmail(String email);
    List<Student> findByName(String name);
}
