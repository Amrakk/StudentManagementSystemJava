package com.sms.StudentManagementSystem;

import com.sms.StudentManagementSystem.Views.Auth.LoginForm;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    @Bean
    public CommandLineRunner welcome() {
        return args -> {
            System.out.println("Spring Boot application is running in both console and GUI.");
        };
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(App.class).headless(false).run(args);
        LoginForm form = context.getBean(LoginForm.class);
    }
}
