package com.example.jwtappdemo.service.impl;

import com.example.jwtappdemo.model.Role;
import com.example.jwtappdemo.model.User;
import com.example.jwtappdemo.model.enums.Status;
import com.example.jwtappdemo.repository.RoleRepository;
import com.example.jwtappdemo.repository.UserRepository;
import com.example.jwtappdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = this.roleRepository.findByName("ROLE_USER");
        List<Role> userRoles=new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {

        List<User> allUsers = userRepository.findAll();
        log.info("IN getAll - {} users found", allUsers.size());
        return allUsers;
    }

    @Override
    public User findByUsername(String username) {
        User byUsername = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username {}", byUsername,username);
        return byUsername;
    }

    @Override
    public User findById(Long id) {
        User byId = userRepository.findById(id).orElse(null);
        if(byId==null){
            log.warn("IN findById - no user found by id {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id {}", byId, id);
        return byId;
    }

    @Override
    public void delete(Long id) {

        userRepository.deleteById(id);
        log.info("IN delete - user with id {} was deleted",id);
    }
}
