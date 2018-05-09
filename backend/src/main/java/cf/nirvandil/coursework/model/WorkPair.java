package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Data
@Table(name = "WORK_PAIRS",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_pair",
                columnNames = {"pair_id", "date", "discipline_id", "auditory_id"}
        )
)
@Entity
@EqualsAndHashCode(callSuper = true)
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
}
