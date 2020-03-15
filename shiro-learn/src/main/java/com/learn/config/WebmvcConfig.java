package com.learn.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author
 * @description WebmvcConfig
 * @date 2019/9/5 15:27
 **/
@Configuration
public class WebmvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //super.addResourceHandlers(registry);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(resourceConverter());
        converters.add(stringConverter());
        converters.add(jacksonConverter());
    }

    @Bean
    MappingJackson2HttpMessageConverter jacksonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = ObjectMapperHolder.getInstance().getNewMapper();
        converter.setObjectMapper(mapper);
        return converter;
    }

    @Bean
    StringHttpMessageConverter stringConverter() {
        return new StringHttpMessageConverter();
    }

    @Bean
    ResourceHttpMessageConverter resourceConverter() {
        return new ResourceHttpMessageConverter();
    }
}
