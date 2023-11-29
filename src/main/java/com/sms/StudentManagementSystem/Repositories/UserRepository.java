package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    Iterable<User> findByRole(String role);

    Iterable<User> searchByName(String text);

    Iterable<User> findByAgeGreaterThanEqualAndRoleAndStatus(int age, String role, String status);
}
