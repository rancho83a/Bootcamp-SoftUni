package bg.bootCamp.recrutmentTool.web;


import bg.bootCamp.recrutmentTool.model.dto.CandidateDto;
import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.model.view.CandidateViewModel;
import bg.bootCamp.recrutmentTool.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping()
    public ResponseEntity<CandidateViewModel> create(@RequestBody @Valid CandidateViewModel candidateViewModel,
                                          UriComponentsBuilder uriBuilder) {

        long candidateId = this.candidateService.createCandidate(candidateViewModel);

        URI location = uriBuilder.path("candidates/{id}").buildAndExpand(candidateId).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateViewModel> getCandidateById(@PathVariable("id") Long id) {
        Optional<CandidateViewModel> candidateById = this.candidateService.getCandidateById(id);

        if (candidateById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(candidateById.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CandidateDto> delete(@PathVariable("id") Long id) {
        candidateService.deleteCandidateById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateDto> update(
            @PathVariable("id") Long id,
            @RequestBody CandidateDto candidateDto,
            UriComponentsBuilder uriBuilder
    ) {
        candidateDto.setId(id);
        Long candidateId = this.candidateService.updateCandidate(candidateDto);

        URI location = uriBuilder.path("candidates/{id}").buildAndExpand(candidateId).toUri();

        return candidateId == null ? ResponseEntity.notFound().build()
                : ResponseEntity
                .created(location)
                .build();

    }
}
