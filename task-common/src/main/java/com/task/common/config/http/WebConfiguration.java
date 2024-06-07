package com.task.common.config.http;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName    : com.task.common.config.http
 * fileName       : WebConfiguration
 * author         : limsooyoung
 * date           : 6/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/4/24        limsooyoung       최초 생성
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * swagger 설정
     * */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Lecture Project")
                        .description("Lecture API Specification")
                        .version("v.1.0.0"));
    }
}
