package bg.bootCamp.recrutmentTool.model.view;

public class RecruiterViewModel {
    private String lastName;
    private String email;
    private String country;

    public String getLastName() {
        return lastName;
    }

    public RecruiterViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RecruiterViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public RecruiterViewModel setCountry(String country) {
        this.country = country;
        return this;
    }
}
