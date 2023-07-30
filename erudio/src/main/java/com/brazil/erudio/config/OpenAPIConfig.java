package com.brazil.erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    OpenAPI customOpenAPI() {
        String url = "https://pub.erudio.com.br/meus-cursos";

        License license = new License()
                .name("Apache 2.0")
                .url(url);

        Info info = new Info()
                .title("Hello Swagger OpenAPI")
                .version("v1")
                .description("Some description about your API.")
                .termsOfService(url)
                .license(license);

        return new OpenAPI()
                .info(info);
    }

}
