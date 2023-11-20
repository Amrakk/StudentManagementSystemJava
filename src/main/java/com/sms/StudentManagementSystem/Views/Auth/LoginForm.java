/*
 * Created by JFormDesigner on Fri Nov 17 13:43:57 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Auth;

import com.sms.StudentManagementSystem.Controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author hoang
 */
@Component
public class LoginForm extends JFrame {
    @Autowired
    private UserController userController;

    public LoginForm() {
        if (GraphicsEnvironment.isHeadless()) System.out.println("Headless mode");
        else initComponents();
    }

    private void btnLoginMouseClicked(MouseEvent e) {
        String email = txtEmail.getText();
        String password = String.valueOf(txtPassword.getPassword());

        boolean isValid = userController.login(email, password, this);
        setVisible(!isValid);
    }

    private void btnLoginEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnLoginMouseClicked(null);
    }

    private void txtPasswordEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnLoginMouseClicked(null);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        labelEmail = new JLabel();
        txtEmail = new JTextField();
        labelPassword = new JLabel();
        btnLogin = new JButton();
        txtPassword = new JPasswordField();

        //======== this ========
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Login to SMS");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 55));
        label1.setAutoscrolls(true);
        label1.setHorizontalTextPosition(SwingConstants.CENTER);
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- labelEmail ----
        labelEmail.setText("Email");
        labelEmail.setFont(new Font("Segoe UI", labelEmail.getFont().getStyle(), labelEmail.getFont().getSize() + 10));

        //---- txtEmail ----
        txtEmail.setFont(new Font("Segoe UI", txtEmail.getFont().getStyle(), txtEmail.getFont().getSize() + 12));

        //---- labelPassword ----
        labelPassword.setText("Password");
        labelPassword.setFont(new Font("Segoe UI", labelPassword.getFont().getStyle(), labelPassword.getFont().getSize() + 10));

        //---- btnLogin ----
        btnLogin.setText("LOGIN");
        btnLogin.setFont(new Font("Segoe UI", btnLogin.getFont().getStyle() | Font.BOLD, btnLogin.getFont().getSize() + 12));
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnLoginMouseClicked(e);
            }
        });
        btnLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                btnLoginEnterKeyPressed(e);
            }
        });

        //---- txtPassword ----
        txtPassword.setFont(new Font("Segoe UI", txtPassword.getFont().getStyle(), txtPassword.getFont().getSize() + 12));
        txtPassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                txtPasswordEnterKeyPressed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(labelPassword)
                                .addComponent(labelEmail))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                                .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)))
                    .addGap(34, 34, 34))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(label1)
                    .addGap(39, 39, 39)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelEmail)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(50, 50, 50)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelPassword)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(41, 41, 41)
                    .addComponent(btnLogin)
                    .addContainerGap(42, Short.MAX_VALUE))
        );
        setSize(500, 475);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel labelEmail;
    private JTextField txtEmail;
    private JLabel labelPassword;
    private JButton btnLogin;
    private JPasswordField txtPassword;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
