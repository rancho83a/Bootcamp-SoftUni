package bg.bootCamp.recrutmentTool.repository;

import bg.bootCamp.recrutmentTool.model.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity,Long> {
    Optional<CandidateEntity> findCandidateEntityByEmail(String email);

}
