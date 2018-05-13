package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.model.*;
import cf.nirvandil.coursework.repo.*;
import cf.nirvandil.coursework.rest.dto.WorkPairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.function.Supplier;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;

@Transactional
@RestController
@RequestMapping("/api/work-pairs")
public class WorkPairsController {

    private final WorkPairRepo workPairRepo;
    private final DisciplineRepo disciplineRepo;
    private final TeacherRepo teacherRepo;
    private final GroupRepo groupRepo;
    private final PairRepo pairRepo;
    private final AuditoryRepo auditoryRepo;
    private final TimeTableRepo timeTableRepo;

    @Autowired
    public WorkPairsController(WorkPairRepo workPairRepo,
                               DisciplineRepo disciplineRepo,
                               TeacherRepo teacherRepo,
                               GroupRepo groupRepo,
                               PairRepo pairRepo,
                               AuditoryRepo auditoryRepo,
                               TimeTableRepo timeTableRepo) {
        this.workPairRepo = workPairRepo;
        this.disciplineRepo = disciplineRepo;
        this.teacherRepo = teacherRepo;
        this.groupRepo = groupRepo;
        this.pairRepo = pairRepo;
        this.auditoryRepo = auditoryRepo;
        this.timeTableRepo = timeTableRepo;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createWorkPair(@Valid @RequestBody WorkPairDTO workPairDTO) {
        TimeTable timeTable = timeTableRepo.findById(workPairDTO.getTimeTableId())
                .orElseThrow(notFoundEntity("TimeTable", String.valueOf(workPairDTO.getTimeTableId())));
        Group group = groupRepo.findByName(workPairDTO.getGroupName())
                .orElseThrow(notFoundEntity("Group", workPairDTO.getGroupName()));
        Discipline discipline = disciplineRepo.findByName(workPairDTO.getDiscipline())
                .orElseThrow(notFoundEntity("Discipline", workPairDTO.getDiscipline()));
        Pair pair = pairRepo.findByNumber(workPairDTO.getPairNumber())
                .orElseThrow(notFoundEntity("Pair", String.valueOf(workPairDTO.getPairNumber())));
        Teacher teacher = teacherRepo.findById(workPairDTO.getTeacherId())
                .orElseThrow(notFoundEntity("Teacher", String.valueOf(workPairDTO.getTeacherId())));
        Auditory auditory = auditoryRepo.findByNumber(workPairDTO.getAuditoryNumber())
                .orElseThrow(notFoundEntity("Auditory", workPairDTO.getAuditoryNumber()));
        timeTable.getPairs().add(new WorkPair(pair, group, discipline, teacher, auditory, workPairDTO.getDate(), workPairDTO.getType()));
        timeTableRepo.save(timeTable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkPair(@PathVariable Long id) {
        workPairRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private Supplier<EntityNotFoundException> notFoundEntity(String name, String value) {
        return () -> new EntityNotFoundException(format("%s %s not found", name, value));
    }
}
