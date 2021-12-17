package com.example.shoppingList.service.impl;


import com.example.shoppingList.model.entity.UserEntity;
import com.example.shoppingList.model.entity.UserRoleEntity;
import com.example.shoppingList.model.entity.enums.UserRoleEnum;
import com.example.shoppingList.model.service.UserRegistrationServiceModel;
import com.example.shoppingList.repository.UserRepository;
import com.example.shoppingList.repository.UserRoleRepository;
import com.example.shoppingList.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ShoppingListUserServiceImpl shoppingListUserService;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           ShoppingListUserServiceImpl shoppingListUserService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;

        this.shoppingListUserService = shoppingListUserService;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("12345"))
                    .setEmail("admin@test.com")
                    .setRoles(List.of(adminRole, userRole));

            userRepository.save(admin);

            UserEntity user = new UserEntity();
            user
                    .setUsername("user")
                    .setPassword(passwordEncoder.encode("12345"))
                    .setEmail("user@test.com")
                    .setRoles(List.of(userRole));

            userRepository.save(user);
        }
    }

    private void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser
                .setUsername(userRegistrationServiceModel.getUsername())
                .setEmail(userRegistrationServiceModel.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()))
                .setRoles(List.of(userRole));

        newUser = userRepository.save(newUser);

        // this is the Spring representation of a user
        UserDetails principal = shoppingListUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }

    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean isEmailFree(String email) {
        return userRepository.findUserEntityByEmail(email).isEmpty();

    }
}
