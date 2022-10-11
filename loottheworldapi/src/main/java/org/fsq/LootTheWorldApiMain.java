package org.fsq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"fsq.core.data.repository"})
@EntityScan(basePackages = "fsq.core.entity")
@ComponentScan({"org.fsq.controller","fsq.core.entity.item", "org.fsq.security"})
public class LootTheWorldApiMain {
    public static void main(String[] args) {
        SpringApplication.run(LootTheWorldApiMain.class, args);
    }
}
