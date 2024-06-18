package com.dreamhire.job_profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JobProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobProfileApplication.class, args);
	}

}
