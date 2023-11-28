/*
 * Created by JFormDesigner on Tue Nov 21 14:41:36 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Admin;

import com.sms.StudentManagementSystem.Controllers.UserController;
import com.sms.StudentManagementSystem.Models.User;
import com.sms.StudentManagementSystem.Views.MainForm;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * @author hoang
 */
@Component
public class UserPanel extends JPanel {
    private Iterable<User> users;

    @Setter
    private UserController userController;

    @Setter
    private MainForm mainForm;

    public UserPanel() {
        if (GraphicsEnvironment.isHeadless()) System.out.println("Headless mode");
        else initComponents();
    }

    public void loadTable() {
        users = userController.getAll();
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        model.setRowCount(0);
        for (User user : users)
            model.addRow(new Object[]{user.getEmail(), user.getName(), user.getDob(), user.getPhone(), user.getStatus(), user.getRole()});
    }

    // Add event mouse leaved for this
    private void tblUserMouseDoubleClicked(MouseEvent e) {
        int row = tblUser.getSelectedRow();
        if (e.getClickCount() == 2 && row >= 0) {
            String email = tblUser.getValueAt(row, 0).toString();
            User user = userController.getUserByEmail(email);
            if (user != null) {
                JOptionPane.showMessageDialog(this, user.toString(), "User Information", JOptionPane.INFORMATION_MESSAGE);
                mainForm.openUserDetailForm(user);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        labelHeader = new JLabel();
        scrollPane1 = new JScrollPane();
        tblUser = new JTable();
        txtSearch = new JTextField();
        btnSearch = new JButton();
        btnFilter = new JButton();
        btnImport = new JButton();
        btnExport = new JButton();
        btnCreate = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(1498, 756));
        setMinimumSize(new Dimension(894, 757));
        setBackground(SystemColor.control);

        //---- labelHeader ----
        labelHeader.setText("User");
        labelHeader.setBackground(new Color(0x990099));
        labelHeader.setForeground(new Color(0x333333));
        labelHeader.setFont(new Font("Segoe UI", Font.BOLD, 36));

        //======== scrollPane1 ========
        {
            scrollPane1.setFont(new Font("Segoe UI", scrollPane1.getFont().getStyle(), 18));
            scrollPane1.setForeground(Color.black);

            //---- tblUser ----
            tblUser.setForeground(new Color(0x333333));
            tblUser.setFont(new Font("Segoe UI", tblUser.getFont().getStyle(), 16));
            tblUser.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "Email", "Name", "Date of Birth", "Phone", "Status", "Role"
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    String.class, String.class, Date.class, String.class, String.class, String.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false, false
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
                TableColumnModel cm = tblUser.getColumnModel();
                cm.getColumn(0).setResizable(false);
                cm.getColumn(0).setMinWidth(315);
                cm.getColumn(0).setMaxWidth(315);
                cm.getColumn(0).setPreferredWidth(315);
                cm.getColumn(1).setResizable(false);
                cm.getColumn(1).setMinWidth(300);
                cm.getColumn(1).setMaxWidth(300);
                cm.getColumn(1).setPreferredWidth(300);
                cm.getColumn(2).setResizable(false);
                cm.getColumn(2).setMinWidth(180);
                cm.getColumn(2).setMaxWidth(180);
                cm.getColumn(2).setPreferredWidth(180);
                cm.getColumn(3).setResizable(false);
                cm.getColumn(3).setMinWidth(175);
                cm.getColumn(3).setMaxWidth(175);
                cm.getColumn(3).setPreferredWidth(175);
                cm.getColumn(4).setResizable(false);
                cm.getColumn(4).setMinWidth(145);
                cm.getColumn(4).setMaxWidth(145);
                cm.getColumn(4).setPreferredWidth(145);
                cm.getColumn(5).setResizable(false);
                cm.getColumn(5).setMinWidth(140);
                cm.getColumn(5).setMaxWidth(140);
                cm.getColumn(5).setPreferredWidth(140);
            }
            tblUser.setFillsViewportHeight(true);
            tblUser.setRowHeight(34);
            tblUser.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    tblUserMouseDoubleClicked(e);
                }
            });
            scrollPane1.setViewportView(tblUser);
        }

        //---- txtSearch ----
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtSearch.setMinimumSize(new Dimension(60, 40));
        txtSearch.setPreferredSize(new Dimension(60, 40));
        txtSearch.setForeground(new Color(0x333333));
        txtSearch.setText("Searh by Name");
        txtSearch.setBorder(new LineBorder(new Color(0x333333)));
        txtSearch.setOpaque(false);

        //---- btnSearch ----
        btnSearch.setText("FIND");
        btnSearch.setMaximumSize(new Dimension(110, 40));
        btnSearch.setMinimumSize(new Dimension(110, 40));
        btnSearch.setPreferredSize(new Dimension(110, 40));
        btnSearch.setFont(new Font("Segoe UI", btnSearch.getFont().getStyle() | Font.BOLD, 16));
        btnSearch.setForeground(new Color(0x666666));
        btnSearch.setBorder(new LineBorder(new Color(0x666666)));
        btnSearch.setOpaque(false);
        btnSearch.setBackground(SystemColor.control);

        //---- btnFilter ----
        btnFilter.setText("FILTER");
        btnFilter.setMinimumSize(new Dimension(110, 40));
        btnFilter.setMaximumSize(new Dimension(110, 40));
        btnFilter.setPreferredSize(new Dimension(110, 40));
        btnFilter.setBorder(new LineBorder(new Color(0x666666)));
        btnFilter.setOpaque(false);
        btnFilter.setForeground(new Color(0x666666));
        btnFilter.setFont(new Font("Segoe UI", btnFilter.getFont().getStyle() | Font.BOLD, 16));
        btnFilter.setBackground(SystemColor.control);

        //---- btnImport ----
        btnImport.setText("IMPORT");
        btnImport.setPreferredSize(new Dimension(118, 38));
        btnImport.setMaximumSize(new Dimension(118, 38));
        btnImport.setMinimumSize(new Dimension(118, 38));
        btnImport.setFont(new Font("Segoe UI", btnImport.getFont().getStyle() | Font.BOLD, 16));
        btnImport.setBorder(new LineBorder(new Color(0x666666), 1, true));
        btnImport.setOpaque(false);
        btnImport.setForeground(new Color(0x666666));
        btnImport.setBackground(SystemColor.control);

        //---- btnExport ----
        btnExport.setText("EXPORT");
        btnExport.setBorder(new LineBorder(new Color(0x666666), 1, true));
        btnExport.setFont(new Font("Segoe UI", btnExport.getFont().getStyle() | Font.BOLD, 16));
        btnExport.setForeground(new Color(0x666666));
        btnExport.setOpaque(false);
        btnExport.setBackground(SystemColor.control);

        //---- btnCreate ----
        btnCreate.setText("CREATE");
        btnCreate.setPreferredSize(new Dimension(118, 38));
        btnCreate.setMaximumSize(new Dimension(118, 38));
        btnCreate.setMinimumSize(new Dimension(118, 38));
        btnCreate.setBorder(null);
        btnCreate.setBackground(new Color(0x0066cc));
        btnCreate.setFont(new Font("Segoe UI", btnCreate.getFont().getStyle() | Font.BOLD, 16));
        btnCreate.setForeground(Color.white);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 1255, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelHeader)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
                            .addGap(12, 12, 12)
                            .addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelHeader)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
                    .addGap(54, 54, 54))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel labelHeader;
    private JScrollPane scrollPane1;
    private JTable tblUser;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnFilter;
    private JButton btnImport;
    private JButton btnExport;
    private JButton btnCreate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
