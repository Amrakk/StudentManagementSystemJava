/*
 * Created by JFormDesigner on Mon Nov 20 20:22:20 ICT 2023
 */

package com.sms.StudentManagementSystem.Views;

import com.sms.StudentManagementSystem.Controllers.UserController;
import com.sms.StudentManagementSystem.Controllers.UtilsController;
import com.sms.StudentManagementSystem.Models.User;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * @author hoang
 */
@Component
public class ProfilePanel extends JPanel {
    private User user;

    private MainForm mainForm;

    @Setter
    private UserController userController;

    public ProfilePanel() {
        if (GraphicsEnvironment.isHeadless()) System.out.println("Headless mode");
        else initComponents();
    }

    public void panelLoad() {
        if (user == null) return;

        if (!user.getRole().equals("Admin")) {
            panelAdmin.setVisible(false);
            panelAdmin.setEnabled(false);
        }

        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtDoB.setText(user.getDob().toString());
        txtRole.setText(user.getRole());
        txtStatus.setText(user.getStatus());

        if (!UtilsController.loadAvatar(avatarBox, user.getEmail()))
            JOptionPane.showMessageDialog(null, "Error: Avatar is not loaded.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMainForm(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    private void btnUserManagementMouseClicked(MouseEvent e) {
        mainForm.loadUserForm();
    }

    private void btnChangePasswordMouseClicked(MouseEvent e) {
        String newPassword = String.valueOf(txtNewPassword.getPassword());
        String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());

        boolean isChanged = userController.changePassword(user.getEmail(), newPassword, confirmPassword);
        if (isChanged) {
            txtNewPassword.setText("");
            txtConfirmPassword.setText("");
        }
    }

    private void txtConfirmPasswordEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnChangePasswordMouseClicked(null);
    }

    private void txtNewPasswordEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnChangePasswordMouseClicked(null);
    }

    private void btnChangeAvatarMouseClicked(MouseEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an image");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif", "ico");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File avatar = fileChooser.getSelectedFile();
            if (UtilsController.saveAvatar(user.getEmail(), avatar)) {
                JOptionPane.showMessageDialog(null, "Avatar changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (!UtilsController.loadAvatar(avatarBox, user.getEmail()))
                    JOptionPane.showMessageDialog(null, "Error: Avatar is not loaded.", "Error", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(null, "Error: Avatar is not changed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnLoginHistoryMouseClicked(MouseEvent e) {
        mainForm.openLoginHistoryForm();
    }

    private void btnChangePasswordEnterKeyPressed(KeyEvent e) {
        btnChangePasswordMouseClicked(null);
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
        labelNewPassword = new JLabel();
        txtNewPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        labelConfirmPassword = new JLabel();
        btnChangePassword = new JButton();
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

            //---- labelNewPassword ----
            labelNewPassword.setText("NEW PASSWORD");
            labelNewPassword.setFont(new Font("Segoe UI", labelNewPassword.getFont().getStyle() | Font.BOLD, 18));
            labelNewPassword.setForeground(new Color(0x666666));

            //---- txtNewPassword ----
            txtNewPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtNewPassword.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    txtNewPasswordEnterKeyPressed(e);
                }
            });

            //---- txtConfirmPassword ----
            txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtConfirmPassword.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    txtConfirmPasswordEnterKeyPressed(e);
                }
            });

            //---- labelConfirmPassword ----
            labelConfirmPassword.setText("CONFIRM PASSWORD");
            labelConfirmPassword.setFont(new Font("Segoe UI", labelConfirmPassword.getFont().getStyle() | Font.BOLD, 18));
            labelConfirmPassword.setForeground(new Color(0x666666));

            //---- btnChangePassword ----
            btnChangePassword.setText("CHANGE PASSWORD");
            btnChangePassword.setFont(new Font("Segoe UI", btnChangePassword.getFont().getStyle() | Font.BOLD, 20));
            btnChangePassword.setBackground(SystemColor.control);
            btnChangePassword.setForeground(new Color(0x666666));
            btnChangePassword.setBorder(new LineBorder(new Color(0x333333), 1, true));
            btnChangePassword.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnChangePasswordMouseClicked(e);
                }
            });
            btnChangePassword.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnChangePasswordEnterKeyPressed(e);
                }
            });

            GroupLayout panelSecurityLayout = new GroupLayout(panelSecurity);
            panelSecurity.setLayout(panelSecurityLayout);
            panelSecurityLayout.setHorizontalGroup(
                panelSecurityLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panelSecurityLayout.createSequentialGroup()
                        .addGroup(panelSecurityLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panelSecurityLayout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(labelNewPassword)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                                .addComponent(txtNewPassword, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelSecurityLayout.createSequentialGroup()
                                .addGap(0, 218, Short.MAX_VALUE)
                                .addComponent(labelConfirmPassword)
                                .addGap(60, 60, 60)
                                .addComponent(txtConfirmPassword, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))
                        .addGap(217, 217, 217))
                    .addGroup(panelSecurityLayout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(btnChangePassword, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelSecurityLayout.setVerticalGroup(
                panelSecurityLayout.createParallelGroup()
                    .addGroup(panelSecurityLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelSecurityLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNewPassword)
                            .addComponent(txtNewPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panelSecurityLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelConfirmPassword)
                            .addComponent(txtConfirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(btnChangePassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(10, Short.MAX_VALUE))
            );
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
            btnUserManagement.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnUserManagementMouseClicked(e);
                }
            });

            //---- btnLoginHistory ----
            btnLoginHistory.setText("Login History");
            btnLoginHistory.setFont(new Font("Segoe UI", btnLoginHistory.getFont().getStyle() | Font.BOLD, 18));
            btnLoginHistory.setBackground(new Color(0x0066cc));
            btnLoginHistory.setForeground(Color.white);
            btnLoginHistory.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnLoginHistoryMouseClicked(e);
                }
            });

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
    private JLabel labelNewPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JLabel labelConfirmPassword;
    private JButton btnChangePassword;
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
