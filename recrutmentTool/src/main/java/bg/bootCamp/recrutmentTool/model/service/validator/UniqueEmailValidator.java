package bg.bootCamp.recrutmentTool.model.service.validator;

import bg.bootCamp.recrutmentTool.service.CandidateService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final CandidateService candidateService;

  public UniqueEmailValidator(CandidateService candidateService) {
    this.candidateService = candidateService;
  }

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (email == null) {
      return true;
    }
    return candidateService.isEmailFree(email);
  }
}
