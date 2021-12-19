package bg.bootCamp.recrutmentTool.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class JobEntity extends BaseEntity {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal salary;
    @ManyToMany
    private Set<SkillEntity> skills;

    public String getTitle() {
        return title;
    }

    public JobEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public JobEntity setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public JobEntity setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
        return this;
    }
}
