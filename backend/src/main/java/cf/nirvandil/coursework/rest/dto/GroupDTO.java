package cf.nirvandil.coursework.rest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private String name;
    private Long id;
    private Byte courseNumber;
}
