package org.example.pw7;

import org.example.pw7.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
public class Pw7Application {

    public static void main(String[] args) {
        SpringApplication.run(Pw7Application.class, args);
    }

}
