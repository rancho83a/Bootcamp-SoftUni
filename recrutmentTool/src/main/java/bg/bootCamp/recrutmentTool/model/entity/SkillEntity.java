package bg.bootCamp.recrutmentTool.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class SkillEntity extends BaseEntity{

    private String name;
    private int candidatesCount;


    public String getName() {
        return name;
    }

    public SkillEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getCandidatesCount() {
        return candidatesCount;
    }

    public SkillEntity setCandidatesCount(int candidatesCount) {
        this.candidatesCount = candidatesCount;
        return this;
    }
}
