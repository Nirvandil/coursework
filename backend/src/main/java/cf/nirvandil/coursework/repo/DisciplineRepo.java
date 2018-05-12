package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplineRepo extends JpaRepository<Discipline, Long> {
    Optional<Discipline> findByName(String name);
}
