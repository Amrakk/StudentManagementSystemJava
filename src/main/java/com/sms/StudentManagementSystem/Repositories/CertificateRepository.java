package com.sms.StudentManagementSystem.Repositories;

import com.sms.StudentManagementSystem.Models.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends CrudRepository<Certificate, String> {
}
