package se.lexicon.course_manager_api.service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.course_manager_api.data.StudentRepository;
import se.lexicon.course_manager_api.exception.AppResourceNotFoundException;
import se.lexicon.course_manager_api.model.dto.StudentDto;
import se.lexicon.course_manager_api.model.entity.Student;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentEntityServiceImpl implements StudentEntityService{

    private final StudentRepository studentRepository;

    @Override
    public Student create(StudentDto studentDto) {
        if(studentDto == null) throw new IllegalArgumentException("StudentDto studentDto was null");
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName().trim());
        student.setLastName(studentDto.getLastName().trim());
        student.setEmail(studentDto.getEmail().trim());
        student.setAddress(studentDto.getAddress().trim());
        student.setPhone(studentDto.getPhone().trim());

        return studentRepository.save(student);
    }

    @Override
    public Student findById(String id) {
        return studentRepository.findStudentById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find student with id " + id));
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student update(String id, StudentDto studentDto) {
        if(id == null || studentDto == null) throw new IllegalArgumentException("String id or StudentDto was null");
        if(!id.equals(studentDto.getId())) throw new IllegalStateException("String id didn't match StudentDto studentDto.id");
        Student student = findById(id);

        Optional<Student> optional = studentRepository.findByEmail(studentDto.getEmail().trim());
        if(optional.isPresent() && ! optional.get().getId().equals(id)){
            throw new IllegalStateException("Email: " + studentDto.getEmail() + " is already taken");
        }

        student.setFirstName(studentDto.getFirstName().trim());
        student.setLastName(studentDto.getLastName().trim());
        student.setAddress(studentDto.getAddress().trim());
        student.setEmail(studentDto.getEmail().trim());
        student.setPhone(studentDto.getPhone().trim());

        return studentRepository.save(student);
    }

    @Override
    public boolean delete(String id) {
        studentRepository.deleteById(id);
        return !studentRepository.existsById(id);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find student with email: " + email));
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }
}
