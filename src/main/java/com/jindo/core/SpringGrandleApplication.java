package com.jindo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * [Spring Boot Security Auto-Configuration]
 * https://www.baeldung.com/spring-boot-security-autoconfiguration
 *
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringGrandleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGrandleApplication.class, args);
    }

}
