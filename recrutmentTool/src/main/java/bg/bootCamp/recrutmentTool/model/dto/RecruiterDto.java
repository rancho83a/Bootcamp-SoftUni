package bg.bootCamp.recrutmentTool.model.dto;

public class RecruiterDto {

    private String lastName;
    private String email;
    private String country;

    public String getLastName() {
        return lastName;
    }

    public RecruiterDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RecruiterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public RecruiterDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
