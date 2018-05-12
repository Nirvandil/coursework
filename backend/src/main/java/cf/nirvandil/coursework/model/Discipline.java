package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "DISCIPLINES", indexes = @Index(name = "discipline_names_idx", columnList = "name", unique = true))
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "teachers")
public class Discipline extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @ManyToMany(mappedBy = "disciplines", fetch = FetchType.EAGER)
    private Set<Teacher> teachers;

    public Discipline(String discipline) {
        this.name = discipline;
    }
}
