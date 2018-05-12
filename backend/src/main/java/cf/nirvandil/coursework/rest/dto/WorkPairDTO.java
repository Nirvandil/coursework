package cf.nirvandil.coursework.rest.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkPairDTO {
    private Long id;
    @Min(1)
    @Max(9)
    @NotNull
    private Integer pairNumber;
    @NotNull
    private String groupName;
    @NotNull
    private String discipline;
    private String teacher;
    @NotNull
    private String auditoryNumber;
    @NotNull
    private LocalDate date;
    @NotNull
    private Long teacherId;
    @NotNull
    private Long timeTableId;
}
