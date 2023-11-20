package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.User;
import com.sms.StudentManagementSystem.Repositories.UserRepository;
import com.sms.StudentManagementSystem.Views.Auth.LoginForm;
import com.sms.StudentManagementSystem.Views.MainForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    public boolean login(String email, String password, LoginForm loginForm) {
        User user = getUserByEmail(email);

//        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
//            JOptionPane.showMessageDialog(null, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//
//        if (user.getStatus().equals("Locked")) {
//            JOptionPane.showMessageDialog(null, "Your account is locked", "Error", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }

        MainForm mainForm = new MainForm();
        mainForm.setUser(user);
        mainForm.setLoginForm(loginForm);

        mainForm.setVisible(true);
        return true;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
