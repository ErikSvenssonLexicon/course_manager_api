package se.lexicon.course_manager_api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.lexicon.course_manager_api.model.Rating;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StudentAssignmentDto {
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CourseAssignmentDto assignment;
    private LocalDateTime deadLine;
    private Rating grade;
    private String gitHub;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CourseStudentDto courseStudent;
}
