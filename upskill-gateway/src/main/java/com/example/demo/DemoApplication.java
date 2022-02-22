package com.example.demo;

import com.example.demo.dto.UserLoginRequestDto;
import com.example.demo.service.JWTService;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Optional;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableZuulProxy
public class DemoApplication {

	private final JWTService jwtService;

	public DemoApplication(JWTService jwtService) {
		this.jwtService = jwtService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostMapping("/auth/token")
	public String login() {
		UserLoginRequestDto dto = new UserLoginRequestDto();

		String cachedHeaderToken = this.jwtService.getCachedHeaderToken(dto).orElse(null);
		jwtService.cacheIssuedHeaderToken(dto, cachedHeaderToken);

		return dto.getUsername();
	}


	@GetMapping("/hello")
	public String hello()
	{
		return "Spring is here!";
	}


}