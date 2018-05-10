package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.dto.DisciplineDTO;
import cf.nirvandil.coursework.dto.TeacherDTO;
import cf.nirvandil.coursework.model.Discipline;
import cf.nirvandil.coursework.repo.DisciplineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
        return new DisciplineDTO(discipline.getName(), discipline.getDescription(), discipline.getTeachers().stream()
                .map(TeacherDTO::new).collect(toList()));
    }
}
