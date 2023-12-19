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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;

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
    @Autowired
    private StudentController studentController;
    @Autowired
    private DepartmentController departmentController;
    @Autowired
    private MajorController majorController;
    @Autowired
    private CertificateController certificateController;

    public boolean login(String email, String password, LoginForm loginForm) {
        User user = getUserByEmail(email);

        if (user == null || !UtilsController.checkPassword(password, user.getPassword())) {
            JOptionPane.showMessageDialog(null, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (user.getStatus().equals("Locked")) {
            JOptionPane.showMessageDialog(null, "Your account is locked", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (user.getRole().equals("Admin")) {
            userPanel.setUserController(this);
            userPanel.setMainForm(mainForm);
            userPanel.loadTable();
        }

        profilePanel.setUserController(this);
        profilePanel.setMainForm(mainForm);
        profilePanel.setUser(user);

        mainForm.setUser(user);
        mainForm.setUserController(this);
        mainForm.setLoginForm(loginForm);
        mainForm.setStudentController(studentController);
        mainForm.setDepartmentController(departmentController);
        mainForm.setMajorController(majorController);
        mainForm.setCertificateController(certificateController);

        JPanel panelBody = mainForm.getPanelBody();
        panelBody.add(studentPanel, "StudentPanel", 0);
        studentPanel.setStudentController(studentController);
        studentPanel.loadTable();
        studentPanel.setMainForm(mainForm);
        studentPanel.setUser(user);
        panelBody.add(profilePanel, "ProfilePanel", 1);
        panelBody.add(userPanel, "UserPanel", 2);

        LoginHistory loginHistory = new LoginHistory(null, new Timestamp(System.currentTimeMillis()), user);
        loginHistoryController.add(loginHistory);

        mainForm.setVisible(true);
        return true;
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public Iterable<User> searchByName(String text) {
        return userRepository.findByNameContaining(text);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean add(User user) {
        if (isInvalidUser(user) || isInvalidPassword(user.getPassword())) return false;

        if (getUserByEmail(user.getEmail()) != null) {
            JOptionPane.showMessageDialog(null, "Email already exists", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        user.setPassword(UtilsController.hashPassword(user.getPassword()));

        try {
            userRepository.save(user);
            UtilsController.saveAvatar(user.getEmail(), null);
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

    public boolean changePassword(String email, String newPassword) {
        User user = getUserByEmail(email);

        if (isInvalidPassword(newPassword)) return false;

        if (user == null) {
            JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
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

    @Transactional
    public boolean delete(User user) {
        try {
            if (!loginHistoryController.deleteByUser(user.getEmail())) {
                JOptionPane.showMessageDialog(null, "Error: Cannot delete login history", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            userRepository.delete(user);

            if (!UtilsController.removeUserFolder(user.getEmail())) {
                JOptionPane.showMessageDialog(null, "Error: Cannot delete user folder", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            JOptionPane.showMessageDialog(null, "User deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean deleteAll() {
        try {
            if (!loginHistoryController.deleteAll()) {
                JOptionPane.showMessageDialog(null, "Error: Cannot delete login history", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            userRepository.deleteAll();
            commit();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void commit() {
        userRepository.flush();
        loginHistoryController.commit();
    }

    public boolean exportFile(String path) {
        try {
            UtilsController<User> utilsController = new UtilsController<>();
            List<User> users = (List<User>) getAll();

            if (path.contains(".xlsx"))
                utilsController.exportToExcel(users, path);
            else if (path.contains(".csv"))
                utilsController.exportToCSV(users, path);
            else {
                JOptionPane.showMessageDialog(null, "Error: Invalid file extension", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean importFile(String path) {
        try {
            UtilsController<User> utilsController = new UtilsController<>();
            List<User> users;

            if (path.contains(".xlsx"))
                users = utilsController.importFromExcel(path, User.class);
            else if (path.contains(".csv"))
                users = utilsController.importFromCSV(path, User.class);
            else {
                JOptionPane.showMessageDialog(null, "Error: Invalid file extension", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (users == null || users.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No records found", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (deleteAll()) {
                for (User user : users) {
                    user.setPassword(user.getPhone());
                    add(user);
                }
            }

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
                user.getAge() < 0) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (!UtilsController.EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            JOptionPane.showMessageDialog(null, "Invalid email", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private boolean isInvalidPassword(String password) {
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (!UtilsController.PASSWORD_PATTERN.matcher(password).matches()) {
            String message = "Password must be at least 8 characters including (a-z), (A-Z), (0-9)\n\nEnsure Unikey or similar software is turned off before entering your password";
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

}
