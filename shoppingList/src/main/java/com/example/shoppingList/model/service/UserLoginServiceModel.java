package com.example.shoppingList.model.service;

public class UserLoginServiceModel {

  private String username;
  private String rawPassword;
  private String email;

  public String getEmail() {
    return email;
  }

  public UserLoginServiceModel setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserLoginServiceModel setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getRawPassword() {
    return rawPassword;
  }

  public UserLoginServiceModel setRawPassword(String rawPassword) {
    this.rawPassword = rawPassword;
    return this;
  }
}
