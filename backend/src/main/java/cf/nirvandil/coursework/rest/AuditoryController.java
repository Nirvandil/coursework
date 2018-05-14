package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.model.Auditory;
import cf.nirvandil.coursework.repo.AuditoryRepo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;

@Transactional
@RestController
@RequestMapping("/api/auditories")
public class AuditoryController {

    private final AuditoryRepo auditoryRepo;

    @Autowired
    public AuditoryController(AuditoryRepo auditoryRepo) {
        this.auditoryRepo = auditoryRepo;
    }

    @Cacheable("auditories")
    @GetMapping
    public List<Auditory> getAuditories() {
        return auditoryRepo.findAll();
    }

    @CacheEvict(value = "auditories", allEntries = true)
    @PostMapping
    public ResponseEntity<Void> createAuditory(@RequestBody Auditory auditory) {
        auditoryRepo.save(auditory);
        return ResponseEntity.ok().build();
    }

    @CacheEvict(value = "auditories", allEntries = true)
    @DeleteMapping("/{number}")
    public ResponseEntity<Void> deleteAuditory(@PathVariable("number") String number) {
        auditoryRepo.deleteByNumber(number);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @ResponseStatus(CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleConstraint(Exception e) {
        return ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
    }
}
