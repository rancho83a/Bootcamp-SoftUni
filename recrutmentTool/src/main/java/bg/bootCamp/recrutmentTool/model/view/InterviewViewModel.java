package bg.bootCamp.recrutmentTool.model.view;

public class InterviewViewModel {
    private String job;
    private String candidateName;
    private String recruiterName;

    public String getJob() {
        return job;
    }

    public InterviewViewModel setJob(String job) {
        this.job = job;
        return this;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public InterviewViewModel setCandidateName(String candidateName) {
        this.candidateName = candidateName;
        return this;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public InterviewViewModel setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
        return this;
    }
}
