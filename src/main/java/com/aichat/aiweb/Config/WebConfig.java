package com.aichat.aiweb.Config;

import com.aichat.aiweb.Interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckInterceptor Lci;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(Lci).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
