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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
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

    public void loadTable(String text) {
        users = userController.searchByName(text);
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        model.setRowCount(0);
        for (User user : users)
            model.addRow(new Object[]{user.getEmail(), user.getName(), user.getDob(), user.getPhone(), user.getStatus(), user.getRole()});
    }

    // Add event mouse released for this
    private void tblUserMouseDoubleClicked(MouseEvent e) {
        int row = tblUser.getSelectedRow();
        if (e.getClickCount() == 2 && row >= 0) {
            String email = tblUser.getValueAt(row, 0).toString();
            User user = userController.getUserByEmail(email);
            if (user != null)
                mainForm.openUserDetailForm(user);
        }
    }

    private void btnCreateMouseClicked(MouseEvent e) {
        mainForm.openUserDetailForm(null);
    }

    private void btnCreateEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnCreateMouseClicked(null);
    }

    private void txtSearchFocusGained(FocusEvent e) {
        if (txtSearch.getText().equals("Search by Name"))
            txtSearch.setText("");
    }

    private void txtSearchFocusLost(FocusEvent e) {
        if (txtSearch.getText().isEmpty())
            txtSearch.setText("Search by Name");
    }

    private void btnSearchMouseClicked(MouseEvent e) {
        if (txtSearch.getText().equals("Search by Name")) loadTable();
        else loadTable(txtSearch.getText());
    }

    private void txtSearchEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnSearchMouseClicked(null);
    }

    private void btnSearchEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnSearchMouseClicked(null);
    }

    private void btnExportMouseClicked(MouseEvent e) {
        // export to excel or csv
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel/CSV file", "xlsx", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if (userController.exportFile(path))
                JOptionPane.showMessageDialog(null, "Export successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Export failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnExportEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnExportMouseClicked(null);
    }

    private void btnImportMouseClicked(MouseEvent e) {
        // import from excel or csv
        int result = JOptionPane.showConfirmDialog(null, "This will remove all user, login history data! Please be certain!", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
        if (result != JOptionPane.YES_OPTION) return;

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel/CSV file", "xlsx", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Specify a file to import");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if (userController.importFile(path)) {
                JOptionPane.showMessageDialog(null, "Users imported successfully!\n\nUser new password will be their phone number", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTable();
            } else
                JOptionPane.showMessageDialog(null, "Import failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnImportEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnImportMouseClicked(null);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Tri
        labelHeader = new JLabel();
        scrollPane1 = new JScrollPane();
        tblUser = new JTable();
        txtSearch = new JTextField();
        btnSearch = new JButton();
        btnImport = new JButton();
        btnExport = new JButton();
        btnCreate = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(1498, 756));
        setMinimumSize(new Dimension(894, 757));
        setBackground(SystemColor.control);
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
        . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder
        . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .
        awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder( )) )
        ;  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;

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
        txtSearch.setText("Search by Name");
        txtSearch.setBorder(new LineBorder(new Color(0x333333)));
        txtSearch.setOpaque(false);
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtSearchFocusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                txtSearchFocusLost(e);
            }
        });
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                txtSearchEnterKeyPressed(e);
            }
        });

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
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSearchMouseClicked(e);
            }
        });
        btnSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                btnSearchEnterKeyPressed(e);
            }
        });

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
        btnImport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnImportMouseClicked(e);
            }
        });
        btnImport.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                btnImportEnterKeyPressed(e);
            }
        });

        //---- btnExport ----
        btnExport.setText("EXPORT");
        btnExport.setBorder(new LineBorder(new Color(0x666666), 1, true));
        btnExport.setFont(new Font("Segoe UI", btnExport.getFont().getStyle() | Font.BOLD, 16));
        btnExport.setForeground(new Color(0x666666));
        btnExport.setOpaque(false);
        btnExport.setBackground(SystemColor.control);
        btnExport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnExportMouseClicked(e);
            }
        });
        btnExport.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                btnExportEnterKeyPressed(e);
            }
        });

        //---- btnCreate ----
        btnCreate.setText("CREATE");
        btnCreate.setPreferredSize(new Dimension(118, 38));
        btnCreate.setMaximumSize(new Dimension(118, 38));
        btnCreate.setMinimumSize(new Dimension(118, 38));
        btnCreate.setBorder(null);
        btnCreate.setBackground(new Color(0x0066cc));
        btnCreate.setFont(new Font("Segoe UI", btnCreate.getFont().getStyle() | Font.BOLD, 16));
        btnCreate.setForeground(Color.white);
        btnCreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnCreateMouseClicked(e);
            }
        });
        btnCreate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                btnCreateEnterKeyPressed(e);
            }
        });

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
                                .addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
                    .addGap(54, 54, 54))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Tri
    private JLabel labelHeader;
    private JScrollPane scrollPane1;
    private JTable tblUser;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnImport;
    private JButton btnExport;
    private JButton btnCreate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
