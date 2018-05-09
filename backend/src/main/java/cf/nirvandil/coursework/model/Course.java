package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Data
@Table(name = "COURSES")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "groups")
public class Course extends BaseEntity {
    @Min(1)
    @Max(6)
    @Column(nullable = false)
    private Byte number;
    @ManyToOne(optional = false)
    private Faculty faculty;
    @OneToMany(mappedBy = "course")
    private Set<Group> groups;
}
