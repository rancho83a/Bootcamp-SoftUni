package bg.bootCamp.recrutmentTool.web;


import bg.bootCamp.recrutmentTool.model.dto.RecruiterDto;
import bg.bootCamp.recrutmentTool.model.dto.RecruiterWithLevelDto;
import bg.bootCamp.recrutmentTool.service.RecruiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recruiters")
public class RecruiterController {
    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RecruiterDto>> getRecruitersWithCandidates() {
        List<RecruiterDto> allRecruiters = this.recruiterService.getAllRecruiters();
        return ResponseEntity.ok(allRecruiters);
    }


    @GetMapping()
    public ResponseEntity<List<RecruiterWithLevelDto>> getRecruiterWithLevel(
            @RequestParam(name = "level") int level) {

        return ResponseEntity.ok(
                this.recruiterService.getRecruitersWithLevel(level));
    }

}
