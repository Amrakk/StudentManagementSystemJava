/*
 * Created by JFormDesigner on Fri Nov 17 13:46:58 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author hoang
 */
public class StudentDetailForm extends JFrame {
    public StudentDetailForm() {
        initComponents();
    }

    private void btnChangeAvatarMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void btnUserManagementMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Tri
        panelProfile = new JPanel();
        labelName = new JLabel();
        txtName = new JTextField();
        txtDoB = new JTextField();
        labelDoB = new JLabel();
        txtEmail = new JTextField();
        labelEmail = new JLabel();
        txtPhone = new JTextField();
        labelPhone = new JLabel();
        labelRole = new JLabel();
        txtRole = new JTextField();
        txtStatus = new JTextField();
        labelStatus = new JLabel();
        panelAvatar = new JPanel();
        avatarBox = new JLabel();
        hSpacer1 = new JPanel(null);
        hSpacer2 = new JPanel(null);
        vSpacer1 = new JPanel(null);
        vSpacer2 = new JPanel(null);
        btnChangeAvatar = new JButton();
        btnUserManagement = new JButton();
        btnChangeAvatar2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== panelProfile ========
        {
            panelProfile.setBorder(new TitledBorder(new LineBorder(new Color(0x999999), 2, true), "Profile", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 28), new Color(0x999999)));
            panelProfile.setForeground(new Color(0x333333));
            panelProfile.setOpaque(false);
            panelProfile.setFont(new Font("Segoe UI", panelProfile.getFont().getStyle(), 12));
            panelProfile.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
            0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
            .BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt.Color.
            red),panelProfile. getBorder()));panelProfile. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
            beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}});

            //---- labelName ----
            labelName.setText("NAME");
            labelName.setFont(new Font("Segoe UI", labelName.getFont().getStyle() | Font.BOLD, 18));
            labelName.setForeground(new Color(0x666666));

            //---- txtName ----
            txtName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtName.setBorder(null);
            txtName.setEnabled(false);
            txtName.setText("Nguyen Hoang Duy");
            txtName.setForeground(new Color(0x666666));
            txtName.setOpaque(false);
            txtName.setDisabledTextColor(new Color(0x666666));

            //---- txtDoB ----
            txtDoB.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtDoB.setBorder(null);
            txtDoB.setEnabled(false);
            txtDoB.setText("12/08/2003");
            txtDoB.setForeground(new Color(0x666666));
            txtDoB.setOpaque(false);
            txtDoB.setDisabledTextColor(new Color(0x666666));
            txtDoB.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

            //---- labelDoB ----
            labelDoB.setText("DoB");
            labelDoB.setFont(new Font("Segoe UI", labelDoB.getFont().getStyle() | Font.BOLD, 18));
            labelDoB.setForeground(new Color(0x666666));

            //---- txtEmail ----
            txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtEmail.setBorder(null);
            txtEmail.setEnabled(false);
            txtEmail.setText("admin@gmail.com");
            txtEmail.setForeground(new Color(0x666666));
            txtEmail.setOpaque(false);
            txtEmail.setDisabledTextColor(new Color(0x666666));

            //---- labelEmail ----
            labelEmail.setText("EMAIL");
            labelEmail.setFont(new Font("Segoe UI", labelEmail.getFont().getStyle() | Font.BOLD, 18));
            labelEmail.setForeground(new Color(0x666666));

            //---- txtPhone ----
            txtPhone.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtPhone.setBorder(null);
            txtPhone.setEnabled(false);
            txtPhone.setText("0902529803");
            txtPhone.setForeground(new Color(0x666666));
            txtPhone.setOpaque(false);
            txtPhone.setDisabledTextColor(new Color(0x666666));

            //---- labelPhone ----
            labelPhone.setText("PHONE");
            labelPhone.setFont(new Font("Segoe UI", labelPhone.getFont().getStyle() | Font.BOLD, 18));
            labelPhone.setForeground(new Color(0x666666));

            //---- labelRole ----
            labelRole.setText("ROLE");
            labelRole.setFont(new Font("Segoe UI", labelRole.getFont().getStyle() | Font.BOLD, 18));
            labelRole.setForeground(new Color(0x666666));

            //---- txtRole ----
            txtRole.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtRole.setBorder(null);
            txtRole.setEnabled(false);
            txtRole.setText("Admin");
            txtRole.setForeground(new Color(0x666666));
            txtRole.setOpaque(false);
            txtRole.setDisabledTextColor(new Color(0x666666));

            //---- txtStatus ----
            txtStatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtStatus.setBorder(null);
            txtStatus.setEnabled(false);
            txtStatus.setText("Normal");
            txtStatus.setForeground(new Color(0x666666));
            txtStatus.setOpaque(false);
            txtStatus.setDisabledTextColor(new Color(0x666666));
            txtStatus.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

            //---- labelStatus ----
            labelStatus.setText("STATUS");
            labelStatus.setFont(new Font("Segoe UI", labelStatus.getFont().getStyle() | Font.BOLD, 18));
            labelStatus.setForeground(new Color(0x666666));

            //======== panelAvatar ========
            {
                panelAvatar.setBorder(new TitledBorder(new LineBorder(new Color(0x999999), 2, true), "Avatar", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Segoe UI", Font.BOLD, 28), new Color(0x999999)));
                panelAvatar.setForeground(new Color(0x999999));
                panelAvatar.setOpaque(false);
                panelAvatar.setLayout(new BorderLayout());

                //---- avatarBox ----
                avatarBox.setIcon(new ImageIcon(getClass().getResource("/Images/defaultAvatar.png")));
                avatarBox.setHorizontalAlignment(SwingConstants.CENTER);
                panelAvatar.add(avatarBox, BorderLayout.CENTER);
                panelAvatar.add(hSpacer1, BorderLayout.NORTH);
                panelAvatar.add(hSpacer2, BorderLayout.SOUTH);
                panelAvatar.add(vSpacer1, BorderLayout.EAST);
                panelAvatar.add(vSpacer2, BorderLayout.WEST);
            }

            //---- btnChangeAvatar ----
            btnChangeAvatar.setText("CHANGE AVATAR");
            btnChangeAvatar.setFont(new Font("Segoe UI", btnChangeAvatar.getFont().getStyle() | Font.BOLD, 20));
            btnChangeAvatar.setBackground(SystemColor.control);
            btnChangeAvatar.setForeground(new Color(0x333333));
            btnChangeAvatar.setBorder(new LineBorder(new Color(0x333333), 1, true));
            btnChangeAvatar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnChangeAvatarMouseClicked(e);
                }
            });

            //---- btnUserManagement ----
            btnUserManagement.setText("SAVE");
            btnUserManagement.setFont(new Font("Segoe UI", btnUserManagement.getFont().getStyle() | Font.BOLD, 18));
            btnUserManagement.setBackground(new Color(0x0066cc));
            btnUserManagement.setForeground(Color.white);
            btnUserManagement.setMaximumSize(new Dimension(172, 38));
            btnUserManagement.setMinimumSize(new Dimension(172, 38));
            btnUserManagement.setPreferredSize(new Dimension(172, 38));
            btnUserManagement.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnUserManagementMouseClicked(e);
                }
            });

            //---- btnChangeAvatar2 ----
            btnChangeAvatar2.setText("RESET");
            btnChangeAvatar2.setFont(new Font("Segoe UI", btnChangeAvatar2.getFont().getStyle() | Font.BOLD, 20));
            btnChangeAvatar2.setBackground(SystemColor.control);
            btnChangeAvatar2.setForeground(new Color(0x333333));
            btnChangeAvatar2.setBorder(new LineBorder(new Color(0x333333), 1, true));
            btnChangeAvatar2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnChangeAvatarMouseClicked(e);
                }
            });

            GroupLayout panelProfileLayout = new GroupLayout(panelProfile);
            panelProfile.setLayout(panelProfileLayout);
            panelProfileLayout.setHorizontalGroup(
                panelProfileLayout.createParallelGroup()
                    .addGroup(panelProfileLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(labelPhone)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panelProfileLayout.createSequentialGroup()
                                .addComponent(labelStatus)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panelProfileLayout.createSequentialGroup()
                                .addComponent(labelRole)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtRole, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panelProfileLayout.createSequentialGroup()
                                .addComponent(labelDoB)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDoB, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panelProfileLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelName)
                                .addGap(40, 40, 40)
                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panelProfileLayout.createSequentialGroup()
                                .addComponent(labelEmail)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57)
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChangeAvatar, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(btnChangeAvatar2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUserManagement, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
            );
            panelProfileLayout.setVerticalGroup(
                panelProfileLayout.createParallelGroup()
                    .addGroup(panelProfileLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelName)
                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelEmail))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPhone))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDoB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDoB))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelRole))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelStatus))
                                .addContainerGap(19, Short.MAX_VALUE))
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnChangeAvatar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnUserManagement, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnChangeAvatar2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(83, 83, 83)
                    .addComponent(panelProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(315, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(panelProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(108, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Tri
    private JPanel panelProfile;
    private JLabel labelName;
    private JTextField txtName;
    private JTextField txtDoB;
    private JLabel labelDoB;
    private JTextField txtEmail;
    private JLabel labelEmail;
    private JTextField txtPhone;
    private JLabel labelPhone;
    private JLabel labelRole;
    private JTextField txtRole;
    private JTextField txtStatus;
    private JLabel labelStatus;
    private JPanel panelAvatar;
    private JLabel avatarBox;
    private JPanel hSpacer1;
    private JPanel hSpacer2;
    private JPanel vSpacer1;
    private JPanel vSpacer2;
    private JButton btnChangeAvatar;
    private JButton btnUserManagement;
    private JButton btnChangeAvatar2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
