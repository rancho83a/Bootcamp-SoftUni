package com.example.zuulGate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
//@EnableBinding(ZuulGateApplication.Channels.class)
public class ZuulGateApplication {


	@RestController
	public class HomeController {
		@GetMapping("/home")
		public String home(){
			return "my home: rancho 83A";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulGateApplication.class, args);
	}





}
