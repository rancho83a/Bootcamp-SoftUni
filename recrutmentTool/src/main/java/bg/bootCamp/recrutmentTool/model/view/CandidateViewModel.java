package bg.bootCamp.recrutmentTool.model.view;

import bg.bootCamp.recrutmentTool.model.dto.RecruiterDto;
import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.model.service.validator.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CandidateViewModel {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;


    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    @Size(min=10)
    private String bio;

    @Past
    private LocalDate birthDate;

    private Set<SkillDto> skills =new HashSet<>();

    private RecruiterViewModel recruiter;



    public String getFirstName() {
        return firstName;
    }

    public CandidateViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CandidateViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CandidateViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public CandidateViewModel setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CandidateViewModel setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public CandidateViewModel setSkills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }

    public RecruiterViewModel getRecruiter() {
        return recruiter;
    }

    public CandidateViewModel setRecruiter(RecruiterViewModel recruiter) {
        this.recruiter = recruiter;
        return this;
    }
}
