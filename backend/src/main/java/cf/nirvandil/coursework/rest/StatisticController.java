package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.repo.WorkPairRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistic")
public class StatisticController {
    private final WorkPairRepo workPairRepo;

    @Autowired
    public StatisticController(WorkPairRepo workPairRepo) {
        this.workPairRepo = workPairRepo;
    }

    @GetMapping("/teachers/{teacherId}")
    public Long getTeacherHoursFromRange(@PathVariable Long teacherId,
                                         @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                         @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {

        return workPairRepo.countAllByTeacher_IdAndDateBetween(teacherId, from, to);
    }

    @GetMapping("/groups/{groupId}")
    public Long getDisciplineHoursFromRange(@PathVariable Long groupId,
                                            @RequestParam Long disciplineId,
                                            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return workPairRepo.countAllByDiscipline_IdAndGroup_IdAndDateBetween(disciplineId, groupId, from, to);
    }
}
