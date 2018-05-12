package cf.nirvandil.coursework.model.abstr;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public abstract class NamedEntity extends BaseEntity {
    @NonNull
    @NotNull
    @Column(nullable = false)
    protected String lastName;
    @NonNull
    @NotNull
    @Column(nullable = false)
    protected String firstName;
    protected String middleName;

    public String getName() {
        return middleName != null ? lastName + " " + firstName + " " + middleName : lastName + " " + firstName;
    }
}
