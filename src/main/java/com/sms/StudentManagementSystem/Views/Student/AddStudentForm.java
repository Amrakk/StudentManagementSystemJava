/*
 * Created by JFormDesigner on Fri Nov 17 13:47:15 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Student;

import com.sms.StudentManagementSystem.Controllers.StudentController;
import com.sms.StudentManagementSystem.Controllers.UserController;
import com.sms.StudentManagementSystem.Models.Student;
import com.sms.StudentManagementSystem.Views.MainForm;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import javax.swing.*;

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

    public AddStudentForm() {
        initComponents();
    }

    public void setEditable(boolean editable) {
        textName.setEditable(editable);
        textDoB.setEditable(editable);
        textEduType.setEditable(editable);
        textCYear.setEditable(editable);
        textCName.setEditable(editable);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Tri
        this2 = new JFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        textName = new JTextField();
        label5 = new JLabel();
        textEduType = new JTextField();
        label6 = new JLabel();
        textCYear = new JTextField();
        label7 = new JLabel();
        textCName = new JTextField();
        radioGender1 = new JRadioButton();
        radioGender2 = new JRadioButton();
        btnAdd = new JButton();
        btnCancel = new JButton();
        textDoB = new JTextField();

        //======== this2 ========
        {
            var this2ContentPane = this2.getContentPane();

            //---- label1 ----
            label1.setText("Add Student Form");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, 16f));

            //---- label2 ----
            label2.setText("Name");

            //---- label3 ----
            label3.setText("Date of Birth");

            //---- label4 ----
            label4.setText("Gender");

            //---- label5 ----
            label5.setText("EduType");

            //---- label6 ----
            label6.setText("Course Year");

            //---- label7 ----
            label7.setText("Class Name");

            //---- radioGender1 ----
            radioGender1.setText("Male");

            //---- radioGender2 ----
            radioGender2.setText("Female");

            //---- btnAdd ----
            btnAdd.setText("Add");

            //---- btnCancel ----
            btnCancel.setText("Cancel");

            GroupLayout this2ContentPaneLayout = new GroupLayout(this2ContentPane);
            this2ContentPane.setLayout(this2ContentPaneLayout);
            this2ContentPaneLayout.setHorizontalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(this2ContentPaneLayout.createParallelGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                            .addGroup(this2ContentPaneLayout.createSequentialGroup()
                                .addGroup(this2ContentPaneLayout.createParallelGroup()
                                    .addComponent(label3)
                                    .addComponent(label2)
                                    .addComponent(label4)
                                    .addComponent(label5)
                                    .addComponent(label6)
                                    .addComponent(label7))
                                .addGap(46, 46, 46)
                                .addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textName, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(textEduType, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(textCYear, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(textCName, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                                        .addComponent(radioGender1)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioGender2))
                                    .addComponent(textDoB, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(this2ContentPaneLayout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(153, Short.MAX_VALUE))
            );
            this2ContentPaneLayout.setVerticalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(textDoB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(radioGender1)
                            .addComponent(radioGender2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(textEduType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label6)
                            .addComponent(textCYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(textCName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 114, Short.MAX_VALUE))
            );
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Tri
    private JFrame this2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField textName;
    private JLabel label5;
    private JTextField textEduType;
    private JLabel label6;
    private JTextField textCYear;
    private JLabel label7;
    private JTextField textCName;
    private JRadioButton radioGender1;
    private JRadioButton radioGender2;
    private JButton btnAdd;
    private JButton btnCancel;
    private JTextField textDoB;

    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
