package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepo extends JpaRepository<Discipline, Long> {
}
