package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.NamedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "TEACHERS")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "disciplines")
public class Teacher extends NamedEntity {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEACHER_DISCIPLINES")
    private Set<Discipline> disciplines;
}
