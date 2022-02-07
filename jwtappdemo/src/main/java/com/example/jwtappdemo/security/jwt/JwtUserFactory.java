package com.example.jwtappdemo.security.jwt;

import com.example.jwtappdemo.model.Role;
import com.example.jwtappdemo.model.User;
import com.example.jwtappdemo.model.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthority(user.getRoles()),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthority(List<Role> roles){
        return roles.stream()
                .map(role-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
