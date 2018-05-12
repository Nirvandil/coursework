package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "AUDITORIES", indexes = @Index(name = "auditory_numbers_idx", columnList = "number", unique = true))
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Auditory extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String number;
    private String description;
}
