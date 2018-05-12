package cf.nirvandil.coursework.model;

import cf.nirvandil.coursework.model.abstr.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "GROUPS", indexes = @Index(name = "group_names_idx", columnList = "name", unique = true))
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "students")
public class Group extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "studentGroup")
    private Set<Student> students;
    @ManyToOne(optional = false)
    private Course course;
}
