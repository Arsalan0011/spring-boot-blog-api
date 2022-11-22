package com.blogapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@EnableSwagger2
@EnableWebMvc
@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER="Authorization";
    private ApiKey apiKeys(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"Header");
    }
    private List<SecurityContext> securityContexts(){
        return Arrays.asList( SecurityContext.builder().securityReferences(securityReference()).build());
    }
    private List<SecurityReference> securityReference(){
        AuthorizationScope scope=new AuthorizationScope("global","acess everything");
        return  Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[]{scope}));
    }
    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                        .securityContexts(securityContexts())
                                .securitySchemes(Arrays.asList(apiKeys())).
                select().
                apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo getInfo(){
        return new ApiInfo("Backend Course","This project is developed by Arasalan","1.0","Terms of service",
                new Contact("Arsalan",
                "https://www.arsalan.com","arsalan@gmail.com"),null,null, Collections.emptyList());
    }
}
