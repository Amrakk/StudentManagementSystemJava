package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.Major;
import com.sms.StudentManagementSystem.Repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MajorController {
    @Autowired
    private MajorRepository majorRepository;

    public void add(Major major) {
        majorRepository.save(major);
    }

    public Major findById(String id) {
        return majorRepository.findById(id).orElse(null);
    }

    public void deleteAll() {
        majorRepository.deleteAll();
    }

    public void deleteById(String id) {
        majorRepository.deleteById(id);
    }

}
