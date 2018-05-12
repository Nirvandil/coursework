package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.repo.StudentRepo;
import cf.nirvandil.coursework.rest.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Cacheable("students")
    @GetMapping
    public List<StudentDTO> getStudents() {
        return studentRepo.findAll().stream().map(StudentDTO::new).collect(toList());
    }
}
