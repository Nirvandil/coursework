package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "PAIR_TYPES")
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PairType extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String type;
}
