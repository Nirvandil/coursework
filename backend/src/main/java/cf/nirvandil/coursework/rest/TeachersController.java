package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.repo.TeacherRepo;
import cf.nirvandil.coursework.rest.dto.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@RestController
@RequestMapping("/api/teachers")
public class TeachersController {
    private final TeacherRepo teacherRepo;

    @Autowired
    public TeachersController(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Cacheable("teachers")
    @GetMapping
    public List<TeacherDTO> getTeachers() {
        return teacherRepo.findAll().stream().map(TeacherDTO::new).collect(toList());
    }
}
