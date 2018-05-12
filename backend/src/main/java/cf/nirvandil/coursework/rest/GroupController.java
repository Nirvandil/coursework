package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.model.Group;
import cf.nirvandil.coursework.repo.GroupRepo;
import cf.nirvandil.coursework.rest.dto.GroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupRepo groupRepo;

    @Autowired
    public GroupController(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @Cacheable("groups")
    @GetMapping
    public ResponseEntity<List<GroupDTO>> getGroups() {
        return ResponseEntity.ok(groupRepo.findAll().stream()
                .map(this::toGroupDTO)
                .collect(Collectors.toList()));
    }

    private GroupDTO toGroupDTO(Group group) {
        return new GroupDTO(group.getName(), group.getId(), group.getCourse().getNumber());
    }
}
