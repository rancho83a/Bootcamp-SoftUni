package bg.bootCamp.recrutmentTool.repository;

import bg.bootCamp.recrutmentTool.model.entity.JobEntity;
import bg.bootCamp.recrutmentTool.model.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<JobEntity,Long> {

  @Query(value = "SELECT * FROM jobs LEFT JOIN jobs_skills as js " +
          " on jobs.id = js.job_entity_id" +
          " LEFT JOIN skills as s  on s.id = js.skills_id " +
          " WHERE s.name= ?1 ", nativeQuery = true)
  List<JobEntity> findAllBySkills(String name);

}
