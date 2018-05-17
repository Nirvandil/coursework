package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.model.Discipline;
import cf.nirvandil.coursework.repo.DisciplineRepo;
import cf.nirvandil.coursework.rest.dto.DisciplineDTO;
import cf.nirvandil.coursework.rest.dto.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final DisciplineRepo disciplineRepo;

    @Autowired
    public DisciplineController(DisciplineRepo disciplineRepo) {
        this.disciplineRepo = disciplineRepo;
    }

    @Cacheable("disciplines")
    @GetMapping
    public List<DisciplineDTO> getDisciplines() {
        return disciplineRepo.findAll().stream().map(this::toDisciplineDTO).collect(toList());
    }

    private DisciplineDTO toDisciplineDTO(Discipline discipline) {
        return new DisciplineDTO(discipline.getId(), discipline.getName(), discipline.getDescription(),
                discipline.getTeachers().stream()
                .map(TeacherDTO::new).collect(toList()));
    }
}
