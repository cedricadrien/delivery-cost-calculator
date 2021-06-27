package com.mynt.calculator.deliverycost.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.POST, getResponseMessageList());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Parcel Delivery Cost Calculator API")
                .build();
    }

    private List<ResponseMessage> getResponseMessageList() {
        return Arrays.asList(
                new ResponseMessageBuilder().code(400).message("Bad Request").build(),
                new ResponseMessageBuilder().code(404).message("Not Found").build(),
                new ResponseMessageBuilder().code(422).message("Business logic error").build(),
                new ResponseMessageBuilder().code(500).message("Internal Server Error").build());
    }
}
