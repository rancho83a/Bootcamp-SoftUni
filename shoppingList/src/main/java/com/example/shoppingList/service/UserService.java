package com.example.shoppingList.service;


import com.example.shoppingList.model.service.UserRegistrationServiceModel;

public interface UserService {

  void initializeUsersAndRoles();

  void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

  boolean isUserNameFree(String username);

  boolean isEmailFree(String email);
}
