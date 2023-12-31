package com.sms.StudentManagementSystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student {
    @Id
    private String id;
    private String name;
    private Date dob;
    private String gender;
    private String eduType;
    private String courseYear;
    private String className;

    @ManyToOne
    private Major major;
    @ManyToOne
    private Department department;
    @OneToMany(mappedBy = "student")
    private List<Certificate> certificates;

    public Student(String id, String name, Date dob, String gender, String eduType, String courseYear, String className, Department department, Major major) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.eduType = eduType;
        this.courseYear = courseYear;
        this.className = className;
        this.major = major;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", eduType='" + eduType + '\'' +
                ", courseYear='" + courseYear + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
