package com.example.shoppingList.init;


import com.example.shoppingList.service.CategoryService;
import com.example.shoppingList.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final CategoryService categoryService;

    public DBInit(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public void run(String... args) throws Exception {

        userService.initializeUsersAndRoles();
        categoryService.initializeCategory();


    }
}
