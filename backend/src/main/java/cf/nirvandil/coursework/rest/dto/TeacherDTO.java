package cf.nirvandil.coursework.rest.dto;

import cf.nirvandil.coursework.model.Discipline;
import cf.nirvandil.coursework.model.Teacher;
import lombok.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private Long id;
    private String name;
    private List<String> disciplines;

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.disciplines = teacher.getDisciplines().stream()
                .map(Discipline::getName)
                .collect(toList());
    }
}
