package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.PairType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PairTypeRepo extends JpaRepository<PairType, Long> {
    Optional<PairType> findByType(String type);
}
