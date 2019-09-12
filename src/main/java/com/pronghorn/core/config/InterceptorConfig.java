package com.pronghorn.core.config;

import com.pronghorn.core.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 王广开
 * @date 2019-06-18
 */
@Configuration
public class InterceptorConfig {
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        *//*registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/swagger-resources/**","/webjars/**","/v2/**", "/swagger-ui.html/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录*//*
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }*/
}
