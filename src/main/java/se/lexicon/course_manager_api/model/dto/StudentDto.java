package se.lexicon.course_manager_api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CourseEnrollmentDto> courses;
}
