package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.LoginHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Long> {
    void deleteAllByUserEmail(String email);
}
