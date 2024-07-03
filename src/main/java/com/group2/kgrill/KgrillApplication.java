package com.group2.kgrill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.swd392.group2.kgrill_service"})
public class KgrillApplication {

    public static void main(String[] args) {
        SpringApplication.run(KgrillApplication.class, args);
    }

}
