package com.group2.kgrill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackages = "com.swd392.group2.kgrill_model.repository")
@EntityScan(basePackages = "com.swd392.group2.kgrill_model.model")
@ComponentScan(basePackages = {"com.group2.kgrill", "com.swd392.group2.kgrill_model"})
public class KgrillApplication {

	public static void main(String[] args) {
		SpringApplication.run(KgrillApplication.class, args);
	}

}
