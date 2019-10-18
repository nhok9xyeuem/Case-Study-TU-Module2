package com.codegym.casestudymovieappdemo;

import com.codegym.casestudymovieappdemo.formatter.DirectorFormatter;
import com.codegym.casestudymovieappdemo.service.DirectorService;
import com.codegym.casestudymovieappdemo.service.DirectorServiceImpl;
import com.codegym.casestudymovieappdemo.service.MovieService;
import com.codegym.casestudymovieappdemo.service.MovieServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CaseStudyMovieAppDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaseStudyMovieAppDemoApplication.class, args);
    }

    @Bean
    public MovieService movieService() {
        return new MovieServiceImpl();
    }

    @Bean
    public DirectorService directorService() {
        return new DirectorServiceImpl();
    }

    @Configuration
    class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

        private ApplicationContext appContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            appContext = applicationContext;
        }

        @Override
        public void addFormatters(FormatterRegistry registry) {
            DirectorService directorService = appContext.getBean(DirectorService.class);
            Formatter directorFormatter = new DirectorFormatter(directorService);
            registry.addFormatter(directorFormatter);
        }

    }
}
