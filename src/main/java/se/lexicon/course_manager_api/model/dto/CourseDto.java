package se.lexicon.course_manager_api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {
    private String id;
    private String courseName;
    private String courseTopic;
    private Integer courseDuration;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CourseAssignmentDto> courseAssignments;
}
