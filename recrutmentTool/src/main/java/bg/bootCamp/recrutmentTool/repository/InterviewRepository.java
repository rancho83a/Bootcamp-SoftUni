package bg.bootCamp.recrutmentTool.repository;

import bg.bootCamp.recrutmentTool.model.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> {
    Optional<InterviewEntity> findInterviewEntityByCandidate_IdAndJob_Id(Long candidate_id, Long job_id);
}
