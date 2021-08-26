package com.gwanho.notice;

import com.gwanho.notice.login.LoginCheckIntercepter;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckIntercepter())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/*.co","/board","/login","/logout","/member","/board/search");
    }
    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }
}
