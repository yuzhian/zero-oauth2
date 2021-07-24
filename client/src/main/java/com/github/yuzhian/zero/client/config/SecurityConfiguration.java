package com.github.yuzhian.zero.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated()
                .and().oauth2Login().userInfoEndpoint()
                .and().successHandler((httpServletRequest, httpServletResponse, authentication) -> httpServletResponse.sendRedirect("/index"))
        ;
    }
}
