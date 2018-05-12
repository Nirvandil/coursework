package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuditoryRepo extends JpaRepository<Auditory, Long> {
    Optional<Auditory> findByNumber(String number);

    void deleteByNumber(String number);
}
