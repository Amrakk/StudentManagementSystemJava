package com.sms.StudentManagementSystem.Controllers;

import com.sms.StudentManagementSystem.Views.Admin.LoginHistoryForm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Getter
public class FormController {
    @Autowired
    private LoginHistoryForm loginHistoryForm;

//    @Autowired
//    private LoginHistoryController loginHistoryController;
//    @Autowired
//    private UserController userController;
//    @Autowired
//    private CertificateController certificateController;
//    @Autowired
//    private StudentController studentController;
//    @Autowired
//    private DepartmentController departmentController;
//    @Autowired
//    private MajorController majorController;
}