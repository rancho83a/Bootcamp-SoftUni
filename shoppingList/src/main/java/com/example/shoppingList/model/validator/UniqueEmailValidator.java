package com.example.shoppingList.model.validator;

import com.example.shoppingList.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueUserName, String> {

  private final UserService userService;

  public UniqueEmailValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (email == null) {
      return true;
    }
    return userService.isEmailFree(email);
  }
}