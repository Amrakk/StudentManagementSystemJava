package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.Certificate;
import com.sms.StudentManagementSystem.Models.Student;
import com.sms.StudentManagementSystem.Repositories.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.List;

@Controller
public class CertificateController {

    @Autowired
    private CertificateRepository certRepository;

    public List<Certificate> getByStudentId(String id) {
        return certRepository.findAllByStudentId(id);

    }

    public List<Certificate> getAll(){
        return certRepository.findAll();
    }

    public Certificate getById(String id) { return certRepository.findById(id).orElse(null); }

    public boolean add(Certificate certificate){
        if (isInvalidCertificate(certificate)) return false;
        try {
            certRepository.save(certificate);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean isInvalidCertificate(Certificate certificate) {
        if (certificate == null) {
            JOptionPane.showMessageDialog(null, "Certificate is null", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (certificate.getId().isEmpty() ||
                certificate.getTitle().isEmpty() ||
                certificate.getDescription().isEmpty() ||
                certificate.getExpiredDate() == null ||
                certificate.getIssuedDate() == null ||
                certificate.getOrganization().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    public boolean exportFile(String path) {
        try {
            UtilsController<Certificate> utilsController = new UtilsController<>();
            List<Certificate> certificates = (List<Certificate>) getAll();

            if (path.contains(".xlsx"))
                utilsController.exportToExcel(certificates, path);
            else if (path.contains(".csv"))
                utilsController.exportToCSV(certificates, path);
            else {
                JOptionPane.showMessageDialog(null, "Error: Invalid file extension", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean importFile(String path) {
        try {
            UtilsController<Certificate> utilsController = new UtilsController<>();
            List<Certificate> certificates;

            if (path.contains(".xlsx"))
                certificates = utilsController.importFromExcel(path, Certificate.class);
            else if (path.contains(".csv"))
                certificates = utilsController.importFromCSV(path, Certificate.class);
            else {
                JOptionPane.showMessageDialog(null, "Error: Invalid file extension", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (certificates == null || certificates.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No records found", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void update(Certificate certificate) {
        if (isInvalidCertificate(certificate)){}

        try {
            certRepository.save(certificate);
            JOptionPane.showMessageDialog(null, "Certificate updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete(Certificate certificate) {
        try{
            certRepository.deleteById(certificate.getId());
            JOptionPane.showMessageDialog(null, "Certificate deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean deleteByStudentId(String id) {
        try {
            certRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
