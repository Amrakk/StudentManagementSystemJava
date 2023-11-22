package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.LoginHistory;
import com.sms.StudentManagementSystem.Repositories.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

}
