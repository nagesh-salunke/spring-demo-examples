package com.springdemo.issuedashboard.issuedashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GithubProperties.class)
public class IssueDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueDashboardApplication.class, args);
	}
}
