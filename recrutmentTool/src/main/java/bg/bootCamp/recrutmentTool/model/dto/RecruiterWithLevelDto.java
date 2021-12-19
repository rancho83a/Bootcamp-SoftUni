package bg.bootCamp.recrutmentTool.model.dto;

public class RecruiterWithLevelDto {

    private String lastName;
    private String email;
    private String country;
    private String experienceLevel;

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public RecruiterWithLevelDto setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RecruiterWithLevelDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RecruiterWithLevelDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public RecruiterWithLevelDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
