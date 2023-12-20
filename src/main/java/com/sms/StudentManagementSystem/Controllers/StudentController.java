package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.Student;
import com.sms.StudentManagementSystem.Repositories.StudentRepository;
import com.sms.StudentManagementSystem.Views.Student.StudentPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.List;

@Controller
public class StudentController {

    // TODO: Add, Delete, Get(by id), GetByName, GetByCriteria(gender, eduType, department, major), Get(all), Update, TotalCount,

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CertificateController certificateController;

    @Autowired
    private StudentPanel studentPanel;

    public Iterable<Student> getByName(String text) {
        return studentRepository.findByNameContaining(text);
    }

    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public Iterable<Student> GetByCriteria(String gender, String eduType, String majorId, String departmentId) {
        return studentRepository.findByGenderAndEduTypeAndMajorIdAndDepartmentId(gender, eduType, majorId, departmentId);
    }

    public boolean addS(Student student) {
        if (isInvalidStudent(student)) return false;
        if (studentRepository.existsById(student.getId())) {
            JOptionPane.showMessageDialog(null, "Student already exists", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            studentRepository.save(student);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean deleteS(Student student) {
        try {
            certificateController.deleteByStudentId(student.getId());
            studentRepository.delete(student);
            JOptionPane.showMessageDialog(null, "User deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public long countS() {
        return studentRepository.count();
    }

    public boolean updateS(Student student) {
        if (isInvalidStudent(student)) return false;
        try {
            studentRepository.save(student);
            JOptionPane.showMessageDialog(null, "Student updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean isInvalidStudent(Student student) {
        if (student == null) {
            JOptionPane.showMessageDialog(null, "Student is null", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (student.getId().isEmpty() ||
                student.getName().isEmpty() ||
                student.getDob() == null ||
                student.getGender().isEmpty() ||
                student.getEduType().isEmpty() ||
                student.getCourseYear() == null ||
                student.getClassName().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    public boolean importFile(String path) {
        try {
            UtilsController<Student> utilsController = new UtilsController<>();
            List<Student> students;

            if (path.contains(".xlsx"))
                students = utilsController.importFromExcel(path, Student.class);
            else if (path.contains(".csv"))
                students = utilsController.importFromCSV(path, Student.class);
            else {
                JOptionPane.showMessageDialog(null, "Error: Invalid file extension", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (students == null || students.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No records found", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean exportFile(String path) {
        try {
            UtilsController<Student> utilsController = new UtilsController<>();
            List<Student> students = (List<Student>) getAll();

            if (path.contains(".xlsx"))
                utilsController.exportToExcel(students, path);
            else if (path.contains(".csv"))
                utilsController.exportToCSV(students, path);
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

    public void commit() {
        studentRepository.flush();
    }
}
