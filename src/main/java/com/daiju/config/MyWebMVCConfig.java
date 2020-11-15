package com.daiju.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author WDY
 * @Date 2020-11-15 16:03
 * @Description TODO
 */
@Configuration
public class MyWebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addRedirectViewController("/index.html", "/");
        registry.addRedirectViewController("/index", "/");
        registry.addViewController("/success").setViewName("success");
        registry.addViewController("/manage").setViewName("manage");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyWebInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index","/","/user/login");
    }
}
