package com.pronghorn.coffee;

import com.pronghorn.core.helper.SpringBeanHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.pronghorn.coffee")
public class CoffeeApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CoffeeApplication.class, args);
        SpringBeanHelper.setApplicationContext(context);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CoffeeApplication.class);
    }

    //显示声明CommonsMultipartResolver为mutipartResolver
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(1048576);
        return multipartResolver;
    }
}
