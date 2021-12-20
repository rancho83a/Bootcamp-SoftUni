package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.dto.RecruiterDto;
import bg.bootCamp.recrutmentTool.model.dto.RecruiterWithLevelDto;
import bg.bootCamp.recrutmentTool.model.entity.RecruiterEntity;
import bg.bootCamp.recrutmentTool.repository.RecruiterRepository;
import bg.bootCamp.recrutmentTool.service.RecruiterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class RecruiterServiceImpl implements RecruiterService {
    private final RecruiterRepository recruiterRepository;
    private final ModelMapper modelMapper;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository, ModelMapper modelMapper) {
        this.recruiterRepository = recruiterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecruiterDto> getAllRecruiters() {
        List<RecruiterEntity> allWithCandidates = this.recruiterRepository.findAllWithCandidates();
        return allWithCandidates.stream().map(r->modelMapper.map(r,RecruiterDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RecruiterWithLevelDto> getRecruitersWithLevel(int level) {
        List<RecruiterEntity> allByExperienceLevel = this.recruiterRepository.findAllByExperienceLevel(level);
        return allByExperienceLevel.stream().map(r->modelMapper.map(r,RecruiterWithLevelDto.class)).collect(Collectors.toList());
    }

    @Override
    public void save(RecruiterEntity recruiter) {
        this.recruiterRepository.save(recruiter);
    }

}
