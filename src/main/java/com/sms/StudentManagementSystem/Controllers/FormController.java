package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Views.Admin.LoginHistoryForm;
import com.sms.StudentManagementSystem.Views.Admin.UserDetailForm;
import com.sms.StudentManagementSystem.Views.MainForm;
import com.sms.StudentManagementSystem.Views.Student.AddStudentForm;
import com.sms.StudentManagementSystem.Views.Student.StudentDetailForm;
import com.sun.tools.javac.Main;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
@Getter
public class FormController {
    @Autowired
    private LoginHistoryForm loginHistoryForm;

    @Autowired
    private UserDetailForm userDetailForm;


//    @Autowired
//    private CertificateController certificateController;
    @Autowired
    private StudentController studentController;

    @Autowired
    private AddStudentForm addStudentForm;

    @Autowired
    private StudentDetailForm studentDetailForm;


//    @Autowired
//    private DepartmentController departmentController;
//    @Autowired
//    private MajorController majorController;


}
