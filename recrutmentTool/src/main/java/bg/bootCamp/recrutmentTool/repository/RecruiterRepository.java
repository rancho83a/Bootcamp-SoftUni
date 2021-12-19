package bg.bootCamp.recrutmentTool.repository;

import bg.bootCamp.recrutmentTool.model.dto.RecruiterDto;
import bg.bootCamp.recrutmentTool.model.entity.RecruiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<RecruiterEntity, Long> {

Optional<RecruiterEntity> findByEmailAndLastName(String email, String lastName);

@Query("SELECT r FROM RecruiterEntity r WHERE size(r.candidates)>0 ")
List<RecruiterEntity> findAllWithCandidates();


List<RecruiterEntity> findAllByExperienceLevel(int experienceLevel);


}
