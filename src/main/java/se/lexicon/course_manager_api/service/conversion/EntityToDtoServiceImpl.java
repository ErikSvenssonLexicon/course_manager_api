package se.lexicon.course_manager_api.service.conversion;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.course_manager_api.model.dto.*;
import se.lexicon.course_manager_api.model.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntityToDtoServiceImpl implements EntityToDtoService{

    @Override
    public CourseDto basicDtoFrom(Course course) {
        CourseDto courseDto = null;
        if(course != null){
            courseDto = new CourseDto();
            courseDto.setId(course.getId());
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCourseTopic(course.getCourseTopic());
            courseDto.setCourseDuration(course.getCourseDuration());
        }
        return courseDto;
    }

    @Override
    public CourseDto fullDtoFrom(Course course) {
        CourseDto courseDto = null;
        if(course != null){
            courseDto = basicDtoFrom(course);
            courseDto.setCourseAssignments(
                    course.getCourseAssignments().stream()
                            .map(this::basicDtoFrom)
                            .collect(Collectors.toList())
            );
        }
        return courseDto;
    }

    @Override
    public CourseAssignmentDto basicDtoFrom(CourseAssignment courseAssignment) {
        CourseAssignmentDto courseAssignmentDto = null;
        if(courseAssignment != null){
            courseAssignmentDto =  new CourseAssignmentDto();
            courseAssignmentDto.setId(courseAssignment.getId());
            courseAssignmentDto.setAssignmentName(courseAssignment.getAssignmentName());
        }
        return courseAssignmentDto;
    }

    @Override
    public CourseAssignmentDto fullDtoFrom(CourseAssignment courseAssignment) {
        CourseAssignmentDto courseAssignmentDto = null;
        if(courseAssignment != null){
            courseAssignmentDto = basicDtoFrom(courseAssignment);
            courseAssignmentDto.setCourses(
                    courseAssignment.getCourses().stream()
                            .map(this::basicDtoFrom)
                            .collect(Collectors.toList())
            );
            courseAssignmentDto.setDescription(courseAssignment.getDescription());
        }
        return courseAssignmentDto;
    }

    @Override
    public CourseGroupDto basicDtoFrom(CourseGroup courseGroup) {
        CourseGroupDto courseGroupDto = null;
        if(courseGroup != null){
            courseGroupDto = new CourseGroupDto();
            courseGroupDto.setId(courseGroup.getId());
            courseGroupDto.setStartDate(courseGroup.getStartDate());
            courseGroupDto.setEndDate(courseGroup.getEndDate());
            courseGroupDto.setCourse(basicDtoFrom(courseGroup.getCourse()));
        }
        return courseGroupDto;
    }

    @Override
    public CourseGroupDto fullDtoFrom(CourseGroup courseGroup) {
        CourseGroupDto courseGroupDto = null;
        if(courseGroup != null){
            courseGroupDto = basicDtoFrom(courseGroup);
            List<CourseEnrollmentDto> courseEnrollmentDtos = new ArrayList<>();
            for(CourseEnrollment courseEnrollment : courseGroup.getCourseEnrollments()){
                courseEnrollmentDtos.add(basicDtoFrom(courseEnrollment));
            }
            courseGroupDto.setCourseStudents(courseEnrollmentDtos);
        }
        return courseGroupDto;
    }

    @Override
    public CourseEnrollmentDto basicDtoFrom(CourseEnrollment courseEnrollment) {
        CourseEnrollmentDto courseEnrollmentDto = null;
        if(courseEnrollment != null){
            courseEnrollmentDto = new CourseEnrollmentDto();
            courseEnrollmentDto.setId(courseEnrollment.getId());
            courseEnrollmentDto.setRegDate(courseEnrollment.getRegDate());
            courseEnrollmentDto.setCourseGroup(basicDtoFrom(courseEnrollment.getCourseGroup()));
        }
        return courseEnrollmentDto;
    }

    @Override
    public CourseEnrollmentDto fullDtoFrom(CourseEnrollment courseEnrollment) {
        CourseEnrollmentDto courseEnrollmentDto = null;
        if(courseEnrollment != null){
            courseEnrollmentDto = basicDtoFrom(courseEnrollment);
            courseEnrollmentDto.setStudent(basicDtoFrom(courseEnrollment.getStudent()));
            List<StudentAssignmentDto> studentAssignmentDtos = new ArrayList<>();
            for(StudentAssignment studentAssignment : courseEnrollment.getAssignmentList()){
                studentAssignmentDtos.add(basicDtoFrom(studentAssignment));
            }
            courseEnrollmentDto.setAssignmentList(studentAssignmentDtos);
            courseEnrollmentDto.setStudent(basicDtoFrom(courseEnrollment.getStudent()));
        }
        return courseEnrollmentDto;
    }

    @Override
    public StudentDto basicDtoFrom(Student student) {
        StudentDto studentDto = null;
        if(student != null){
            studentDto = new StudentDto();
            studentDto.setId(student.getId());
            studentDto.setFirstName(student.getFirstName());
            studentDto.setLastName(student.getLastName());
            studentDto.setEmail(student.getEmail());
            studentDto.setAddress(student.getAddress());
            studentDto.setPhone(student.getPhone());
        }
        return studentDto;
    }

    @Override
    public StudentDto fullDtoFrom(Student student) {
        StudentDto studentDto = null;
        if(student != null){
            studentDto = basicDtoFrom(student);
            List<CourseEnrollmentDto> courseEnrollmentDtos = new ArrayList<>();
            for(CourseEnrollment courseEnrollment : student.getCourses()){
                courseEnrollmentDtos.add(basicDtoFrom(courseEnrollment));
            }
            studentDto.setCourses(courseEnrollmentDtos);
        }
        return studentDto;
    }

    @Override
    public StudentAssignmentDto basicDtoFrom(StudentAssignment studentAssignment) {
        StudentAssignmentDto studentAssignmentDto = null;
        if(studentAssignment != null){
            studentAssignmentDto = new StudentAssignmentDto();
            studentAssignmentDto.setId(studentAssignment.getId());
            studentAssignmentDto.setDeadLine(studentAssignment.getDeadLine());
            studentAssignmentDto.setGrade(studentAssignment.getGrade());
            studentAssignmentDto.setGitHub(studentAssignment.getGitHub());
        }
        return studentAssignmentDto;
    }

    @Override
    public StudentAssignmentDto fullDtoFrom(StudentAssignment studentAssignment) {
        StudentAssignmentDto studentAssignmentDto = null;
        if(studentAssignment != null){
            studentAssignmentDto = basicDtoFrom(studentAssignment);
            studentAssignmentDto.setAssignment(basicDtoFrom(studentAssignment.getAssignment()));
            studentAssignmentDto.setCourseStudent(basicDtoFrom(studentAssignment.getCourseEnrollment()));
        }
        return studentAssignmentDto;
    }
}
