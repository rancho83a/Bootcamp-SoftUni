package bg.bootCamp.recrutmentTool.model.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

public class JobDto {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    @Size(min = 10)
    private String description;
    @Positive
    private BigDecimal salary;
    private Set<SkillDto> skills;

    public Long getId() {
        return id;
    }

    public JobDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public JobDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public JobDto setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public JobDto setSkills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }
}
