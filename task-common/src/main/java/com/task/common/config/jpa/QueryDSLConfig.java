package com.task.common.config.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.task.common.config.querydsl
 * fileName       : QueryDSLConfig
 * author         : limsooyoung
 * date           : 6/7/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/7/24        limsooyoung       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class QueryDSLConfig {
    private final EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
