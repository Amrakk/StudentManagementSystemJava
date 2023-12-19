package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Iterable<Student> findByGenderAndEduTypeAndMajorIdAndDepartmentId(String gender, String eduType, String majorId, String departmentId);

    Iterable<Student> findByNameContaining(String text);

}
