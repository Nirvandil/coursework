package cf.nirvandil.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkPairDTO {
    private Long id;
    private Integer pairNumber;
    private String groupName;
    private String discipline;
    private String teacher;
    private Integer auditoryNumber;
    private LocalDate date;
}
