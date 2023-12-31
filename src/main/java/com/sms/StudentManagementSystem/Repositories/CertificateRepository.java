package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, String> {

    Optional<Certificate> findById(String id);
    List<Certificate> findAllByStudentId(String id);

    void deleteAllByStudentId(String id);
}
