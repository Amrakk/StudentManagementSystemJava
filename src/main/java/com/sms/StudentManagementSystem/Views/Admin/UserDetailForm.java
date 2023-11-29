/*
 * Created by JFormDesigner on Fri Nov 17 13:44:35 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Admin;

import com.sms.StudentManagementSystem.Controllers.UserController;
import com.sms.StudentManagementSystem.Controllers.UtilsController;
import com.sms.StudentManagementSystem.Models.User;
import com.sms.StudentManagementSystem.Views.MainForm;
import com.toedter.calendar.JDateChooser;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.Date;

/**
 * @author hoang
 */
@Component
public class UserDetailForm extends JFrame {
    @Setter
    private User user;

    @Setter
    private MainForm mainForm;

    @Setter
    private UserController userController;

    public UserDetailForm() {
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

    public void loadForm() {
        btnResetMouseClicked(null);
        if (user == null) {
            // adding new user
            labelFormStatus.setText("... Adding");
            btnSave.setText("ADD");
            txtEmail.setEditable(true);
            panelSecurity.setVisible(false);
            btnResetAvatar.setVisible(false);
        } else {
            // editing user
            labelFormStatus.setText("... Editing");
            btnSave.setText("EDIT");
            txtEmail.setEditable(false);
            panelSecurity.setVisible(true);
            btnResetAvatar.setVisible(true);
            if (!UtilsController.loadAvatar(avatarBox, user.getEmail()))
                JOptionPane.showMessageDialog(null, "Error: Avatar is not loaded.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnChangePasswordMouseClicked(MouseEvent e) {
        String newPassword = String.valueOf(txtNewPassword.getPassword());
        if (userController.changePassword(user.getEmail(), newPassword))
            txtNewPassword.setText("");
    }

    private void txtNewPasswordEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnChangePasswordMouseClicked(null);
    }

    private void btnChangePasswordEnterKeyPressed(KeyEvent e) {
        btnChangePasswordMouseClicked(null);
    }

    private void btnResetMouseClicked(MouseEvent e) {
        if (user == null) {
            // adding new user
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            cbRole.setSelectedIndex(0);
            cbStatus.setSelectedIndex(0);
            dcDoB.setDate(Date.from(Instant.now()));
        } else {
            // editing user
            txtName.setText(user.getName());
            txtEmail.setText(user.getEmail());
            txtPhone.setText(user.getPhone());
            for (int i = 0; i < cbRole.getItemCount(); i++) {
                if (cbRole.getItemAt(i).equals(user.getRole())) {
                    cbRole.setSelectedIndex(i);
                    break;
                }
            }
            for (int i = 0; i < cbStatus.getItemCount(); i++) {
                if (cbStatus.getItemAt(i).equals(user.getStatus())) {
                    cbStatus.setSelectedIndex(i);
                    break;
                }
            }

            dcDoB.setDate(user.getDob());
        }
    }

    private void btnResetAvatarMouseClicked(MouseEvent e) {
        if (UtilsController.saveAvatar(user.getEmail(), null)) {
            JOptionPane.showMessageDialog(null, "Avatar reset successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            if (!UtilsController.loadAvatar(avatarBox, user.getEmail()))
                JOptionPane.showMessageDialog(null, "Error: Avatar is not loaded.", "Error", JOptionPane.ERROR_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null, "Error: Avatar is not changed.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void btnDeleteMouseClicked(MouseEvent e) {
        if (userController.delete(user)) {
            mainForm.userPanelLoadTable();
            dispose();
        }
    }

    private void btnSaveMouseClicked(MouseEvent e) {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        Object role = cbRole.getSelectedItem();
        Object status = cbStatus.getSelectedItem();
        Date dob = dcDoB.getDate();
        int age = UtilsController.calculateAge(dob);

        if (role == null) role = "";
        if (status == null) status = "";

        if (user == null) {
            // adding new user
            user = new User(email, phone, name, age, dob, phone, status.toString(), role.toString(), null);
            if (userController.add(user)) {
                mainForm.userPanelLoadTable();
                dispose();
            }
            user = null;
        } else {
            // editing user
            user.setName(name);
            user.setAge(age);
            user.setDob(dob);
            user.setPhone(phone);
            user.setStatus(status.toString());
            user.setRole(role.toString());

            if (userController.update(user)) {
                mainForm.userPanelLoadTable();
                mainForm.loadHeader();
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panelProfile = new JPanel();
        labelName = new JLabel();
        txtName = new JTextField();
        labelDoB = new JLabel();
        txtEmail = new JTextField();
        labelEmail = new JLabel();
        txtPhone = new JTextField();
        labelPhone = new JLabel();
        labelRole = new JLabel();
        labelStatus = new JLabel();
        panelAvatar = new JPanel();
        avatarBox = new JLabel();
        hSpacer1 = new JPanel(null);
        hSpacer2 = new JPanel(null);
        vSpacer1 = new JPanel(null);
        vSpacer2 = new JPanel(null);
        btnResetAvatar = new JButton();
        btnSave = new JButton();
        btnReset = new JButton();
        cbRole = new JComboBox<>();
        cbStatus = new JComboBox<>();
        dcDoB = new JDateChooser();
        panelSecurity = new JPanel();
        labelNewPassword = new JLabel();
        txtNewPassword = new JPasswordField();
        btnChangePassword = new JButton();
        labelDeleteAccount = new JLabel();
        txtWarning = new JTextPane();
        btnDelete = new JButton();
        labelFormStatus = new JLabel();

        //======== this ========
        setTitle("User Details");
        var contentPane = getContentPane();

        //======== panelProfile ========
        {
            panelProfile.setBorder(new TitledBorder(new LineBorder(new Color(0x999999), 2, true), "Profile", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 28), new Color(0x999999)));
            panelProfile.setForeground(new Color(0x333333));
            panelProfile.setOpaque(false);
            panelProfile.setFont(new Font("Segoe UI", panelProfile.getFont().getStyle(), 12));

            //---- labelName ----
            labelName.setText("NAME");
            labelName.setFont(new Font("Segoe UI", labelName.getFont().getStyle() | Font.BOLD, 18));
            labelName.setForeground(new Color(0x666666));

            //---- txtName ----
            txtName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtName.setBorder(new LineBorder(new Color(0x666666), 1, true));
            txtName.setText("Nguyen Hoang Duy");
            txtName.setForeground(new Color(0x666666));
            txtName.setOpaque(false);
            txtName.setDisabledTextColor(new Color(0x666666));
            txtName.setPreferredSize(new Dimension(191, 42));
            txtName.setMinimumSize(new Dimension(61, 42));

            //---- labelDoB ----
            labelDoB.setText("DoB");
            labelDoB.setFont(new Font("Segoe UI", labelDoB.getFont().getStyle() | Font.BOLD, 18));
            labelDoB.setForeground(new Color(0x666666));

            //---- txtEmail ----
            txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtEmail.setBorder(new LineBorder(new Color(0x666666), 1, true));
            txtEmail.setText("admin@gmail.com");
            txtEmail.setForeground(new Color(0x666666));
            txtEmail.setOpaque(false);
            txtEmail.setDisabledTextColor(new Color(0x666666));
            txtEmail.setMinimumSize(new Dimension(61, 42));
            txtEmail.setPreferredSize(new Dimension(183, 42));

            //---- labelEmail ----
            labelEmail.setText("EMAIL");
            labelEmail.setFont(new Font("Segoe UI", labelEmail.getFont().getStyle() | Font.BOLD, 18));
            labelEmail.setForeground(new Color(0x666666));

            //---- txtPhone ----
            txtPhone.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            txtPhone.setBorder(new LineBorder(new Color(0x666666), 1, true));
            txtPhone.setText("0902529803");
            txtPhone.setForeground(new Color(0x666666));
            txtPhone.setOpaque(false);
            txtPhone.setDisabledTextColor(new Color(0x666666));
            txtPhone.setMinimumSize(new Dimension(61, 42));
            txtPhone.setPreferredSize(new Dimension(133, 42));

            //---- labelPhone ----
            labelPhone.setText("PHONE");
            labelPhone.setFont(new Font("Segoe UI", labelPhone.getFont().getStyle() | Font.BOLD, 18));
            labelPhone.setForeground(new Color(0x666666));

            //---- labelRole ----
            labelRole.setText("ROLE");
            labelRole.setFont(new Font("Segoe UI", labelRole.getFont().getStyle() | Font.BOLD, 18));
            labelRole.setForeground(new Color(0x666666));

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

            //---- btnResetAvatar ----
            btnResetAvatar.setText("RESET AVATAR");
            btnResetAvatar.setFont(new Font("Segoe UI", btnResetAvatar.getFont().getStyle() | Font.BOLD, 20));
            btnResetAvatar.setBackground(SystemColor.control);
            btnResetAvatar.setForeground(new Color(0x333333));
            btnResetAvatar.setBorder(new LineBorder(new Color(0x333333), 1, true));
            btnResetAvatar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnResetAvatarMouseClicked(e);
                }
            });

            //---- btnSave ----
            btnSave.setText("SAVE");
            btnSave.setFont(new Font("Segoe UI", btnSave.getFont().getStyle() | Font.BOLD, 18));
            btnSave.setBackground(new Color(0x0066cc));
            btnSave.setForeground(Color.white);
            btnSave.setMaximumSize(new Dimension(172, 38));
            btnSave.setMinimumSize(new Dimension(172, 38));
            btnSave.setPreferredSize(new Dimension(172, 38));
            btnSave.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnSaveMouseClicked(e);
                }
            });

            //---- btnReset ----
            btnReset.setText("RESET");
            btnReset.setFont(new Font("Segoe UI", btnReset.getFont().getStyle() | Font.BOLD, 20));
            btnReset.setBackground(SystemColor.control);
            btnReset.setForeground(new Color(0x333333));
            btnReset.setBorder(new LineBorder(new Color(0x333333), 1, true));
            btnReset.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnResetMouseClicked(e);
                }
            });

            //---- cbRole ----
            cbRole.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            cbRole.setBorder(new LineBorder(new Color(0x666666), 1, true));
            cbRole.setModel(new DefaultComboBoxModel<>(new String[] {
                "Admin",
                "Manager",
                "Employee"
            }));
            cbRole.setMinimumSize(new Dimension(98, 42));
            cbRole.setPreferredSize(new Dimension(145, 42));
            cbRole.setForeground(new Color(0x666666));
            cbRole.setOpaque(false);

            //---- cbStatus ----
            cbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            cbStatus.setBorder(new LineBorder(new Color(0x666666), 1, true));
            cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {
                "Normal",
                "Locked"
            }));
            cbStatus.setMinimumSize(new Dimension(98, 42));
            cbStatus.setPreferredSize(new Dimension(126, 42));
            cbStatus.setForeground(new Color(0x666666));
            cbStatus.setOpaque(false);

            GroupLayout panelProfileLayout = new GroupLayout(panelProfile);
            panelProfile.setLayout(panelProfileLayout);
            panelProfileLayout.setHorizontalGroup(
                panelProfileLayout.createParallelGroup()
                    .addGroup(panelProfileLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addComponent(labelPhone)
                            .addComponent(labelStatus)
                            .addComponent(labelRole)
                            .addComponent(labelEmail)
                            .addComponent(labelName)
                            .addComponent(labelDoB))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(cbRole, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(cbStatus, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtPhone, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(dcDoB, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                        .addGap(57, 57, 57)
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnResetAvatar, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(btnReset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
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
                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelEmail)
                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelPhone)
                                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(dcDoB, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDoB))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelRole)
                                    .addComponent(cbRole, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelStatus)
                                    .addComponent(cbStatus, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(38, Short.MAX_VALUE))
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addComponent(panelAvatar, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btnResetAvatar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))))
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
            txtNewPassword.setOpaque(false);
            txtNewPassword.setPreferredSize(new Dimension(61, 42));
            txtNewPassword.setMinimumSize(new Dimension(61, 42));
            txtNewPassword.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    txtNewPasswordEnterKeyPressed(e);
                }
            });

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

            //---- labelDeleteAccount ----
            labelDeleteAccount.setText("Delete Account");
            labelDeleteAccount.setFont(new Font("Segoe UI", labelDeleteAccount.getFont().getStyle() | Font.BOLD, labelDeleteAccount.getFont().getSize() + 1));
            labelDeleteAccount.setForeground(Color.red);

            //---- txtWarning ----
            txtWarning.setFont(new Font("Segoe UI", txtWarning.getFont().getStyle() | Font.BOLD, 12));
            txtWarning.setText("Once you delete the account, there is no going back. Please be certain.");
            txtWarning.setBorder(null);
            txtWarning.setOpaque(false);

            //---- btnDelete ----
            btnDelete.setText("DELETE ACCOUNT");
            btnDelete.setFont(new Font("Segoe UI Light", btnDelete.getFont().getStyle(), 18));
            btnDelete.setBackground(Color.red);
            btnDelete.setForeground(Color.white);
            btnDelete.setBorder(new LineBorder(Color.red, 1, true));
            btnDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnDeleteMouseClicked(e);
                }
            });

            GroupLayout panelSecurityLayout = new GroupLayout(panelSecurity);
            panelSecurity.setLayout(panelSecurityLayout);
            panelSecurityLayout.setHorizontalGroup(
                panelSecurityLayout.createParallelGroup()
                    .addGroup(panelSecurityLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panelSecurityLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelNewPassword)
                            .addComponent(txtNewPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnChangePassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelDeleteAccount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtWarning, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))
            );
            panelSecurityLayout.setVerticalGroup(
                panelSecurityLayout.createParallelGroup()
                    .addGroup(panelSecurityLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(labelNewPassword)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNewPassword, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChangePassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(labelDeleteAccount, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWarning, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
            );
        }

        //---- labelFormStatus ----
        labelFormStatus.setText("... Editing");
        labelFormStatus.setFont(new Font("Segoe UI", labelFormStatus.getFont().getStyle() | Font.BOLD, labelFormStatus.getFont().getSize() + 7));
        labelFormStatus.setForeground(new Color(0x0066cc));
        labelFormStatus.setHorizontalAlignment(SwingConstants.RIGHT);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(panelProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(labelFormStatus)
                        .addComponent(panelSecurity, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(33, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(panelSecurity, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelFormStatus)))
                    .addContainerGap(27, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panelProfile;
    private JLabel labelName;
    private JTextField txtName;
    private JLabel labelDoB;
    private JTextField txtEmail;
    private JLabel labelEmail;
    private JTextField txtPhone;
    private JLabel labelPhone;
    private JLabel labelRole;
    private JLabel labelStatus;
    private JPanel panelAvatar;
    private JLabel avatarBox;
    private JPanel hSpacer1;
    private JPanel hSpacer2;
    private JPanel vSpacer1;
    private JPanel vSpacer2;
    private JButton btnResetAvatar;
    private JButton btnSave;
    private JButton btnReset;
    private JComboBox<String> cbRole;
    private JComboBox<String> cbStatus;
    private JDateChooser dcDoB;
    private JPanel panelSecurity;
    private JLabel labelNewPassword;
    private JPasswordField txtNewPassword;
    private JButton btnChangePassword;
    private JLabel labelDeleteAccount;
    private JTextPane txtWarning;
    private JButton btnDelete;
    private JLabel labelFormStatus;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
