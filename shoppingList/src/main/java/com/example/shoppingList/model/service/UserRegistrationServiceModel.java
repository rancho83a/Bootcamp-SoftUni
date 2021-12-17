package com.example.shoppingList.model.service;

public class UserRegistrationServiceModel {


  private String password;
  private String username;
  private String email;

  public String getUsername() {
    return username != null ?
        username.trim() :
        null;
  }

  public UserRegistrationServiceModel setUsername(String username) {
    this.username = username;
    return this;
  }


  public String getPassword() {
    return password;
  }

  public UserRegistrationServiceModel setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegistrationServiceModel setEmail(String email) {
    this.email = email;
    return this;
  }
}
