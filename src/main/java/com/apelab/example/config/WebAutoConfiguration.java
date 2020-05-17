package com.apelab.example.config;

import com.apelab.example.config.log.EmbeddedTomcatCustomizer;
import com.apelab.example.filter.TenantFilter;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
@Configuration
public class WebAutoConfiguration implements WebMvcConfigurer {

  @Bean // access-log
  public WebServerFactoryCustomizer tomcatAccessLogCustomizer() {
    return new EmbeddedTomcatCustomizer(true /*logResponseSize or not*/);
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean(){
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new TenantFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setOrder(1);
    return filterRegistrationBean;
  }
}
