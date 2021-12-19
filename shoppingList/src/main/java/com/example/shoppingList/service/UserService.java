package com.example.shoppingList.service;


import com.example.shoppingList.model.entity.UserEntity;
import com.example.shoppingList.model.service.UserRegistrationServiceModel;

import java.util.Optional;

public interface UserService {

  void initializeUsersAndRoles();

  void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

  boolean isUserNameFree(String username);

  boolean isEmailFree(String email);

    Optional<UserEntity> findByUsername(String username);

  boolean isAdmin(UserEntity currentUser);
}
