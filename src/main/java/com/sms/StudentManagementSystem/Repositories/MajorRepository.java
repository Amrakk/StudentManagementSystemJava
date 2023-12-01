package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.Major;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends CrudRepository<Major, String> {

    Iterable<Major> findAllByDepartmentId(String departmentId);

    Major findByName(String name);
}
