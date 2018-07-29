package cf.nirvandil.coursework.repo;

import cf.nirvandil.coursework.model.WorkPair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WorkPairRepo extends JpaRepository<WorkPair, Long> {
    Long countAllByTeacher_IdAndDateBetween(Long teacherId,
                                         LocalDate from, LocalDate to);
    Long countAllByDiscipline_IdAndGroup_IdAndDateBetween(Long disciplineId, Long groupId,
                                                          LocalDate from, LocalDate to);
}
