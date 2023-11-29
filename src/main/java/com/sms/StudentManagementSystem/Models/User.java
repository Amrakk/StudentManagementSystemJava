package com.sms.StudentManagementSystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
    @Id
    private String email;
    private String password;
    private String name;
    private int age;
    private Date dob;
    private String phone;
    private String status;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<LoginHistory> loginHistories;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
