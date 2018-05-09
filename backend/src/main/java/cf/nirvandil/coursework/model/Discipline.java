package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Table(name = "DISCIPLINES")
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
}
