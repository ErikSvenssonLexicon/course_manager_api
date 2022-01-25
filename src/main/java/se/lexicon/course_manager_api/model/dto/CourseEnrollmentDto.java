package se.lexicon.course_manager_api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseEnrollmentDto {
    private String id;
    private LocalDate regDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CourseGroupDto courseGroup;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentDto student;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<StudentAssignmentDto> assignmentList;
}
