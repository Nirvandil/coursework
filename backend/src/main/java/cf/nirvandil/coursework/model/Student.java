package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.NamedEntity;
import lombok.*;

import javax.persistence.*;
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
