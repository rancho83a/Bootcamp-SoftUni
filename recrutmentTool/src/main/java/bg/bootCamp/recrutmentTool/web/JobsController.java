package bg.bootCamp.recrutmentTool.web;


import bg.bootCamp.recrutmentTool.model.dto.JobDto;
import bg.bootCamp.recrutmentTool.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    private final JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping()
    public ResponseEntity<JobDto> create(@RequestBody @Valid JobDto jobDto,
                                         UriComponentsBuilder uriBuilder) {

        long jobId = this.jobService.createJob(jobDto);

        URI location = uriBuilder.path("candidates/{id}").buildAndExpand(jobId).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JobDto> delete(@PathVariable("id") Long id) {
        jobService.deleteJobById(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping()
    public ResponseEntity<List<JobDto>> getJobBySkill(
            @RequestParam(name = "skill") String skill) {


        return ResponseEntity.ok(
                this.jobService.getJobBySkill(skill));
    }
}
