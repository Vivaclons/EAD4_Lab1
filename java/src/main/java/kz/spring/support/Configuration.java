package kz.spring.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

@org.springframework.context.annotation.Configuration
@ComponentScan("kz.spring.support")
@PropertySource("/application.properties")
@EnableJpaRepositories(basePackages = "kz.spring.support")
public class Configuration {
    @Bean
    public Scanner getScannerBean() {
        return new Scanner(System.in);
    }

    @Bean
    public BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
