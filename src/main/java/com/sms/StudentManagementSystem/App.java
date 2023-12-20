package com.sms.StudentManagementSystem;

import com.sms.StudentManagementSystem.Controllers.DepartmentController;
import com.sms.StudentManagementSystem.Controllers.MajorController;
import com.sms.StudentManagementSystem.Controllers.UserController;
import com.sms.StudentManagementSystem.Models.Department;
import com.sms.StudentManagementSystem.Models.Major;
import com.sms.StudentManagementSystem.Models.User;
import com.sms.StudentManagementSystem.Views.Auth.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication
public class App {
    @Autowired
    private UserController userController;

    @Autowired
    private DepartmentController departmentController;

    @Autowired
    private MajorController majorController;

    @Bean
    public CommandLineRunner welcome() {
        return args -> {
            System.out.println("Spring Boot application is running in both console and GUI.");
        };
    }

    @Bean
    public CommandLineRunner setup() {
        return args -> {
            try {
                if (majorController.count() == 0 && departmentController.count() == 0) {
                    departmentController.add(new Department("E&EE", "Electrical and Electronic Engineering", 2, null, null));
                    departmentController.add(new Department("F&B", "Food and Beverage", 3, null, null));
                    departmentController.add(new Department("IT", "Information Technology", 5, null, null));

                    majorController.add(new Major("MKT", "Marketing", departmentController.findById("F&B"), null));
                    majorController.add(new Major("CS", "Computer Science", departmentController.findById("IT"), null));
                    majorController.add(new Major("NE", "Network Engineering", departmentController.findById("IT"), null));
                    majorController.add(new Major("SE", "Software Engineering", departmentController.findById("IT"), null));
                }

                if (userController.getUserByEmail("admin@gmail.com") == null) {
                    LocalDate dob = LocalDate.of(2003, 8, 12);
                    User admin = new User("admin@gmail.com", "admin123", "Admin", 20, Date.valueOf(dob), "0123123123", "Normal", "Admin", null);
                    userController.add(admin);
                }

                if (userController.getUserByEmail("employee@gmail.com") == null) {
                    LocalDate dob = LocalDate.of(2003, 8, 12);
                    User admin = new User("employee@gmail.com", "employee123", "Employee", 20, Date.valueOf(dob), "0123123123", "Normal", "Employee", null);
                    userController.add(admin);
                }

                System.out.println("Database is ready.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        };
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(App.class).headless(false).run(args);
        LoginForm form = context.getBean(LoginForm.class);
        form.setVisible(true);
    }
}
