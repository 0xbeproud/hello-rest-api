package com.weproud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author Logan.k
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.weproud.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "Hello Rest API server",
                "Spring Boot REST API for Hello Rest API",
                "1.0",
                "Terms of service",
                null,
                "",
                "",
                Collections.emptyList());
    }
}