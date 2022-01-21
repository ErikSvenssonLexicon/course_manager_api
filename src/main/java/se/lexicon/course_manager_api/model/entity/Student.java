package se.lexicon.course_manager_api.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    private List<CourseStudent> courses;

    public List<CourseStudent> getCourses() {
        if(courses == null) courses = new ArrayList<>();
        return courses;
    }

    public void setCourses(List<CourseStudent> courses) {
        if(courses == null) courses = new ArrayList<>();
        if(courses.isEmpty()){
            if(this.courses != null){
                for(CourseStudent courseStudent : this.courses){
                    if(courseStudent != null){
                        courseStudent.setStudent(null);
                    }
                }
            }
        }else{
            for(CourseStudent courseStudent : courses){
                if(courseStudent != null){
                    courseStudent.setStudent(this);
                }
            }
        }
        this.courses = courses;
    }

    public void addCourses(CourseStudent...courseStudents){
        if(courseStudents == null) return;
        if(this.courses == null) this.courses = new ArrayList<>();
        if(courseStudents.length > 0){
            for(CourseStudent courseStudent : courseStudents){
                if(courseStudent != null && !this.courses.contains(courseStudent)){
                    this.courses.add(courseStudent);
                    courseStudent.setStudent(this);
                }
            }
        }
    }

    public void removeCourses(CourseStudent...courseStudents){
        if(courseStudents == null) return;
        if(this.courses == null) this.courses = new ArrayList<>();
        if(courseStudents.length > 0){
            for(CourseStudent courseStudent : courseStudents){
                if(courseStudent != null && this.courses.contains(courseStudent)){
                    courses.remove(courseStudent);
                    courseStudent.setStudent(null);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(address, student.address) && Objects.equals(phone, student.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, address, phone);
    }
}
