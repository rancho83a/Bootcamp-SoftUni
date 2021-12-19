package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.service.CandidateServiceModel;
import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.model.entity.CandidateEntity;
import bg.bootCamp.recrutmentTool.model.entity.RecruiterEntity;
import bg.bootCamp.recrutmentTool.model.entity.SkillEntity;
import bg.bootCamp.recrutmentTool.model.view.CandidateViewModel;
import bg.bootCamp.recrutmentTool.model.view.RecruiterViewModel;
import bg.bootCamp.recrutmentTool.repository.CandidateRepository;
import bg.bootCamp.recrutmentTool.repository.RecruiterRepository;
import bg.bootCamp.recrutmentTool.repository.SkillRepository;
import bg.bootCamp.recrutmentTool.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;
    //private final SkillService skillService;
    private final SkillRepository skillRepository;
    private final RecruiterRepository recruiterRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository, ModelMapper modelMapper,
                                SkillRepository skillRepository, RecruiterRepository recruiterRepository) {
        this.candidateRepository = candidateRepository;
        this.modelMapper = modelMapper;
        this.skillRepository = skillRepository;
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public Long createCandidate(CandidateViewModel candidateViewModel) {

        CandidateEntity candidate = new CandidateEntity()
                .setFirstName(candidateViewModel.getFirstName())
                .setLastName(candidateViewModel.getLastName())
                .setEmail(candidateViewModel.getEmail())
                .setBirthDate(candidateViewModel.getBirthDate())
                .setBio(candidateViewModel.getBio());

        Set<SkillDto> skillsDto = candidateViewModel.getSkills();

        Set<SkillEntity> candidateSkills = new HashSet<>();

        skillsDto
                .forEach(skill -> {

                    Optional<SkillEntity> skillByName = this.skillRepository.findByName(skill.getName());

                    if (skillByName.isEmpty()) {
                        SkillEntity newSkill = new SkillEntity()
                                .setName(skill.getName())
                                .setCandidatesCount(1);
                        SkillEntity savedNewSkill = skillRepository.save(newSkill);
                        candidateSkills.add(savedNewSkill);
                    } else {
                        candidateSkills.add(skillByName.get());
                    }
                });
        candidate.setSkills(candidateSkills);


        RecruiterViewModel recruiterViewModel = candidateViewModel.getRecruiter();

        Optional<RecruiterEntity> recruiterOpt = this.recruiterRepository.findByEmailAndLastName(recruiterViewModel.getEmail(),
                recruiterViewModel.getLastName());

        RecruiterEntity recruiter = new RecruiterEntity();

        if (recruiterOpt.isEmpty()) {
            recruiter.setLastName(recruiterViewModel.getLastName())
                    .setEmail(recruiterViewModel.getEmail())
                    .setCountry(recruiterViewModel.getCountry())
                    .setExperienceLevel(1)
                    .setInterviewSlot(0);


        } else {
            recruiter = recruiterOpt.get();
            recruiter.setExperienceLevel(recruiter.getExperienceLevel() + 1);
        }

        RecruiterEntity savedRecruiter = recruiterRepository.save(recruiter);

        candidate.setRecruiter(savedRecruiter);

        CandidateEntity saved = this.candidateRepository.save(candidate);


        return saved.getId();
    }

    @Transactional
    @Override
    public Optional<CandidateViewModel> getCandidateById(Long id) {
        return this.candidateRepository.findById(id).map(this::mapToCandidateViewModel);
    }

    @Override
    public boolean isEmailFree(String email) {
        return this.candidateRepository.findCandidateEntityByEmail(email).isEmpty();
    }

    @Override
    public void deleteCandidateById(Long id) {
        this.candidateRepository.deleteById(id);
    }

    @Override
    public Long updateCandidate(CandidateServiceModel candidateDto) {

        CandidateEntity candidate = candidateRepository.findById(candidateDto.getId()).orElse(null);
        if (candidate == null) {
            return null;
        }

        candidate
                .setFirstName(candidateDto.getFirstName())
                .setLastName(candidateDto.getLastName())
                .setEmail(candidateDto.getEmail())
                .setBirthDate(candidateDto.getBirthDate())
                .setBio(candidateDto.getBio());

        Set<SkillDto> skillsDto = candidateDto.getSkills();

        Set<SkillEntity> candidateSkills = new HashSet<>();

        skillsDto
                .forEach(skill -> {

                    Optional<SkillEntity> skillByName = this.skillRepository.findByName(skill.getName());

                    if (skillByName.isEmpty()) {
                        SkillEntity newSkill = new SkillEntity()
                                .setName(skill.getName())
                                .setCandidatesCount(1);
                        SkillEntity savedNewSkill = skillRepository.save(newSkill);
                        candidateSkills.add(savedNewSkill);
                    } else {
                        candidateSkills.add(skillByName.get());
                    }
                });
        candidate.setSkills(candidateSkills);


        CandidateEntity saved = this.candidateRepository.save(candidate);


        return saved.getId();
    }

    @Override
    public List<CandidateServiceModel> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(c->modelMapper.map(c, CandidateServiceModel.class))
                .collect(Collectors.toList());

    }

    private CandidateServiceModel mapToCandidateDto(CandidateEntity candidate) {
        return modelMapper.map(candidate, CandidateServiceModel.class);
    }
    private CandidateViewModel     mapToCandidateViewModel(CandidateEntity candidate) {
        return modelMapper.map(candidate, CandidateViewModel.class);
    }
}
