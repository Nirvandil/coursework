package cf.nirvandil.coursework.model.abstr;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "id_generator")
    @SequenceGenerator(initialValue = 1000, name = "id_generator")
    private Long id;
}
