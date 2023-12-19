package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.Department;
import com.sms.StudentManagementSystem.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department getByName(String name) {
        return departmentRepository.findByName(name);
    }

    public int count() {
        return (int) departmentRepository.count();
    }

    public void add(Department department) {
        departmentRepository.save(department);
    }

    public Department findById(String id) {
        return departmentRepository.findById(id).orElse(null);
    }

}
