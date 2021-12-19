package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.dto.CandidateDto;

import java.util.Optional;

public interface CandidateService {
    long createCandidate(CandidateDto candidateDto);

    Optional<CandidateDto> getCandidateById(Long id);

    boolean isEmailFree(String email);

    void deleteCandidateById(Long id);

    Long updateCandidate(CandidateDto candidateDto);
}
