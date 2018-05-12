package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.model.Auditory;
import cf.nirvandil.coursework.repo.AuditoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
