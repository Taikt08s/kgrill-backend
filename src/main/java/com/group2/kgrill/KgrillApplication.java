package com.group2.kgrill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"com.group2.kgrill", "com.swd392.group2.kgrill_service"})
public class KgrillApplication {

    public static void main(String[] args) {
        SpringApplication.run(KgrillApplication.class, args);
    }

}
