package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Data
@Table(
        name = "COURSES",
        uniqueConstraints =
        @UniqueConstraint(
                name = "unique_course_on_faculty", columnNames = {"number", "faculty_id"})
)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "groups")
@Check(constraints = "number > 0 AND number < 6")
class Course extends BaseEntity {
    @Min(1)
    @Max(6)
    @Column(nullable = false, updatable = false)
    private Byte number;
    @ManyToOne(optional = false)
    private Faculty faculty;
    @OneToMany(mappedBy = "course")
    private Set<Group> groups;
}
