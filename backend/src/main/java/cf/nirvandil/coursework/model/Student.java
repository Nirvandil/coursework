package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.NamedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "STUDENTS")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Student extends NamedEntity {
    @ManyToOne(optional = false)
    private Group studentGroup;
    public Student(@NotNull String lastName, @NotNull String firstName) {
        super(lastName, firstName);
    }
}
