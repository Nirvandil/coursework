package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "FACULTIES")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "courses")
public class Faculty extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "faculty")
    private Set<Course> courses;
    @ManyToOne(optional = false)
    private University university;
}
