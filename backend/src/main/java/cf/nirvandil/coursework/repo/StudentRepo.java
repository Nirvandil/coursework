package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
