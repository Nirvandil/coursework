package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;

@Data
@Table(name = "WORK_PAIRS",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_pair",
                columnNames = {"pair_id", "date", "discipline_id", "auditory_id"}
        )
)
@Entity
@EqualsAndHashCode(callSuper = true, exclude = "timeTable")
@NoArgsConstructor
@AllArgsConstructor
public class WorkPair extends BaseEntity {
    @ManyToOne(optional = false)
    private Pair pair;
    @ManyToOne(optional = false)
    private Group group;
    @ManyToOne(optional = false)
    private Discipline discipline;
    @ManyToOne(optional = false)
    private Teacher teacher;
    @ManyToOne(optional = false)
    private Auditory auditory;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne(optional = false)
    private PairType type;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TimeTable timeTable;

    public WorkPair(Pair pair, Group group, Discipline discipline, Teacher teacher, Auditory auditory, LocalDate date, PairType type) {
        this.pair = pair;
        this.group = group;
        this.discipline = discipline;
        this.teacher = teacher;
        this.auditory = auditory;
        this.date = date;
        this.type = type;
    }
}
