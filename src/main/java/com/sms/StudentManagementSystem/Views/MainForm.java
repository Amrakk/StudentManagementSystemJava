/*
 * Created by JFormDesigner on Fri Nov 17 13:42:56 ICT 2023
 */

package com.sms.StudentManagementSystem.Views;

import com.sms.StudentManagementSystem.Controllers.FormController;
import com.sms.StudentManagementSystem.Models.User;
import com.sms.StudentManagementSystem.Views.Admin.LoginHistoryForm;
import com.sms.StudentManagementSystem.Views.Auth.LoginForm;
import lombok.Getter;
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
public class MainForm extends JFrame {
    private User user;

    private LoginForm loginForm;
    private CardLayout card;

    @Autowired
    private FormController formController;

    public MainForm() {
        if (GraphicsEnvironment.isHeadless()) System.out.println("Headless mode");
        else initComponents();

        card = (CardLayout) panelBody.getLayout();
        card.show(panelBody, "StudentPanel");
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null)
            btnProfile.setText(user.getName());
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    private void btnLogoMouseClicked(MouseEvent e) {
        card.show(panelBody, "StudentPanel");
    }

    private void btnProfileMouseClicked(MouseEvent e) {
        card.show(panelBody, "ProfilePanel");
        ProfilePanel profilePanel = (ProfilePanel) panelBody.getComponent(1);
        profilePanel.panelLoad();
    }

    public void loadUserForm() {
        card.show(panelBody, "UserPanel");
    }

    private void btnLogoutMouseClicked(MouseEvent e) {
        loginForm.setVisible(true);
        this.dispose();
    }

    private void btnLogoEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) btnLogoMouseClicked(null);
    }

    private void btnProfileEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) btnProfileMouseClicked(null);
    }

    private void btnLogoutEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) btnLogoutMouseClicked(null);
    }

    public void openLoginHistoryForm() {
        LoginHistoryForm loginHistoryForm = formController.getLoginHistoryForm();
        loginHistoryForm.setVisible(true);
        loginHistoryForm.loadTable();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panelHeader = new JPanel();
        btnLogo = new JButton();
        labelWelcome = new JLabel();
        btnProfile = new JButton();
        btnLogout = new JButton();
        panelBody = new JPanel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Form");
        setPreferredSize(new Dimension(1500, 900));
        var contentPane = getContentPane();

        //======== panelHeader ========
        {
            panelHeader.setBackground(new Color(0x0066ff));
            panelHeader.setForeground(Color.white);

            //---- btnLogo ----
            btnLogo.setText("SMS");
            btnLogo.setFont(new Font("Segoe UI", btnLogo.getFont().getStyle() | Font.BOLD, 54));
            btnLogo.setBackground(new Color(0x0066ff));
            btnLogo.setForeground(Color.white);
            btnLogo.setBorder(null);
            btnLogo.setMultiClickThreshhold(200L);
            btnLogo.setNextFocusableComponent(btnProfile);
            btnLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnLogoMouseClicked(e);
                }
            });
            btnLogo.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnLogoEnterKeyPressed(e);
                }
            });

            //---- labelWelcome ----
            labelWelcome.setText("Welcome,");
            labelWelcome.setForeground(Color.white);
            labelWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            //---- btnProfile ----
            btnProfile.setText("Nguyen Hoang Duy");
            btnProfile.setFont(new Font("Segoe UI", btnProfile.getFont().getStyle() | Font.BOLD, 22));
            btnProfile.setBackground(new Color(0x0066ff));
            btnProfile.setForeground(new Color(0xeeeeee));
            btnProfile.setBorder(null);
            btnProfile.setMaximumSize(new Dimension(320, 30));
            btnProfile.setMultiClickThreshhold(200L);
            btnProfile.setNextFocusableComponent(btnLogout);
            btnProfile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnProfileMouseClicked(e);
                }
            });
            btnProfile.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnProfileEnterKeyPressed(e);
                }
            });

            //---- btnLogout ----
            btnLogout.setBackground(new Color(0x0066ff));
            btnLogout.setIcon(new ImageIcon(getClass().getResource("/Images/logout.png")));
            btnLogout.setBorder(null);
            btnLogout.setNextFocusableComponent(btnLogo);
            btnLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnLogoutMouseClicked(e);
                }
            });
            btnLogout.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnLogoutEnterKeyPressed(e);
                }
            });

            GroupLayout panelHeaderLayout = new GroupLayout(panelHeader);
            panelHeader.setLayout(panelHeaderLayout);
            panelHeaderLayout.setHorizontalGroup(
                panelHeaderLayout.createParallelGroup()
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnLogo, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 790, Short.MAX_VALUE)
                        .addComponent(labelWelcome)
                        .addGap(2, 2, 2)
                        .addComponent(btnProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
            panelHeaderLayout.setVerticalGroup(
                panelHeaderLayout.createParallelGroup()
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelHeaderLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelWelcome)
                            .addComponent(btnLogo, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(btnProfile, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                        .addContainerGap(26, Short.MAX_VALUE)
                        .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
            );
        }

        //======== panelBody ========
        {
            panelBody.setLayout(new CardLayout());
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panelBody, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1498, Short.MAX_VALUE)
                .addComponent(panelHeader, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panelHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelBody, GroupLayout.PREFERRED_SIZE, 758, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panelHeader;
    private JButton btnLogo;
    private JLabel labelWelcome;
    private JButton btnProfile;
    private JButton btnLogout;
    @Getter private JPanel panelBody;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
