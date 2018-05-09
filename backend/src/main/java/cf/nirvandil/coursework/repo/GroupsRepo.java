package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepo extends JpaRepository<Group, Long> {
}
