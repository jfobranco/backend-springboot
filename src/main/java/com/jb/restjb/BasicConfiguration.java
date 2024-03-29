package com.jb.restjb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .httpBasic().and().authorizeRequests()
               .anyRequest().authenticated()
               .and().csrf().disable();
//                .antMatchers("/*").permitAll()
//                .antMatchers("/img/**").permitAll()
//                .anyRequest().authenticated();
    }
}