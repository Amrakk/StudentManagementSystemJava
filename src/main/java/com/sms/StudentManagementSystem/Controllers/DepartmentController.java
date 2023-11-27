package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.Department;
import com.sms.StudentManagementSystem.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Iterable<Department> findAll() {
        return departmentRepository.findAll();
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

    public void deleteAll() {
        departmentRepository.deleteAll();
    }

    public void delete(Department department) {
        departmentRepository.delete(department);
    }
}
