package br.com.hello.world.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.hello.world"))
                .paths(PathSelectors.any())
                .build().apiInfo(metaData());
    }

    private ApiInfo metaData() {

        return new ApiInfo(
                "Hello World API",
                "Hello World API",
                "1.0",
                null,
                new Contact("Caiuzu", "https://github.com/Caiuzu", ""),
                "License Version 1.0",
                null,
                Collections.emptyList());
    }

}
