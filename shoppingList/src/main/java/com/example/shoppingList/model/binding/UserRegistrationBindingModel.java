package com.example.shoppingList.model.binding;


import com.example.shoppingList.model.validator.UniqueUserName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserRegistrationBindingModel {


  @NotBlank
  @Size(min=3, max=20)
  private String password;
  @NotBlank
  @Size(min=3, max=20)
  private String confirmPassword;
  @NotBlank
  @Size(min=3, max=20)

  @NotBlank
  @UniqueUserName
  private String username;

  public String getUsername() {
    return username;
  }

  public UserRegistrationBindingModel setUsername(String username) {
    this.username = username;
    return this;
  }


  public String getPassword() {
    return password;
  }

  public UserRegistrationBindingModel setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }
}
