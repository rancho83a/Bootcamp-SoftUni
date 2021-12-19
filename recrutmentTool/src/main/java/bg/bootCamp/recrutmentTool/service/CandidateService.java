package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.dto.CandidateDto;
import bg.bootCamp.recrutmentTool.model.view.CandidateViewModel;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    Long createCandidate(CandidateViewModel candidateViewModel);

    Optional<CandidateViewModel> getCandidateById(Long id);

    boolean isEmailFree(String email);

    void deleteCandidateById(Long id);

    Long updateCandidate(CandidateDto candidateDto);

    List<CandidateDto> getAllCandidates();
}
