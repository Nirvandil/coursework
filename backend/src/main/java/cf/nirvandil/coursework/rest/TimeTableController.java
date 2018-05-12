package cf.nirvandil.coursework.rest;

import cf.nirvandil.coursework.model.TimeTable;
import cf.nirvandil.coursework.model.WorkPair;
import cf.nirvandil.coursework.repo.TimeTableRepo;
import cf.nirvandil.coursework.rest.dto.TimeTableDTO;
import cf.nirvandil.coursework.rest.dto.WorkPairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/time-table")
public class TimeTableController {

    private final TimeTableRepo timeTableRepo;

    @Autowired
    public TimeTableController(TimeTableRepo timeTableRepo) {
        this.timeTableRepo = timeTableRepo;
    }

    @GetMapping
    public List<TimeTableDTO> getTimeTables() {
        List<TimeTable> timeTables = timeTableRepo.findAll();
        return timeTables.stream()
                .map(timeTable -> {
                    TimeTableDTO timeTableDTO = new TimeTableDTO();
                    Set<WorkPair> pairs = timeTable.getPairs();
                    List<WorkPairDTO> workPairDTOS = pairs.stream()
                            .map(workPair -> {
                                WorkPairDTO workPairDTO = new WorkPairDTO();
                                workPairDTO.setDate(workPair.getDate());
                                workPairDTO.setAuditoryNumber(workPair.getAuditory().getNumber());
                                workPairDTO.setDiscipline(workPair.getDiscipline().getName());
                                workPairDTO.setGroupName(workPair.getGroup().getName());
                                workPairDTO.setPairNumber(workPair.getPair().getNumber());
                                workPairDTO.setTeacher(workPair.getTeacher().getName());
                                workPairDTO.setId(workPair.getId());
                                return workPairDTO;
                            }).sorted(Comparator.comparingInt(WorkPairDTO::getPairNumber))
                            .collect(toList());
                    timeTableDTO.setWorkPairs(workPairDTOS);
                    timeTableDTO.setId(timeTable.getId());
                    return timeTableDTO;
                }).collect(toList());
    }
}
