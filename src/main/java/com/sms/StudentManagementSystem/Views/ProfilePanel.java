/*
 * Created by JFormDesigner on Mon Nov 20 20:22:20 ICT 2023
 */

package com.sms.StudentManagementSystem.Views;

import com.sms.StudentManagementSystem.Controllers.UserController;
import com.sms.StudentManagementSystem.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author hoang
 */
@Component
public class ProfilePanel extends JPanel {
    private User user;

    @Autowired
    private UserController userController;

    public ProfilePanel() {
        initComponents();
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        labelHeader = new JLabel();
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
        panelSecurity = new JPanel();
        labelPhone2 = new JLabel();
        txtNewPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        labelPhone3 = new JLabel();
        btnChangeAvatar2 = new JButton();
        btnChangeAvatar = new JButton();
        panelAdmin = new JPanel();
        btnUserManagement = new JButton();
        btnLoginHistory = new JButton();
        panelAvatar = new JPanel();
        avatarBox = new JLabel();
        hSpacer1 = new JPanel(null);
        hSpacer2 = new JPanel(null);
        vSpacer1 = new JPanel(null);
        vSpacer2 = new JPanel(null);

        //======== this ========
        setPreferredSize(new Dimension(1498, 756));
        setMinimumSize(new Dimension(894, 757));
        setBackground(SystemColor.control);
        setForeground(Color.white);

        //---- labelHeader ----
        labelHeader.setText("Account");
        labelHeader.setBackground(new Color(0x990099));
        labelHeader.setForeground(new Color(0x333333));
        labelHeader.setFont(new Font("Segoe UI", Font.BOLD, 36));

        //======== panelProfile ========
        {
            panelProfile.setBorder(new TitledBorder(new LineBorder(new Color(0x999999), 2, true), "Profile", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 28), new Color(0x999999)));
            panelProfile.setForeground(new Color(0x333333));
            panelProfile.setOpaque(false);

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

            GroupLayout panelProfileLayout = new GroupLayout(panelProfile);
            panelProfile.setLayout(panelProfileLayout);
            panelProfileLayout.setHorizontalGroup(
                panelProfileLayout.createParallelGroup()
                    .addGroup(panelProfileLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(labelEmail)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(labelName)
                                .addGap(40, 40, 40)
                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(labelPhone)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)))
                        .addGap(136, 136, 136)
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(labelDoB)
                                .addGap(90, 90, 90)
                                .addComponent(txtDoB, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panelProfileLayout.createSequentialGroup()
                                .addGroup(panelProfileLayout.createParallelGroup()
                                    .addComponent(labelRole)
                                    .addComponent(labelStatus))
                                .addGap(58, 58, 58)
                                .addGroup(panelProfileLayout.createParallelGroup()
                                    .addComponent(txtStatus, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRole, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))))
                        .addGap(46, 46, 46))
            );
            panelProfileLayout.setVerticalGroup(
                panelProfileLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panelProfileLayout.createSequentialGroup()
                        .addContainerGap(28, Short.MAX_VALUE)
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelName)
                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelEmail))
                                .addGap(30, 30, 30)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPhone)))
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelDoB)
                                    .addComponent(txtDoB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelRole))
                                .addGap(30, 30, 30)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelStatus))))
                        .addGap(28, 28, 28))
            );
        }

        //======== panelSecurity ========
        {
            panelSecurity.setBorder(new TitledBorder(new LineBorder(new Color(0x999999), 2, true), "Security", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 28), new Color(0x999999)));
            panelSecurity.setForeground(new Color(0x333333));
            panelSecurity.setOpaque(false);
            panelSecurity.setPreferredSize(new Dimension(956, 310));

            //---- labelPhone2 ----
            labelPhone2.setText("NEW PASSWORD");
            labelPhone2.setFont(new Font("Segoe UI", labelPhone2.getFont().getStyle() | Font.BOLD, 18));
            labelPhone2.setForeground(new Color(0x666666));

            //---- txtNewPassword ----
            txtNewPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));

            //---- txtConfirmPassword ----
            txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));

            //---- labelPhone3 ----
            labelPhone3.setText("CONFIRM PASSWORD");
            labelPhone3.setFont(new Font("Segoe UI", labelPhone3.getFont().getStyle() | Font.BOLD, 18));
            labelPhone3.setForeground(new Color(0x666666));

            //---- btnChangeAvatar2 ----
            btnChangeAvatar2.setText("CHANGE PASSWORD");
            btnChangeAvatar2.setFont(new Font("Segoe UI", btnChangeAvatar2.getFont().getStyle() | Font.BOLD, 20));
            btnChangeAvatar2.setBackground(SystemColor.control);
            btnChangeAvatar2.setForeground(new Color(0x666666));
            btnChangeAvatar2.setBorder(new LineBorder(new Color(0x333333), 1, true));

            GroupLayout panelSecurityLayout = new GroupLayout(panelSecurity);
            panelSecurity.setLayout(panelSecurityLayout);
            panelSecurityLayout.setHorizontalGroup(
                panelSecurityLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panelSecurityLayout.createSequentialGroup()
                        .addGroup(panelSecurityLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panelSecurityLayout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(labelPhone2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                                .addComponent(txtNewPassword, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelSecurityLayout.createSequentialGroup()
                                .addGap(0, 218, Short.MAX_VALUE)
                                .addComponent(labelPhone3)
                                .addGap(60, 60, 60)
                                .addComponent(txtConfirmPassword, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))
                        .addGap(217, 217, 217))
                    .addGroup(panelSecurityLayout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(btnChangeAvatar2, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelSecurityLayout.setVerticalGroup(
                panelSecurityLayout.createParallelGroup()
                    .addGroup(panelSecurityLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelSecurityLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPhone2)
                            .addComponent(txtNewPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panelSecurityLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPhone3)
                            .addComponent(txtConfirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(btnChangeAvatar2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(10, Short.MAX_VALUE))
            );
        }

        //---- btnChangeAvatar ----
        btnChangeAvatar.setText("CHANGE AVATAR");
        btnChangeAvatar.setFont(new Font("Segoe UI", btnChangeAvatar.getFont().getStyle() | Font.BOLD, 20));
        btnChangeAvatar.setBackground(SystemColor.control);
        btnChangeAvatar.setForeground(new Color(0x333333));
        btnChangeAvatar.setBorder(new LineBorder(new Color(0x333333), 1, true));

        //======== panelAdmin ========
        {
            panelAdmin.setBorder(new TitledBorder(new LineBorder(new Color(0x999999), 2, true), "Admin", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 28), new Color(0x999999)));
            panelAdmin.setForeground(new Color(0x999999));
            panelAdmin.setOpaque(false);

            //---- btnUserManagement ----
            btnUserManagement.setText("User Management");
            btnUserManagement.setFont(new Font("Segoe UI", btnUserManagement.getFont().getStyle() | Font.BOLD, 18));
            btnUserManagement.setBackground(new Color(0x0066cc));
            btnUserManagement.setForeground(Color.white);
            btnUserManagement.setMaximumSize(new Dimension(172, 38));
            btnUserManagement.setMinimumSize(new Dimension(172, 38));
            btnUserManagement.setPreferredSize(new Dimension(172, 38));

            //---- btnLoginHistory ----
            btnLoginHistory.setText("Login History");
            btnLoginHistory.setFont(new Font("Segoe UI", btnLoginHistory.getFont().getStyle() | Font.BOLD, 18));
            btnLoginHistory.setBackground(new Color(0x0066cc));
            btnLoginHistory.setForeground(Color.white);

            GroupLayout panelAdminLayout = new GroupLayout(panelAdmin);
            panelAdmin.setLayout(panelAdminLayout);
            panelAdminLayout.setHorizontalGroup(
                panelAdminLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panelAdminLayout.createSequentialGroup()
                        .addGroup(panelAdminLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLoginHistory, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUserManagement, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0))
            );
            panelAdminLayout.setVerticalGroup(
                panelAdminLayout.createParallelGroup()
                    .addGroup(panelAdminLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnUserManagement, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoginHistory, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

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

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addComponent(labelHeader)
                                .addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(btnChangeAvatar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelAdmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(panelProfile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelSecurity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(labelHeader)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnChangeAvatar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(panelAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(panelSecurity, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)))
                    .addGap(38, 38, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel labelHeader;
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
    private JPanel panelSecurity;
    private JLabel labelPhone2;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JLabel labelPhone3;
    private JButton btnChangeAvatar2;
    private JButton btnChangeAvatar;
    private JPanel panelAdmin;
    private JButton btnUserManagement;
    private JButton btnLoginHistory;
    private JPanel panelAvatar;
    private JLabel avatarBox;
    private JPanel hSpacer1;
    private JPanel hSpacer2;
    private JPanel vSpacer1;
    private JPanel vSpacer2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
