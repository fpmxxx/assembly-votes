package com.assemblyvotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.assemblyvotes.config.ConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class AssemblyVotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssemblyVotesApplication.class, args);
	}

}
