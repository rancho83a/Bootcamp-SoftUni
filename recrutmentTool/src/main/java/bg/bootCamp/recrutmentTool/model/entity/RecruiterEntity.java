package bg.bootCamp.recrutmentTool.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="recruiters")
public class RecruiterEntity extends BaseEntity {

    private String lastName;
    private String email;
    private String country;

    private int interviewSlot;
    private int experienceLevel;

    @OneToMany(mappedBy = "recruiter", fetch = FetchType.EAGER)
    private List<CandidateEntity> candidates;

    public String getLastName() {
        return lastName;
    }

    public RecruiterEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RecruiterEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public RecruiterEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public int getInterviewSlot() {
        return interviewSlot;
    }

    public RecruiterEntity setInterviewSlot(int interviewSlot) {
        this.interviewSlot = interviewSlot;
        return this;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public RecruiterEntity setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
        return this;
    }
}
