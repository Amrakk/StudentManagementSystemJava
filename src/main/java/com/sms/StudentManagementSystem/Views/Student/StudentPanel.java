/*
 * Created by JFormDesigner on Mon Nov 20 19:39:53 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Student;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * @author hoang
 */
@Component
public class StudentPanel extends JPanel {
    public StudentPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        labelHeader = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1498, 756));
        setMinimumSize(new Dimension(894, 757));
        setBackground(SystemColor.control);

        //---- labelHeader ----
        labelHeader.setText("Student");
        labelHeader.setBackground(new Color(0x990099));
        labelHeader.setForeground(new Color(0x333333));
        labelHeader.setFont(new Font("Segoe UI", Font.BOLD, 36));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addComponent(labelHeader)
                    .addContainerGap(1253, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(labelHeader)
                    .addContainerGap(656, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel labelHeader;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
