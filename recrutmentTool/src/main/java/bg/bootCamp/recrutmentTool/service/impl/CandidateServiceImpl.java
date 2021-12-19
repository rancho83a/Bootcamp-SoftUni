package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.dto.CandidateDto;
import bg.bootCamp.recrutmentTool.model.dto.RecruiterDto;
import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.model.entity.CandidateEntity;
import bg.bootCamp.recrutmentTool.model.entity.RecruiterEntity;
import bg.bootCamp.recrutmentTool.model.entity.SkillEntity;
import bg.bootCamp.recrutmentTool.repository.CandidateRepository;
import bg.bootCamp.recrutmentTool.repository.RecruiterRepository;
import bg.bootCamp.recrutmentTool.repository.SkillRepository;
import bg.bootCamp.recrutmentTool.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public long createCandidate(CandidateDto candidateDto) {

        CandidateEntity candidate = new CandidateEntity()
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


        RecruiterDto recruiterDto = candidateDto.getRecruiter();

        Optional<RecruiterEntity> recruiterOpt = this.recruiterRepository.findByEmailAndLastName(recruiterDto.getEmail(),
                recruiterDto.getLastName());

        RecruiterEntity recruiter = new RecruiterEntity();

        if (recruiterOpt.isEmpty()) {
            recruiter.setLastName(recruiterDto.getLastName())
                    .setEmail(recruiterDto.getEmail())
                    .setCountry(recruiterDto.getCountry())
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
    public Optional<CandidateDto> getCandidateById(Long id) {
        return this.candidateRepository.findById(id).map(this::mapToCandidateDto);
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
    public Long updateCandidate(CandidateDto candidateDto) {

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

    private CandidateDto mapToCandidateDto(CandidateEntity candidate) {
        return modelMapper.map(candidate, CandidateDto.class);
    }
}
