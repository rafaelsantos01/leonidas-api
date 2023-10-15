package com.spantanTech.leonidas.config;

import io.swagger.models.Info;
import io.swagger.models.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Leonidas API",
                        "API de configuração para a aplicação Leonidas",
                        "1.0",
                        "https://example.com/terms-of-service",
                        new Contact("SpartanTech Solutions", "https://spantan.com", "contato@spantan.com"),
                        "Apache 2.0",
                        "https://www.apache.org/licenses/LICENSE-2.0.html",
                        Arrays.asList(new VendorExtension[]{})
                ));


    }
}
