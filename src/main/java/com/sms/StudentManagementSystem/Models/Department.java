package com.sms.StudentManagementSystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    private String id;
    private String name;
    private int code;

    @OneToMany(mappedBy = "department")
    private List<Major> majors;
    @OneToMany(mappedBy = "department")
    private List<Student> students;

//    @Override
//    public String toString() {
//        return "Department{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", code=" + code +
//                ", majors=" + majors +
//                ", students=" + students +
//                '}';
//    }
}
