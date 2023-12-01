package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.LoginHistory;
import com.sms.StudentManagementSystem.Repositories.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class LoginHistoryController {

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    public LoginHistory add(LoginHistory loginHistory) {
        return loginHistoryRepository.save(loginHistory);
    }

    public Iterable<LoginHistory> getAll() {
        return loginHistoryRepository.findAll();
    }

    @Transactional
    public boolean deleteByUser(String email) {
        try {
            loginHistoryRepository.deleteAllByUserEmail(email);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteAll() {
        try {
            loginHistoryRepository.deleteAll();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Transactional
    public void commit() {
        loginHistoryRepository.flush();
    }

}
