package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Pair;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Optional;

public interface PairRepo extends JpaRepository<Pair, Long> {
    Optional<Pair> findByNumber(@Min(1) @Max(9) Integer number);
}
