package com.example.kafka.model;

import java.io.Serializable;

public class BODto implements Serializable {

    private String email;
    private String password;

    public BODto() {
    }

    public BODto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public BODto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BODto setPassword(String password) {
        this.password = password;
        return this;
    }
}
