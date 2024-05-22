package com.bank.bank.util;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<PortBasedRequestFilter> adminPortFilter() {
        FilterRegistrationBean<PortBasedRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new PortBasedRequestFilter());
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }
}

