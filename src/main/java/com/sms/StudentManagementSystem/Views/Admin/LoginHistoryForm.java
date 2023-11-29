/*
 * Created by JFormDesigner on Fri Nov 17 13:46:15 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Admin;

import com.sms.StudentManagementSystem.Controllers.LoginHistoryController;
import com.sms.StudentManagementSystem.Models.LoginHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * @author hoang
 */
@Component
public class LoginHistoryForm extends JFrame {
    private Iterable<LoginHistory> loginHistories;

    private LoginHistoryController loginHistoryController;

    @Autowired
    public LoginHistoryForm(LoginHistoryController loginHistoryController) {
        this.loginHistoryController = loginHistoryController;
        if (GraphicsEnvironment.isHeadless()) System.out.println("Headless mode");
        else initComponents();
    }

    public void loadTable() {
        loginHistories = loginHistoryController.getAll();
        DefaultTableModel model = (DefaultTableModel) tableMain.getModel();
        model.setRowCount(0);
        for (LoginHistory loginHistory : loginHistories)
            model.addRow(new Object[]{loginHistory.getId(), loginHistory.getUser().getEmail(), loginHistory.getAccessed().toString()});
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        tableMain = new JTable();

        //======== this ========
        setTitle("Login History");
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {
            scrollPane1.setFont(new Font("Segoe UI", Font.BOLD, 16));

            //---- tableMain ----
            tableMain.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            tableMain.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null},
                    {null, null, null},
                },
                new String[] {
                    "ID", "EMAIL", "ACCESSED"
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    Integer.class, String.class, String.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false, false
                };
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = tableMain.getColumnModel();
                cm.getColumn(0).setMinWidth(35);
                cm.getColumn(0).setMaxWidth(35);
                cm.getColumn(1).setMinWidth(225);
                cm.getColumn(1).setPreferredWidth(225);
                cm.getColumn(2).setMinWidth(175);
            }
            tableMain.setForeground(new Color(0x333333));
            scrollPane1.setViewportView(tableMain);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(16, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable tableMain;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
