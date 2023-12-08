package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Models.Student;
import com.sms.StudentManagementSystem.Models.User;
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
    private StudentPanel studentPanel;

    public Iterable<Student> getByName(String text) {
        return studentRepository.searchByName(text);
    }

    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public Iterable<Student> GetByCriteria(String gender, String eduType, String majorId, String departmentId){
        return studentRepository.findByGenderAndEduTypeAndMajorIdAndDepartmentId(gender, eduType, majorId, departmentId);
    }

    public Student addS(Student student){
        return studentRepository.save(student);
    }

    public void deleteS(String id){
        studentRepository.deleteById(id);
    }

    public long countS() {
        return studentRepository.count();
    }

    public Student updateS(Student student) {
        return studentRepository.save(student);
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
}
