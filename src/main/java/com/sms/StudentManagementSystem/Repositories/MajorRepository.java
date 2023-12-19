package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, String> {

    Iterable<Major> findAllByDepartmentId(String departmentId);

    Major findByName(String name);
}
