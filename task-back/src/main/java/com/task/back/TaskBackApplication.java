package com.task.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EntityScan("com.task.common")
@EnableJpaRepositories("com.task.back")
@SpringBootApplication(scanBasePackages = {"com.task"})
public class TaskBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskBackApplication.class);
    }
}
