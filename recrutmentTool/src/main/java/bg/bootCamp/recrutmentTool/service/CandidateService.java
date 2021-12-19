package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.service.CandidateServiceModel;
import bg.bootCamp.recrutmentTool.model.view.CandidateViewModel;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    Long createCandidate(CandidateViewModel candidateViewModel);

    Optional<CandidateViewModel> getCandidateById(Long id);

    boolean isEmailFree(String email);

    void deleteCandidateById(Long id);

    Long updateCandidate(CandidateServiceModel candidateDto);

    List<CandidateServiceModel> getAllCandidates();
}
