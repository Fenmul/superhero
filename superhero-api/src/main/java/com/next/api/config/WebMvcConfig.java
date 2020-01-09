package com.next.api.config;


import com.next.api.config.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 添加静态资源--过滤swagger-api (开源的在线API文档)
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * macos: file:/temp/
         * win:	  file:C:/temp/
         */
        registry.addResourceHandler("/**")
                .addResourceLocations("file:/Users/fox/Documents/shfile/")	// 映射tomcat虚拟目录
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    @Bean
    public UserInterceptor createUserInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(createUserInterceptor())
                .addPathPatterns("/user/uploadFace")
                .addPathPatterns("/user/modifyUserinfo");
    }
}
