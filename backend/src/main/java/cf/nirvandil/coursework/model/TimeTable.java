package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Data
@Table(name = "TIME_TABLE")
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable extends BaseEntity {
    @OneToMany(fetch = FetchType.EAGER, cascade = ALL, mappedBy = "timeTable")
    private Set<WorkPair> pairs;

    public void addPair(WorkPair pair) {
        pairs.add(pair);
        pair.setTimeTable(this);
    }
}
