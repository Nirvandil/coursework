package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalTime;

@Data
@Table(name = "PAIRS", indexes = @Index(name = "pair_numbers_idx", columnList = "number", unique = true),
uniqueConstraints = {
        @UniqueConstraint(name = "unique_pair_start", columnNames = {"number", "startTime"}),
        @UniqueConstraint(name = "unique_pair_finish", columnNames = {"number", "finishTime"})
})
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Pair extends BaseEntity {
    @Min(1)
    @Max(9)
    @Column(nullable = false, unique = true)
    private Integer number;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime finishTime;
}
