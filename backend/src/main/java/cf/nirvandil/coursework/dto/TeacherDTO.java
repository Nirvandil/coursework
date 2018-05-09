package cf.nirvandil.coursework.dto;

import cf.nirvandil.coursework.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private String name;

    public TeacherDTO(Teacher teacher) {
        this.name = teacher.getName();
    }
}
