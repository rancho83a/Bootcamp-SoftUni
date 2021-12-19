package bg.bootCamp.recrutmentTool.model.dto;


import bg.bootCamp.recrutmentTool.model.service.validator.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CandidateDto {

    private Long id;


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

    private RecruiterDto recruiter;

    public RecruiterDto getRecruiter() {
        return recruiter;
    }

    public CandidateDto setRecruiter(RecruiterDto recruiter) {
        this.recruiter = recruiter;
        return this;
    }

    //TODO recruter


    public Long getId() {
        return id;
    }

    public CandidateDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CandidateDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CandidateDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CandidateDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public CandidateDto setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CandidateDto setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public CandidateDto setSkills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }
    public void addSkill(SkillDto skill){
        this.skills.add(skill);
    }
}
