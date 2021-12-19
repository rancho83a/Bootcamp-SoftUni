package bg.bootCamp.recrutmentTool.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="interviews")
public class InterviewEntity extends BaseEntity{

    @ManyToOne
    private JobEntity job;

    @ManyToOne
    private CandidateEntity candidate;

    public JobEntity getJob() {
        return job;
    }

    public InterviewEntity setJob(JobEntity job) {
        this.job = job;
        return this;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public InterviewEntity setCandidate(CandidateEntity candidate) {
        this.candidate = candidate;
        return this;
    }
}
