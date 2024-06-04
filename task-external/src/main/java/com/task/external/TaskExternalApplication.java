package com.task.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * packageName    : com.task.common
 * fileName       : TaskExternalApplication
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@EnableJpaAuditing
@EntityScan("com.task.common")
@EnableJpaRepositories("com.task.external")
@SpringBootApplication(scanBasePackages = {"com.task"})
public class TaskExternalApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskExternalApplication.class);
    }
}
