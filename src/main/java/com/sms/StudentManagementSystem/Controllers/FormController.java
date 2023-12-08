package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Views.Admin.LoginHistoryForm;
import com.sms.StudentManagementSystem.Views.Admin.UserDetailForm;
import com.sms.StudentManagementSystem.Views.Student.AddStudentForm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Autowired
//    private StudentController studentController;
//    @Autowired
//    private DepartmentController departmentController;
//    @Autowired
//    private MajorController majorController;


}
