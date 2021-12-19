package bg.bootCamp.recrutmentTool.repository;

import bg.bootCamp.recrutmentTool.model.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> {
}
