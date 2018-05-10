package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.dto.WorkPairDTO;
import cf.nirvandil.coursework.model.WorkPair;
import cf.nirvandil.coursework.repo.DisciplineRepo;
import cf.nirvandil.coursework.repo.GroupsRepo;
import cf.nirvandil.coursework.repo.TeachersRepo;
import cf.nirvandil.coursework.repo.WorkPairRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;

@Transactional
@RestController
@RequestMapping("/api/work-pairs")
public class WorkPairsController {

    private final WorkPairRepo workPairRepo;
    private final DisciplineRepo disciplineRepo;
    private final TeachersRepo teachersRepo;
    private final GroupsRepo groupsRepo;

    @Autowired
    public WorkPairsController(WorkPairRepo workPairRepo,
                               DisciplineRepo disciplineRepo,
                               TeachersRepo teachersRepo,
                               GroupsRepo groupsRepo) {
        this.workPairRepo = workPairRepo;
        this.disciplineRepo = disciplineRepo;
        this.teachersRepo = teachersRepo;
        this.groupsRepo = groupsRepo;
    }

    @GetMapping
    public List<WorkPairDTO> getWorkPairs() {
        return workPairRepo.findAll().stream().map(this::toWorkPairDTO).collect(toList());
    }

    private WorkPairDTO toWorkPairDTO(WorkPair workPair) {
        return new WorkPairDTO(workPair.getId(), workPair.getPair().getNumber(), workPair.getGroup().getName(),
                workPair.getDiscipline().getName(), workPair.getTeacher().getName(),
                workPair.getAuditory().getNumber(), workPair.getDate());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createWorkPair(WorkPairDTO workPairDTO) {
        WorkPair workPair = new WorkPair();
        workPair.setDate(workPairDTO.getDate());
        workPairRepo.save(workPair);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkPair(@PathVariable Long id) {
        workPairRepo.deleteById(id);
    }

}
