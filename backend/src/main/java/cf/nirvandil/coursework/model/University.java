package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "UNIVERSITY")
@Entity
@EqualsAndHashCode(callSuper = true, exclude = "faculties")
@NoArgsConstructor
@AllArgsConstructor
public class University extends BaseEntity {
    @OneToMany(mappedBy = "university")
    private Set<Faculty> faculties;
    private String name;
    private String description;
    @OneToMany(mappedBy = "university")
    private Set<Teacher> teachers;
}
