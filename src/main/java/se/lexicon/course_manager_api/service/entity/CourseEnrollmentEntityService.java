package se.lexicon.course_manager_api.service.entity;

import se.lexicon.course_manager_api.model.dto.CourseEnrollmentDto;
import se.lexicon.course_manager_api.model.entity.CourseEnrollment;

import java.time.LocalDate;
import java.util.List;

public interface CourseEnrollmentEntityService {
    CourseEnrollment create();
    CourseEnrollment findById(String id);
    List<CourseEnrollment> findAll();
    List<CourseEnrollment> findByCourseName(String courseName);
    List<CourseEnrollment> findByRegDate(LocalDate regDate);
    List<CourseEnrollment> findByCourseGroupId(String courseGroupId);
    List<CourseEnrollment> findByStudentId(String studentId);
    CourseEnrollment update(String id, CourseEnrollmentDto courseEnrollmentDto);
    boolean delete(String id);
}
