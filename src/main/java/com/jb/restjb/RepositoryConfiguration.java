//package com.jb.restjb;
//
//import java.util.Collections;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import com.jb.restjb.entities.Company;
//import com.jb.restjb.entities.Post;
//import com.jb.restjb.entities.User;
//
//@Configuration
//public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {
//
//    @Override
//    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//        config.exposeIdsFor(Company.class);
//        config.exposeIdsFor(Post.class);
//        config.exposeIdsFor(User.class);
//    }
//
//    // CORS
//    @Bean
//    public FilterRegistrationBean simpleCorsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        // *** URL below needs to match the Vue client URL and port ***
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
//        // *** URL below needs to match the React client URL and port ***
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//        config.setAllowedMethods(Collections.singletonList("*"));
//        config.setAllowedHeaders(Collections.singletonList("*"));
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }
//}
