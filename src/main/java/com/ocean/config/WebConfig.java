package com.ocean.config;

import com.ocean.converter.StringToDateConverter;
import com.ocean.converter.TestConverter;
import com.ocean.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**").
                excludePathPatterns("/login");
        //多个拦截器
//        registry.addInterceptor(loginInterceptor());
        super.addInterceptors(registry);
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public StringToDateConverter stringToDateConverter() {
        return new StringToDateConverter();
    }

    @Resource
    TestConverter converter;
    // 注册转换器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToDateConverter());
        registry.addConverter(converter);
    }
}
