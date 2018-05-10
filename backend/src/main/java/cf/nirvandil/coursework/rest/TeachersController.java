package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.dto.TeacherDTO;
import cf.nirvandil.coursework.repo.TeachersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/teachers")
public class TeachersController {
    private final TeachersRepo teachersRepo;

    @Autowired
    public TeachersController(TeachersRepo teachersRepo) {
        this.teachersRepo = teachersRepo;
    }

    @Cacheable("teachers")
    @GetMapping
    public List<TeacherDTO> getTeachers() {
        return teachersRepo.findAll().stream().map(TeacherDTO::new).collect(toList());
    }
}
