package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.model.entity.SkillEntity;
import bg.bootCamp.recrutmentTool.repository.SkillRepository;
import bg.bootCamp.recrutmentTool.service.SkillService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    public SkillServiceImpl(SkillRepository skillRepository, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<SkillDto> getSkillById(Long id) {
        return this.skillRepository.findById(id).map(this::mapToSkillDto);
    }

    @Override
    public List<SkillDto> getActiveSkills() {
        List<SkillEntity> allByCandidatesCountGreaterThan = this.skillRepository.findAllByCandidatesCountGreaterThan(0);
        return allByCandidatesCountGreaterThan.stream()
                .map(skill-> modelMapper.map(skill,SkillDto.class))
                .collect(Collectors.toList());
    }

    private SkillDto mapToSkillDto(SkillEntity skill) {
        return modelMapper.map(skill, SkillDto.class);
    }
}
