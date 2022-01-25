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
public class CourseGroupDto {
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CourseDto course;
    private LocalDate startDate;
    private LocalDate endDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CourseEnrollmentDto> courseStudents;
}
