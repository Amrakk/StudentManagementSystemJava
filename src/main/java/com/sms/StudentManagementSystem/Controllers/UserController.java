package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.LoginHistory;
import com.sms.StudentManagementSystem.Models.User;
import com.sms.StudentManagementSystem.Repositories.UserRepository;
import com.sms.StudentManagementSystem.Views.Admin.UserPanel;
import com.sms.StudentManagementSystem.Views.Auth.LoginForm;
import com.sms.StudentManagementSystem.Views.MainForm;
import com.sms.StudentManagementSystem.Views.ProfilePanel;
import com.sms.StudentManagementSystem.Views.Student.StudentPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.sql.Timestamp;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginHistoryController loginHistoryController;

    @Autowired
    private MainForm mainForm;
    @Autowired
    private StudentPanel studentPanel;
    @Autowired
    private ProfilePanel profilePanel;
    @Autowired
    private UserPanel userPanel;

    public boolean login(String email, String password, LoginForm loginForm) {
        User user = getUserByEmail(email);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            JOptionPane.showMessageDialog(null, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (user.getStatus().equals("Locked")) {
            JOptionPane.showMessageDialog(null, "Your account is locked", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        profilePanel.setUserController(this);
        profilePanel.setMainForm(mainForm);
        profilePanel.setUser(user);

        mainForm.setUser(user);
        mainForm.setLoginForm(loginForm);

        JPanel panelBody = mainForm.getPanelBody();
        panelBody.add(studentPanel, "StudentPanel", 0);
        panelBody.add(profilePanel, "ProfilePanel", 1);
        panelBody.add(userPanel, "UserPanel", 2);

        LoginHistory loginHistory = new LoginHistory(null, new Timestamp(System.currentTimeMillis()), user);
        loginHistoryController.add(loginHistory);

        mainForm.setVisible(true);
        return true;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean add(User user) {
        if (isInvalidUser(user)) return false;

        user.setPassword(UtilsController.hashPassword(user.getPassword()));

        try {
            userRepository.save(user);
            JOptionPane.showMessageDialog(null, "User added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean update(User user) {
        if (isInvalidUser(user)) return false;

        try {
            userRepository.save(user);
            JOptionPane.showMessageDialog(null, "User updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean changePassword(String email, String newPassword, String confirmPassword) {
        User user = getUserByEmail(email);
        if (user == null) {
            JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!UtilsController.PASSWORD_PATTERN.matcher(newPassword).matches()) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters including (a-z), (A-Z), (0-9)", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "New password and confirm password must be the same", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        user.setPassword(UtilsController.hashPassword(newPassword));

        try {
            userRepository.save(user);
            JOptionPane.showMessageDialog(null, "Password changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    private boolean isInvalidUser(User user) {
        if (user == null) {
            JOptionPane.showMessageDialog(null, "User is null", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (user.getEmail().isEmpty() ||
                user.getName().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getRole().isEmpty() ||
                user.getStatus().isEmpty() ||
                user.getDob() == null ||
                user.getAge() <= 0) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (!UtilsController.EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            JOptionPane.showMessageDialog(null, "Invalid email", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (!UtilsController.PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters including (a-z), (A-Z), (0-9)", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (getUserByEmail(user.getEmail()) != null) {
            JOptionPane.showMessageDialog(null, "Email already exists", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    public void deleteByEmail(String email) {
        try {
            userRepository.deleteByEmail(email);
            JOptionPane.showMessageDialog(null, "User deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
