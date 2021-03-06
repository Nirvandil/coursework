package cf.nirvandil.coursework.rest.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineDTO {
    private Long id;
    private String name;
    private String description;
    private List<TeacherDTO> teachers;
}
