package com.sms.StudentManagementSystem;

import com.sms.StudentManagementSystem.Controllers.DepartmentController;
import com.sms.StudentManagementSystem.Controllers.MajorController;
import com.sms.StudentManagementSystem.Models.Department;
import com.sms.StudentManagementSystem.Models.Major;
import com.sms.StudentManagementSystem.Views.Auth.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
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
                majorController.deleteAll();
                departmentController.deleteAll();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            Department enee = new Department("E&EE", "Electrical and Electronic Engineering", 2, null, null);
            Department fnb = new Department("F&B", "Food and Beverage", 3, null, null);
            Department it = new Department("IT", "Information Technology", 5, null, null);
            if (departmentController.findById(enee.getId()) == null)
                departmentController.add(enee);

            if (departmentController.findById(fnb.getId()) == null)
                departmentController.add(fnb);

            if (departmentController.findById(it.getId()) == null)
                departmentController.add(it);

            Major mkt = new Major("MKT", "Marketing", fnb, null);
            Major cs = new Major("CS", "Computer Science", it, null);
            Major ne = new Major("NE", "Network Engineering", it, null);
            Major se = new Major("SE", "Software Engineering", it, null);

            if (majorController.findById(mkt.getId()) == null)
                majorController.add(mkt);

            if (majorController.findById(cs.getId()) == null)
                majorController.add(cs);

            if (majorController.findById(ne.getId()) == null)
                majorController.add(ne);

            if (majorController.findById(se.getId()) == null)
                majorController.add(se);

            System.out.println("Setup");
        };
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(App.class).headless(false).run(args);
        LoginForm form = context.getBean(LoginForm.class);
        form.setVisible(true);
    }
}
