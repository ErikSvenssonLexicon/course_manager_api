package se.lexicon.course_manager_api.service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.course_manager_api.data.CourseEnrollmentRepository;
import se.lexicon.course_manager_api.exception.AppResourceNotFoundException;
import se.lexicon.course_manager_api.model.dto.CourseEnrollmentDto;
import se.lexicon.course_manager_api.model.entity.CourseEnrollment;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseEnrollmentEntityServiceImpl implements CourseEnrollmentEntityService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;

    @Override
    public CourseEnrollment create() {
        return courseEnrollmentRepository.save(
                new CourseEnrollment()
        );
    }

    @Override
    public CourseEnrollment findById(String id) {
        return courseEnrollmentRepository.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find CourseEnrollment with id " + id));
    }

    @Override
    public List<CourseEnrollment> findAll() {
        return courseEnrollmentRepository.findAllCourseEnrollments();
    }

    @Override
    public List<CourseEnrollment> findByCourseName(String courseName) {
        return courseEnrollmentRepository.findByCourseName(courseName);
    }

    @Override
    public List<CourseEnrollment> findByRegDate(LocalDate regDate) {
        return courseEnrollmentRepository.findByRegDate(regDate);
    }

    @Override
    public List<CourseEnrollment> findByCourseGroupId(String courseGroupId) {
        return courseEnrollmentRepository.findByCourseGroupId(courseGroupId);
    }

    @Override
    public List<CourseEnrollment> findByStudentId(String studentId) {
        return courseEnrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public CourseEnrollment update(String id, CourseEnrollmentDto courseEnrollmentDto) {
        if(id == null || courseEnrollmentDto == null) throw new IllegalArgumentException("Id or dto was null");
        if(!id.equals(courseEnrollmentDto.getId())) throw new IllegalStateException("String id did not match courseEnrollmentDto.id");
        CourseEnrollment courseEnrollment = findById(id);
        courseEnrollment.setRegDate(courseEnrollmentDto.getRegDate());
        return courseEnrollmentRepository.save(courseEnrollment);
    }

    @Override
    public boolean delete(String id) {
        CourseEnrollment courseEnrollment = findById(id);
        courseEnrollment.setStudent(null);
        courseEnrollment.setAssignmentList(null);
        courseEnrollment.setCourseGroup(null);
        courseEnrollmentRepository.delete(courseEnrollment);
        return !courseEnrollmentRepository.existsById(id);
    }
}
