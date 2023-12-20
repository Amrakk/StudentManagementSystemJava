/*
 * Created by JFormDesigner on Fri Nov 17 13:47:15 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Student;

import com.sms.StudentManagementSystem.Controllers.DepartmentController;
import com.sms.StudentManagementSystem.Controllers.MajorController;
import com.sms.StudentManagementSystem.Controllers.StudentController;
import com.sms.StudentManagementSystem.Models.Department;
import com.sms.StudentManagementSystem.Models.Major;
import com.sms.StudentManagementSystem.Models.Student;
import com.sms.StudentManagementSystem.Views.MainForm;
import com.toedter.calendar.JDateChooser;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author hoang
 */

@Component
@Service
public class AddStudentForm extends JFrame {
    @Setter
    private Student student;

    @Setter
    private MainForm mainForm;

    @Setter
    private StudentController studentController;

    private List<Department> departments;

    @Setter
    private DepartmentController departmentController;

    private List<Major> majors;

    @Setter
    private MajorController majorController;

    public AddStudentForm() {
        if (GraphicsEnvironment.isHeadless()) System.out.println("Headless mode");
        else initComponents();

        //---- dcDoB ----
        dcDoB.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        dcDoB.setBorder(new LineBorder(new Color(0x666666), 1, true));
        dcDoB.setForeground(new Color(0x666666));
        dcDoB.setDate(Date.from(Instant.now()));
        dcDoB.setDateFormatString("dd/MM/yyyy");
        dcDoB.setOpaque(false);
    }

    public void loadAddForm() {
        departments = departmentController.findAll();
        majors = majorController.getAll();

        cbDepartmentID.setRenderer(new DepartmentObjectRenderer());
        cbMajorID.setRenderer(new MajorObjectRenderer());

        cbDepartmentID.removeAllItems();
        cbMajorID.removeAllItems();

        departments.forEach(d -> {
            cbDepartmentID.addItem(d);
        });
        majors.forEach(m -> {
            cbMajorID.addItem(m);
        });
    }

    private void btnAddMouseClicked(MouseEvent e) {
        String id = textStudentID.getText();
        String name = textName.getText();
        Date dob = dcDoB.getDate();
        String gender = radioGender1.isSelected() ? "Male" : "Female";
        String eduType = textEduType.getText();
        String courseYear = textCYear.getText();
        String className = textCName.getText();
        Object departmentID = cbDepartmentID.getSelectedItem();
        Object majorID = cbMajorID.getSelectedItem();

        if (departmentID == null) departmentID = "";
        if (majorID == null) majorID = "";

        Student student = new Student(id, name, dob, gender, eduType, courseYear, className, (Department) departmentID, (Major) majorID);

        if (studentController.addS(student)) {
            System.out.println(student + "\n Add Student Success");
            JOptionPane.showMessageDialog(null, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            mainForm.studentPanelLoadTable();
            dispose();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Tri
        label1 = new JLabel();
        labelName = new JLabel();
        labelDoB = new JLabel();
        labelGender = new JLabel();
        textName = new JTextField();
        labelEduT = new JLabel();
        textEduType = new JTextField();
        labelCourseY = new JLabel();
        textCYear = new JTextField();
        labelClassN = new JLabel();
        textCName = new JTextField();
        radioGender1 = new JRadioButton();
        radioGender2 = new JRadioButton();
        btnAdd = new JButton();
        labelStudentID = new JLabel();
        textStudentID = new JTextField();
        dcDoB = new JDateChooser();
        labelDepartmentID = new JLabel();
        labelMajorID = new JLabel();
        cbDepartmentID = new JComboBox();
        cbMajorID = new JComboBox();

        //======== this ========
        setMinimumSize(new Dimension(575, 475));
        setPreferredSize(new Dimension(575, 475));
        setTitle("Add Student Form");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Add Student Form");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, 16f));

        //---- labelName ----
        labelName.setText("Name");

        //---- labelDoB ----
        labelDoB.setText("Date of Birth");

        //---- labelGender ----
        labelGender.setText("Gender");

        //---- labelEduT ----
        labelEduT.setText("EduType");

        //---- labelCourseY ----
        labelCourseY.setText("Course Year");

        //---- labelClassN ----
        labelClassN.setText("Class Name");

        //---- radioGender1 ----
        radioGender1.setText("Male");

        //---- radioGender2 ----
        radioGender2.setText("Female");

        //---- btnAdd ----
        btnAdd.setText("Add");
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnAddMouseClicked(e);
            }
        });

        //---- labelStudentID ----
        labelStudentID.setText("StudentID");

        //---- labelDepartmentID ----
        labelDepartmentID.setText("DepartmentID");

        //---- labelMajorID ----
        labelMajorID.setText("MajorID");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(377, Short.MAX_VALUE)
                    .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    .addGap(95, 95, 95))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addComponent(labelDoB)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcDoB, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(labelName)
                                        .addComponent(labelGender)
                                        .addComponent(labelEduT)
                                        .addComponent(labelCourseY)
                                        .addComponent(labelClassN)
                                        .addComponent(labelStudentID)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelDepartmentID))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(labelMajorID)
                                    .addGap(110, 110, 110)))
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(textName, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                .addComponent(textEduType, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                .addComponent(textCYear, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                .addComponent(textCName, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(radioGender1)
                                    .addGap(18, 18, 18)
                                    .addComponent(radioGender2))
                                .addComponent(textStudentID, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                .addComponent(cbDepartmentID)
                                .addComponent(cbMajorID))))
                    .addContainerGap(247, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelStudentID)
                        .addComponent(textStudentID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelName)
                        .addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDoB)
                        .addComponent(dcDoB, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelGender)
                        .addComponent(radioGender1)
                        .addComponent(radioGender2))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelEduT)
                        .addComponent(textEduType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCourseY)
                        .addComponent(textCYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelClassN)
                        .addComponent(textCName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDepartmentID)
                        .addComponent(cbDepartmentID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelMajorID)
                        .addComponent(cbMajorID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(7, 7, 7)
                    .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Tri
    private JLabel label1;
    private JLabel labelName;
    private JLabel labelDoB;
    private JLabel labelGender;
    private JTextField textName;
    private JLabel labelEduT;
    private JTextField textEduType;
    private JLabel labelCourseY;
    private JTextField textCYear;
    private JLabel labelClassN;
    private JTextField textCName;
    private JRadioButton radioGender1;
    private JRadioButton radioGender2;
    private JButton btnAdd;
    private JLabel labelStudentID;
    private JTextField textStudentID;
    private JDateChooser dcDoB;
    private JLabel labelDepartmentID;
    private JLabel labelMajorID;
    private JComboBox cbDepartmentID;
    private JComboBox cbMajorID;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

class DepartmentObjectRenderer extends DefaultListCellRenderer {
    @Override
    public java.awt.Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Department) {
            Department yourObject = (Department) value;
            setText(yourObject.getName());
        }

        return this;
    }
}

class MajorObjectRenderer extends DefaultListCellRenderer {
    @Override
    public java.awt.Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Major) {
            Major yourObject = (Major) value;
            setText(yourObject.getName());
        }

        return this;
    }
}
