package com.example.boardproject;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Board-proejct")
                        .description("대용량 트래픽을 고려해서 초당 1000tps 이상의 게시글 검색 API 성능을 목표로 하는 게시판 프로젝트.")
                        .version("1.0.0"));
    }
}