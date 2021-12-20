package bg.bootCamp.recrutmentTool.web;


import bg.bootCamp.recrutmentTool.model.view.InterviewViewModel;
import bg.bootCamp.recrutmentTool.service.InterviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/interviews")
public class InterviewController {
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }


    @GetMapping()
    public ResponseEntity<List<InterviewViewModel>> getAllInterviews() {
        List<InterviewViewModel> allInterview = this.interviewService.getAllInterviews();
        return ResponseEntity.ok(allInterview);
    }
}
