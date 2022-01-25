package se.lexicon.course_manager_api.service.conversion;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.course_manager_api.model.dto.*;
import se.lexicon.course_manager_api.model.entity.*;

public interface EntityToDtoService {
    CourseDto basicDtoFrom(Course course);
    CourseDto fullDtoFrom(Course course);
    CourseAssignmentDto basicDtoFrom(CourseAssignment courseAssignment);
    CourseAssignmentDto fullDtoFrom(CourseAssignment courseAssignment);
    CourseGroupDto basicDtoFrom(CourseGroup courseGroup);
    CourseGroupDto fullDtoFrom(CourseGroup courseGroup);
    CourseEnrollmentDto basicDtoFrom(CourseEnrollment courseEnrollment);
    CourseEnrollmentDto fullDtoFrom(CourseEnrollment courseEnrollment);
    StudentDto basicDtoFrom(Student student);
    StudentDto fullDtoFrom(Student student);
    StudentAssignmentDto basicDtoFrom(StudentAssignment studentAssignment);
    StudentAssignmentDto fullDtoFrom(StudentAssignment studentAssignment);
}
