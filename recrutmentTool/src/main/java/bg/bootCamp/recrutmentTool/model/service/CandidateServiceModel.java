package bg.bootCamp.recrutmentTool.model.service;


import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.model.view.RecruiterViewModel;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CandidateServiceModel {

    private Long id;


    private String firstName;

    private String lastName;



    private String email;


    private String bio;


    private LocalDate birthDate;

    private Set<SkillDto> skills =new HashSet<>();

    private RecruiterViewModel recruiter;

    public RecruiterViewModel getRecruiter() {
        return recruiter;
    }

    public CandidateServiceModel setRecruiter(RecruiterViewModel recruiter) {
        this.recruiter = recruiter;
        return this;
    }

    //TODO recruter


    public Long getId() {
        return id;
    }

    public CandidateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CandidateServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CandidateServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CandidateServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public CandidateServiceModel setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CandidateServiceModel setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public CandidateServiceModel setSkills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }
    public void addSkill(SkillDto skill){
        this.skills.add(skill);
    }
}
