package com.sms.StudentManagementSystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    @Id
    private String id;
    private String name;

    @ManyToOne
    private Department department;
    @OneToMany(mappedBy = "major")
    private List<Student> students;

//    @Override
//    public String toString() {
//        return "Major{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", department=" + department +
//                ", students=" + students +
//                '}';
//    }
}
