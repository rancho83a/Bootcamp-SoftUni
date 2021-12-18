package com.example.shoppingList.model.binding;


import com.example.shoppingList.model.validator.UniqueEmail;
import com.example.shoppingList.model.validator.UniqueUserName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserRegistrationBindingModel {

    @Email
    @NotBlank
    @UniqueEmail
    private String email;

    @Size(min = 3, max = 20, message = "Password size must be between 3 and 20 symbols")
    private String password;

    private String confirmPassword;


    @Size(min = 3, max = 20, message = "Username size must be between 3 and 20 symbols")
    @UniqueUserName
    private String username;

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

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
