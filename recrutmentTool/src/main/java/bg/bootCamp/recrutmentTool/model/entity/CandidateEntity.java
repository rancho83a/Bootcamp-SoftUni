package bg.bootCamp.recrutmentTool.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "candidatesS")
public class CandidateEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    //TODO unique
    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    private LocalDate birthDate;

    @ManyToMany
    private Set<SkillEntity> skills;

    @ManyToOne
    private RecruiterEntity recruiter;

    public RecruiterEntity getRecruiter() {
        return recruiter;
    }

    public CandidateEntity setRecruiter(RecruiterEntity recruiter) {
        this.recruiter = recruiter;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CandidateEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CandidateEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CandidateEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public CandidateEntity setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CandidateEntity setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public CandidateEntity setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
        return this;
    }

}
