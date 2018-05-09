package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.NamedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Table(name = "TEACHERS")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "disciplines")
public class Teacher extends NamedEntity {
    @ManyToMany
    @JoinColumn(name = "TEACHER_DISCIPLINES")
    private Set<Discipline> disciplines;
}
