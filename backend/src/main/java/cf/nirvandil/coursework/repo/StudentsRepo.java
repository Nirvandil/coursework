package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepo extends JpaRepository<Student, Long> {
}
