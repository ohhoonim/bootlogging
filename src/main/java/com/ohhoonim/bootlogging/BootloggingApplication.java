package com.ohhoonim.bootlogging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BootloggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootloggingApplication.class, args);
	}

	@Value("${spring.profiles.active}")
	private String profiles;

	@Bean
	CommandLineRunner startBoot() {
		return args -> {
			log.info("hi, there! this is logging. Profiles are " + profiles);
			log.error("Oops, something went wrong");

		};
	}
}
