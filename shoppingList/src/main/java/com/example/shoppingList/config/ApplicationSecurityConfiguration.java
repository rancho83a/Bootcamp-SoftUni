package com.example.shoppingList.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;

  public ApplicationSecurityConfiguration(
      UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
          authorizeRequests().
          requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().

          antMatchers("/", "/users/login", "/users/register").permitAll().
          antMatchers("/img/**").permitAll().

          antMatchers("/**").authenticated().
        and().
          formLogin().
          loginPage("/users/login").

          usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).

          passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).

            defaultSuccessUrl("/home").

            // the place where I should land if the login is NOT successful
          failureForwardUrl("/users/login-error").
        and().
          logout().
          // This is the URL which spring will implement for me and will log the user out.
          logoutUrl("/users/logout").
          // where to go after the logout.
          logoutSuccessUrl("/").
          // remove the session from server
          invalidateHttpSession(true).
        //delete the cookie that references my session
          deleteCookies("JSESSIONID");

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.
        userDetailsService(userDetailsService).
        passwordEncoder(passwordEncoder);
  }
}
