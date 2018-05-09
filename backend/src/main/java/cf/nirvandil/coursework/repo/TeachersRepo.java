package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersRepo extends JpaRepository<Teacher, Long> {
}
