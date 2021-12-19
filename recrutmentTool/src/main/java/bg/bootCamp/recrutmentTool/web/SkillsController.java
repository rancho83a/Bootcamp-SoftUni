package bg.bootCamp.recrutmentTool.web;


import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
public class SkillsController {
    private final SkillService skillService;

    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable("id") Long id) {
        Optional<SkillDto> skillById = this.skillService.getSkillById(id);

        if (skillById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(skillById.get());
    }

    @GetMapping("/active")
    public ResponseEntity<List<SkillDto>> getActiveSkills() {
        List<SkillDto> activeSkills = this.skillService.getActiveSkills();
        return ResponseEntity.ok(activeSkills);
    }

}
