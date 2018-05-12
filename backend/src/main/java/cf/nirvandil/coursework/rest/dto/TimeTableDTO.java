package cf.nirvandil.coursework.rest.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableDTO {
    private List<WorkPairDTO> workPairs;
    private Long id;
}
