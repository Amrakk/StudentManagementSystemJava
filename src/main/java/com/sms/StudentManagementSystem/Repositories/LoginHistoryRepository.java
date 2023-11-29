package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    void deleteAllByUserEmail(String email);
}
