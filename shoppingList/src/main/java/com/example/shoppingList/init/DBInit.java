package com.example.shoppingList.init;


import com.example.shoppingList.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {


    private final UserService userService;

    public DBInit(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        userService.initializeUsersAndRoles();

    }
}
