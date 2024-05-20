package com.group2.kgrill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KgrillApplication {

	public static void main(String[] args) {
		SpringApplication.run(KgrillApplication.class, args);
	}

}
