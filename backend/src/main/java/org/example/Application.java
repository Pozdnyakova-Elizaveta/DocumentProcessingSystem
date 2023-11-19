package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Класс для запуска приложения
 */
@SpringBootApplication
@EntityScan("org.example")
@EnableJpaRepositories("org.example.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
