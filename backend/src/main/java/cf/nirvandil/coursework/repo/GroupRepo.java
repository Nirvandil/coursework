package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepo extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);
}
