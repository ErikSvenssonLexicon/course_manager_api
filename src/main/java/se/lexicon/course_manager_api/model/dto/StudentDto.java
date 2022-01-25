package se.lexicon.course_manager_api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import se.lexicon.course_manager_api.validation.OnPost;
import se.lexicon.course_manager_api.validation.OnPut;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class StudentDto {
    @NotBlank(groups = {OnPut.class})
    private String id;
    @NotBlank(groups = {OnPost.class, OnPut.class})
    private String firstName;
    @NotBlank(groups = {OnPost.class, OnPut.class})
    private String lastName;
    @NotBlank(groups = {OnPost.class, OnPut.class})
    private String email;
    @NotBlank(groups = {OnPost.class, OnPut.class})
    private String address;
    @NotBlank(groups = {OnPost.class, OnPut.class})
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CourseEnrollmentDto> courses;
}
