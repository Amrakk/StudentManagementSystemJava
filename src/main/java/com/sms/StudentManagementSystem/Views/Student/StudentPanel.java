/*
 * Created by JFormDesigner on Mon Nov 20 19:39:53 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Student;

import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.sms.StudentManagementSystem.Controllers.StudentController;
import com.sms.StudentManagementSystem.Models.Department;
import com.sms.StudentManagementSystem.Models.Major;
import com.sms.StudentManagementSystem.Models.Student;
import com.sms.StudentManagementSystem.Models.User;
import com.sms.StudentManagementSystem.Views.MainForm;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author hoang
 */
@Component
public class StudentPanel extends JPanel {

    @Setter
    private User user;

    public StudentPanel() {
        if (GraphicsEnvironment.isHeadless()) System.out.println("Headless mode");
        else initComponents();

        tblStudent.getColumnModel().getColumn(7).setCellRenderer(new DepartmentObjectTableRenderer());
        tblStudent.getColumnModel().getColumn(8).setCellRenderer(new MajorObjectTableRenderer());
    }

    public Iterable<Student> students;

    @Setter
    private StudentController studentController;

    @Setter
    private MainForm mainForm;

    private void txtSearchFocusGained(FocusEvent e) {
        // TODO add your code here
        if (txtSearch.getText().equals("Search by Name"))
            txtSearch.setText("");
    }

    private void txtSearchFocusLost(FocusEvent e) {
        // TODO add your code here
        if (txtSearch.getText().isEmpty())
            txtSearch.setText("Search by Name");
    }

    private void txtSearchEnterKeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnSearchMouseClicked(null);

    }

    private void btnSearchMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (txtSearch.getText().equals("Search by Name")) loadTable();
        else loadTable(txtSearch.getText());
    }

    private void btnSearchEnterKeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnSearchMouseClicked(null);
    }

    public void loadTable() {
        students = studentController.getAll();
        DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
        model.setRowCount(0);
        for (Student student : students)
            model.addRow(new Object[]{student.getId(), student.getName(), student.getDob(), student.getGender(), student.getEduType(), student.getCourseYear(), student.getClassName(), student.getDepartment(), student.getMajor()});
    }

    public void loadTable(String text) {
        students = studentController.getByName(text);
        DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
        model.setRowCount(0);
        for (Student student : students)
            model.addRow(new Object[]{student.getId(), student.getName(), student.getDob(), student.getGender(), student.getEduType(), student.getCourseYear(), student.getClassName(), student.getDepartment(), student.getMajor()});
    }

    private void btnImportMouseClicked(MouseEvent e) {
        // TODO add your code here
        int result = JOptionPane.showConfirmDialog(null, "This will remove all student! Please be certain!", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
        if (result != JOptionPane.YES_OPTION) return;

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel/CSV file", "xlsx", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Specify a file to import");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if (studentController.importFile(path)) {
                JOptionPane.showMessageDialog(null, "Students imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTable();
            } else
                JOptionPane.showMessageDialog(null, "Import failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnImportEnterKeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnImportMouseClicked(null);
    }

    private void btnExportMouseClicked(MouseEvent e) {
        // TODO add your code here
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel/CSV file", "xlsx", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if (studentController.exportFile(path))
                JOptionPane.showMessageDialog(null, "Export successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Export failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnExportEnterKeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnExportMouseClicked(null);
    }

    private void btnCreateMouseClicked(MouseEvent e) {
        if(user.getRole().equals("Employee")) {
            JOptionPane.showMessageDialog(null, "You are not authorized to do this operation", "Unauthorized", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(mainForm != null)
            mainForm.showAddForm();
    }

    private void btnCreateEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnCreateMouseClicked(null);
    }

    private void tblStudentMouseDoubleClick(MouseEvent e) {
        int row = tblStudent.getSelectedRow();
        if (e.getClickCount() == 2 && row >= 0) {
            String id = tblStudent.getValueAt(row, 0).toString();
            Student student = studentController.getStudentById(id);
            if (student != null){
                mainForm.openStudentDetailForm(student);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Tri
        labelHeader = new JLabel();
        txtSearch = new JTextField();
        btnSearch = new JButton();
        btnExport = new JButton();
        btnImport = new JButton();
        btnCreate = new JButton();
        scrollPane1 = new JScrollPane();
        tblStudent = new JTable();

        //======== this ========
        setPreferredSize(new Dimension(1498, 756));
        setMinimumSize(new Dimension(894, 757));
        setBackground(SystemColor.control);
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
        ( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border
        . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
        propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );

        //---- labelHeader ----
        labelHeader.setText("Student");
        labelHeader.setBackground(new Color(0x990099));
        labelHeader.setForeground(new Color(0x333333));
        labelHeader.setFont(new Font("Segoe UI", Font.BOLD, 36));

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

        //---- btnCreate ----
        btnCreate.setText("CREATE");
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

        //======== scrollPane1 ========
        {

            //---- tblStudent ----
            tblStudent.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, "", null, null, null, null, null, null, null},
                },
                new String[] {
                    "Id", "Name", "DoB", "Gender", "EduType", "CourseYear", "ClassName", "Department", "Major"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false, false, false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            tblStudent.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    tblStudentMouseDoubleClick(e);
                }
            });
            scrollPane1.setViewportView(tblStudent);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(labelHeader)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 1017, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                    .addGap(194, 194, 194)
                                    .addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(labelHeader)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Tri
    private JLabel labelHeader;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnExport;
    private JButton btnImport;
    private JButton btnCreate;
    private JScrollPane scrollPane1;
    private JTable tblStudent;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

class DepartmentObjectTableRenderer extends DefaultTableCellRenderer {
    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Assuming the value is an instance of Department
        if (value instanceof Department) {
            Department department = (Department) value;
            value = department.getName();  // Display the department name
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

class MajorObjectTableRenderer extends DefaultTableCellRenderer {
    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Assuming the value is an instance of Department
        if (value instanceof Major) {
            Major major = (Major) value;
            value = major.getName();  // Display the department name
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
