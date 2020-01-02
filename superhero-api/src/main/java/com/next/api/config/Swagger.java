package com.next.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {

    // http://localhost:8080/swagger-ui.html    原访问路径
    // http://localhost:8080/doc.html           界面美化版访问路径

    /**
     * 配置 Swagger2 的一些基本内容以及扫描路径
     * @return Swagger 基本配置
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.next.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("电影预告 API 接口文档")
                .contact(new Contact("lee", "www.baidu.com", "1159542665@qq.com"))
                .description("电影预告文档的基本信息")
                .version("v1.0")
                .termsOfServiceUrl("https://ke.qq.com/course/379043")
                .build();
    }
}
