package com.sms.StudentManagementSystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {
    @Id
    private String id;
    private String title;
    private String description;
    private Date issuedDate;
    private Date expiredDate;
    private String organization;
    private boolean isValid;

    @ManyToOne
    private Student student;

//    public Certificate(String id, String tittle, String desc, Boolean isValid, Date issueD, Date expiredD, String organization) {
//        this.id = id;
//        this.title = tittle;
//        this.description = desc;
//        this.isValid = isValid;
//        this.issuedDate = issueD;
//        this.expiredDate = expiredD;
//        this.organization = organization;
//    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", issuedDate=" + issuedDate +
                ", expiredDate=" + expiredDate +
                ", organization='" + organization + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    public boolean getValid() {
        return isValid;
    }
}
