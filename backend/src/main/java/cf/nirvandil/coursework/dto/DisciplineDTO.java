package cf.nirvandil.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineDTO {
    private String name;
    private String description;
    private List<TeacherDTO> teachers;
}
