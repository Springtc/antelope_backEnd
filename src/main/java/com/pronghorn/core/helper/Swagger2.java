package com.pronghorn.core.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 23:07 2019-06-03
 * @Modified By: 23:07 2019-06-03
 */
@Configuration
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.pronghorn.coffee"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("叉角羚咖啡api文档")
                .description("简单优雅的restful风格，http://www.pronghorn.com")
                .termsOfServiceUrl("http://www.pronghorn.com")
                .version("1.0")
                .build();
    }
}
