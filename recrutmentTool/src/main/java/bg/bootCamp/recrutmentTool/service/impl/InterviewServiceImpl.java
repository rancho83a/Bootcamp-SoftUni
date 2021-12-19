package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.service.CandidateServiceModel;
import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.repository.InterviewRepository;
import bg.bootCamp.recrutmentTool.service.CandidateService;
import bg.bootCamp.recrutmentTool.service.InterviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository interviewRepository;
    private final CandidateService candidateService;

    public InterviewServiceImpl(InterviewRepository interviewRepository, CandidateService candidateService) {
        this.interviewRepository = interviewRepository;
        this.candidateService = candidateService;
    }

    @Override
    public void createInterview(Set<SkillDto> skillsDto) {

        List<CandidateServiceModel> allCandidates = candidateService.getAllCandidates();

//        skillsDto.forEach(skill->{
//
//            allCandidates
//                    .forEach(candidateDto ->{
//
//                        candidateDto.getSkills()
//                                .forEach(c_skill->{
//                                    if(c_skill.getName().equals(skill.getName())){
//                                        candidateService.getCandidateById(id)
//                                    }
//                                });
//
//                    });
//
//
//
//
//      });


    }
}
