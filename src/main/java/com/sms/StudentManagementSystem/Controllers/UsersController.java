package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.User;
import com.sms.StudentManagementSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    public void login(String email, String password) {
        Iterable<User> users = userRepository.findAll();
        users.forEach(System.out::println);
        System.out.println("Email: " + email + "\nPassword: " + password);
    }

}
