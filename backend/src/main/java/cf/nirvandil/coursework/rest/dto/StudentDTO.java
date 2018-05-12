package cf.nirvandil.coursework.rest.dto;

import cf.nirvandil.coursework.model.Student;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String name;
    private String groupName;

    public StudentDTO(Student student) {
        this.name = student.getName();
        this.groupName = student.getStudentGroup().getName();
    }
}
