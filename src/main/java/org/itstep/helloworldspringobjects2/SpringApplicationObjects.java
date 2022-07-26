package org.itstep.helloworldspringobjects2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringApplicationObjects {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationObjects.class, args);
    }

}
