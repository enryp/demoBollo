package com.aruba.demoBollo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
        	.and()
        	.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/stawapp/rest/veicoli")
            //.hasAuthority("springboot_user")
            //.authenticated()

            //.authenticated()
            //.hasAuthority("SCOPE_springboot_user")
            //.anyRequest()
            .authenticated()
//            .antMatchers(HttpMethod.GET, "/stawapp/rest/**")
//            .hasAuthority("springboot_user")
//            .antMatchers(HttpMethod.POST, "/stawapp/rest")
////            .hasAuthority("springboot_user")
//            .anyRequest()
//            .authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt();
        return http.build();
    }
}
