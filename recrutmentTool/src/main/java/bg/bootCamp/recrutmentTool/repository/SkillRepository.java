package bg.bootCamp.recrutmentTool.repository;

import bg.bootCamp.recrutmentTool.model.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    List<SkillEntity> findAllByCandidatesCountGreaterThan(int candidatesCount);

    Optional<SkillEntity> findByName(String name);
}
