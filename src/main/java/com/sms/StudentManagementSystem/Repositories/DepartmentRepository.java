package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {
    Department findByName(String name);
}
