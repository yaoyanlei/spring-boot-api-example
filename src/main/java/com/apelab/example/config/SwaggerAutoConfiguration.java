package com.apelab.example.config;

import io.swagger.annotations.ApiOperation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 * TODO REPLACE_ME 替换swagger info信息
 */
@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("apelab-api")
        .pathProvider(apiPathProvider()).select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("SAMPLE SERVICE API").description("")
        .termsOfServiceUrl("www.apelab.com")
        .contact(new Contact("apelab", "www.apelab.com", "xxx@apelab.com"))
        .license("Internal Only").licenseUrl("www.apelab.com").version("0.0.1").build();
  }

  private PathProvider apiPathProvider() {
    return new AbstractPathProvider() {
      @Override
      protected String applicationPath() {
        return "";
      }

      @Override
      protected String getDocumentationPath() {
        return "";
      }
    };
  }
}
