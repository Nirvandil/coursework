package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepo extends JpaRepository<TimeTable, Long> {
}
