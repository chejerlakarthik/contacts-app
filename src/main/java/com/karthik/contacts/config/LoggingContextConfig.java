package com.karthik.contacts.config;

import com.karthik.contacts.filter.LoggingMDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingContextConfig {

    private static final String CORRELATION_ID_HEADER_NAME = "x-request-id";

    @Bean
    public FilterRegistrationBean loggingContextConfiguration() {
        final FilterRegistrationBean<LoggingMDCFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        final LoggingMDCFilter mdcFilter = new LoggingMDCFilter();
        filterRegistrationBean.setFilter(mdcFilter);
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
