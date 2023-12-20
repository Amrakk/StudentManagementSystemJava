/*
 * Created by JFormDesigner on Fri Nov 17 13:46:58 ICT 2023
 */

package com.sms.StudentManagementSystem.Views.Student;

import com.sms.StudentManagementSystem.Controllers.CertificateController;
import com.sms.StudentManagementSystem.Controllers.DepartmentController;
import com.sms.StudentManagementSystem.Controllers.MajorController;
import com.sms.StudentManagementSystem.Controllers.StudentController;
import com.sms.StudentManagementSystem.Models.*;
import com.sms.StudentManagementSystem.Views.MainForm;
import com.toedter.calendar.JDateChooser;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author hoang
 */
@Component
public class StudentDetailForm extends JFrame {
    @Setter
    private User user;

    @Setter
    private Student student;

    @Setter
    private MainForm mainForm;

    @Setter
    private StudentController studentController;

    private List<Department> departments;

    @Setter
    private DepartmentController departmentController;

    private List<Major> majors;
    private Certificate certificate;
    private List<Certificate> certificates;

    @Setter
    private MajorController majorController;

    @Setter
    private CertificateController certificateController;

    public StudentDetailForm(Student student) {
        initComponents();
        this.student = student;
    }

    public void loadStudentDetail() {
        btnResetMouseClicked(null);
        loadTable();
        btnResetCerMouseClicked(null);
    }

    public void loadCertDetail() {
        textIDCer.setText(certificate.getId());
        textTITLE.setText(certificate.getTitle());
        textDESCRIPTION.setText(certificate.getDescription());
        textORGANIZATION.setText(certificate.getOrganization());
        checkValid.setSelected(certificate.getValid());
        dcISSUE.setDate(certificate.getIssuedDate());
        dcEXPIRED.setDate(certificate.getExpiredDate());
    }

    private void btnDeleteStudentMouseClicked(MouseEvent e) {
        if (user.getRole().equals("Employee")) {
            JOptionPane.showMessageDialog(null, "You are not authorized to do this operation", "Unauthorized", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student?", "Warning", JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) return;

        if (studentController.deleteS(student)) {
            studentController.commit();
            mainForm.studentPanelLoadTable();
            dispose();
        }
    }

    private void btnDeleteStudentEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnDeleteStudentMouseClicked(null);
    }

    private void btnResetMouseClicked(MouseEvent e) {
        departments = departmentController.findAll();
        majors = majorController.getAll();

        cbDepartment.setRenderer(new DepartmentObjectRenderer());
        cbMajor.setRenderer(new MajorObjectRenderer());

        cbDepartment.removeAllItems();
        cbMajor.removeAllItems();

        departments.forEach(d -> {
            cbDepartment.addItem(d);
        });
        majors.forEach(m -> {
            cbMajor.addItem(m);
        });

        textID.setText(student.getId());
        textNAME.setText(student.getName());
        dcDoB.setDate(student.getDob());
        if (student.getGender().equals("Male")) {
            rdMale.setSelected(true);
        } else {
            rdFemale.setSelected(true);
        }
        textCYear.setText(student.getCourseYear());
        textEduType.setText(student.getEduType());
        textClass.setText(student.getClassName());

        int departmentIndex = 0;
        for (int i = 0; i < cbDepartment.getItemCount(); i++) {
            Department item = (Department) cbDepartment.getItemAt(i);
            if (Objects.equals(item.getId(), student.getDepartment().getId())) {
                departmentIndex = i;
                break;
            }
        }

        cbDepartment.setSelectedIndex(departmentIndex);

        int majorIndex = 0;
        for (int i = 0; i < cbMajor.getItemCount(); i++) {
            Major item = (Major) cbMajor.getItemAt(i);
            if (Objects.equals(item.getId(), student.getMajor().getId())) {
                majorIndex = i;
                break;
            }
        }

        cbMajor.setSelectedIndex(majorIndex);
    }

    private void btnResetEnterKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnResetMouseClicked(null);
    }

    private void btnEditStudentMouseClicked(MouseEvent e) {
        if ("Employee".equals(user.getRole())) {
            JOptionPane.showMessageDialog(null, "You are not authorized to do this operation", "Unauthorized", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = textID.getText();
        String name = textNAME.getText();
        // Assuming inputDoB is a JDatePicker or similar component
        java.util.Date dobUtil = dcDoB.getDate();
        java.sql.Date dob = new java.sql.Date(dobUtil.getTime());
        String gender = (rdMale.isSelected()) ? "Male" : "Female";
        String eduType = textEduType.getText();
        Department department = (Department) cbDepartment.getSelectedItem();
        Major major = (Major) cbMajor.getSelectedItem();
        String className = textClass.getText();

        String courseYear = textCYear.getText();
        int parsedYear = 0;

        // Validation
        if (
                id.isEmpty() ||
                        name.isEmpty() ||
                        eduType.isEmpty() ||
                        courseYear.isEmpty()
        ) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Empty fields", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (department == null) {
            JOptionPane.showMessageDialog(null, "Please select a valid department!", "Invalid department", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (major == null) {
            JOptionPane.showMessageDialog(null, "Please select a valid major!", "Invalid major", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (className == null) {
            JOptionPane.showMessageDialog(null, "Please select a valid class", "Invalid class", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (dob.after(new java.util.Date())) {
            JOptionPane.showMessageDialog(null, "Date of birth must be less than current date!", "Invalid date", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Update student information
        student.setName(name);
        student.setDob(dob);
        student.setGender(gender);
        student.setEduType(eduType);
        student.setDepartment(department);
        student.setMajor(major);
        student.setClassName(className);
        student.setCourseYear(courseYear);

        try {
            studentController.updateS(student);
            mainForm.studentPanelLoadTable();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void btnStudentEditKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnEditStudentMouseClicked(null);
    }

    private void btnSaveCerMouseClicked(MouseEvent e) {
        if ("Employee".equals(user.getRole())) {
            JOptionPane.showMessageDialog(null, "You are not authorized to do this operation", "Unauthorized", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = textIDCer.getText();
        String title = textTITLE.getText();
        String desc = textDESCRIPTION.getText();
        Boolean isValid = checkValid.isSelected();
        Date issueD = dcISSUE.getDate();
        Date expiredD = dcEXPIRED.getDate();
        String organization = textORGANIZATION.getText();

        if (issueD == null ||
                expiredD == null ||
                id.isEmpty() ||
                title.isEmpty() ||
                desc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Empty fields", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (issueD.compareTo(expiredD) > 0) {
            JOptionPane.showMessageDialog(null, "Issue date must be less than expired date!", "Invalid date", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (certificate == null) {
            certificate = new Certificate(id, title, desc, issueD, expiredD, organization, isValid, student);
            if (certificateController.add(certificate)) {
                JOptionPane.showMessageDialog(null, "Certificate added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTable();
            } else
                JOptionPane.showMessageDialog(null, "Certificate added failed!", "Failed", JOptionPane.INFORMATION_MESSAGE);

            certificate = null;
        } else {
            certificate.setId(id);
            certificate.setTitle(title);
            certificate.setDescription(desc);
            certificate.setValid(isValid);
            certificate.setIssuedDate(issueD);
            certificate.setExpiredDate(expiredD);
            certificate.setOrganization(organization);

            certificateController.update(certificate);
        }
        loadTable();
        btnResetCerMouseClicked(null);
    }

    private void btnSaveCerKeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnSaveCerMouseClicked(null);
    }

    public void loadTable() {
        certificates = certificateController.getByStudentId(student.getId());

        DefaultTableModel model = (DefaultTableModel) tblCer.getModel();
        model.setRowCount(0);
        for (Certificate certificate : certificates) {
            model.addRow(new Object[]{certificate.getId(),
                    certificate.getTitle(),
                    certificate.getOrganization(),
                    certificate.getValid(),
                    certificate.getIssuedDate(),
                    certificate.getExpiredDate()});
        }
        tblCer.getColumn("id").setWidth(0);
        tblCer.getColumn("id").setMinWidth(0);
        tblCer.getColumn("id").setMaxWidth(0);
    }

    private void btnResetCerMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (certificate == null) {
//            add new cert
            textIDCer.setText("");
            textTITLE.setText("");
            textDESCRIPTION.setText("");
            textORGANIZATION.setText("");
            dcISSUE.setDate(Date.from(Instant.now()));
            dcEXPIRED.setDate(Date.from(Instant.now()));
            checkValid.setSelected(false);

            btnSaveCer.setText("Add");
            textIDCer.setEnabled(true);
        } else {
//            edit cert
            textIDCer.setText(certificate.getId());
            textTITLE.setText(certificate.getTitle());
            textDESCRIPTION.setText(certificate.getDescription());
            checkValid.setSelected(certificate.getValid());
            dcISSUE.setDate(certificate.getIssuedDate());
            dcEXPIRED.setDate(certificate.getExpiredDate());
            textORGANIZATION.setText(certificate.getOrganization());

            btnSaveCer.setText("Edit");
            textIDCer.setEnabled(false);
        }
    }

    private void btnResetCerKeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnResetCerMouseClicked(null);

    }

    private void tblCerMouseDoubleClicked(MouseEvent e) {
        int row = tblCer.getSelectedRow();
        String id = tblCer.getValueAt(row, 0).toString();

        if (e.getClickCount() == 2 && row >= 0) {
            certificate = certificateController.getById(id);
            if (certificate != null) loadCertDetail();
            btnResetCerMouseClicked(null);
        }
    }

    private void btnCreateMouseClicked(MouseEvent e) {
        if ("Employee".equals(user.getRole())) {
            JOptionPane.showMessageDialog(null, "You are not authorized to do this operation", "Unauthorized", JOptionPane.ERROR_MESSAGE);
            return;
        }

        certificate = null;
        btnResetCerMouseClicked(null);
    }

    private void btnCreateKeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnCreateMouseClicked(null);
    }

    private void btnImportMouseClicked(MouseEvent e) {
        // TODO add your code here
        int result = JOptionPane.showConfirmDialog(null, "This will remove all certificate! Please be certain!", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
        if (result != JOptionPane.YES_OPTION) return;

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel/CSV file", "xlsx", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Specify a file to import");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if (certificateController.importFile(path)) {
                JOptionPane.showMessageDialog(null, "Certificates imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTable();
            } else
                JOptionPane.showMessageDialog(null, "Import failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnImportKeyPressed(KeyEvent e) {
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
            if (certificateController.exportFile(path))
                JOptionPane.showMessageDialog(null, "Export successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Export failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnExportKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnExportMouseClicked(null);
    }

    private void btnDeleteCertificateKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnDeleteCertificateMouseClicked(null);
    }

    private void btnDeleteCertificateMouseClicked(MouseEvent e) {
        if (user.getRole().equals("Employee")) {
            JOptionPane.showMessageDialog(null, "You are not authorized to do this operation", "Unauthorized", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this certificate?", "Warning", JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) return;

        if (certificate == null) {
            JOptionPane.showMessageDialog(null, "Please select a specific certificate!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        certificateController.delete(certificate);

        certificate = null;
        btnResetCerMouseClicked(null);
        loadTable();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Duy Nguyen
        panelProfile = new JPanel();
        labelID = new JLabel();
        labelNAME = new JLabel();
        labelDoB = new JLabel();
        labelGender = new JLabel();
        labelCYear = new JLabel();
        labelDepartment = new JLabel();
        labelMajor = new JLabel();
        labelEduType = new JLabel();
        labelClass = new JLabel();
        textID = new JTextField();
        textNAME = new JTextField();
        textCYear = new JTextField();
        dcDoB = new JDateChooser();
        rdMale = new JRadioButton();
        rdFemale = new JRadioButton();
        cbDepartment = new JComboBox();
        cbMajor = new JComboBox();
        btnReset = new JButton();
        btnEdit = new JButton();
        textEduType = new JTextField();
        textClass = new JTextField();
        panelCertificate = new JPanel();
        labelIDCer = new JLabel();
        labelTITLE = new JLabel();
        labelDESCRIPTION = new JLabel();
        labelVALID = new JLabel();
        labelISSUE = new JLabel();
        labelEXPIRED = new JLabel();
        labelORGANIZATION = new JLabel();
        textIDCer = new JTextField();
        textTITLE = new JTextField();
        btnResetCer = new JButton();
        btnSaveCer = new JButton();
        textDESCRIPTION = new JTextField();
        textORGANIZATION = new JTextField();
        checkValid = new JCheckBox();
        dcISSUE = new JDateChooser();
        dcEXPIRED = new JDateChooser();
        scrollPane1 = new JScrollPane();
        tblCer = new JTable();
        btnExport = new JButton();
        btnImport = new JButton();
        btnCreate = new JButton();
        panelDeleteSTUDENT = new JPanel();
        labelDeleteStudent = new JLabel();
        txtWarningStudent = new JTextPane();
        btnDeleteStudent = new JButton();
        panelDeleteCERTIFICATE = new JPanel();
        labelDeleteCertificate = new JLabel();
        txtWarningCertificate = new JTextPane();
        btnDeleteCertificate = new JButton();

        //======== this ========
        setTitle("Student Details");
        setMinimumSize(new Dimension(14, 37));
        setPreferredSize(new Dimension(14, 37));
        setResizable(false);
        var contentPane = getContentPane();

        //======== panelProfile ========
        {
            panelProfile.setBackground(Color.white);
            panelProfile.setBorder(new TitledBorder("Profile"));
            panelProfile.setForeground(new Color(0x333333));
            panelProfile.setFont(new Font("Segoe UI", Font.BOLD, 20));
            panelProfile.setOpaque(false);
            panelProfile.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,panelProfile. getBorder( )) ); panelProfile. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //---- labelID ----
            labelID.setText("ID");
            labelID.setBackground(Color.black);
            labelID.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelID.setForeground(new Color(0x666666));

            //---- labelNAME ----
            labelNAME.setText("NAME");
            labelNAME.setBackground(Color.black);
            labelNAME.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelNAME.setForeground(new Color(0x666666));

            //---- labelDoB ----
            labelDoB.setText("DoB");
            labelDoB.setBackground(Color.black);
            labelDoB.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelDoB.setForeground(new Color(0x666666));

            //---- labelGender ----
            labelGender.setText("Gender");
            labelGender.setBackground(Color.black);
            labelGender.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelGender.setForeground(new Color(0x666666));

            //---- labelCYear ----
            labelCYear.setText("Course Year");
            labelCYear.setBackground(Color.black);
            labelCYear.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelCYear.setForeground(new Color(0x666666));

            //---- labelDepartment ----
            labelDepartment.setText("Department");
            labelDepartment.setBackground(Color.black);
            labelDepartment.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelDepartment.setForeground(new Color(0x666666));

            //---- labelMajor ----
            labelMajor.setText("Major");
            labelMajor.setBackground(Color.black);
            labelMajor.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelMajor.setForeground(new Color(0x666666));

            //---- labelEduType ----
            labelEduType.setText("Education Type");
            labelEduType.setBackground(Color.black);
            labelEduType.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelEduType.setForeground(new Color(0x666666));

            //---- labelClass ----
            labelClass.setText("Class");
            labelClass.setBackground(Color.black);
            labelClass.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelClass.setForeground(new Color(0x666666));

            //---- textID ----
            textID.setEditable(false);
            textID.setEnabled(false);

            //---- rdMale ----
            rdMale.setText("Male");
            rdMale.setFont(new Font("Segoe UI", Font.PLAIN, 13));

            //---- rdFemale ----
            rdFemale.setText("Female");
            rdFemale.setFont(new Font("Segoe UI", Font.PLAIN, 13));

            //---- btnReset ----
            btnReset.setText("RESET");
            btnReset.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btnReset.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnResetMouseClicked(e);
                }
            });
            btnReset.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnResetEnterKeyPressed(e);
                }
            });

            //---- btnEdit ----
            btnEdit.setText("EDIT");
            btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btnEdit.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnStudentEditKeyPressed(e);
                }
            });
            btnEdit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnEditStudentMouseClicked(e);
                }
            });

            GroupLayout panelProfileLayout = new GroupLayout(panelProfile);
            panelProfile.setLayout(panelProfileLayout);
            panelProfileLayout.setHorizontalGroup(
                panelProfileLayout.createParallelGroup()
                    .addGroup(panelProfileLayout.createSequentialGroup()
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addComponent(labelID)
                            .addComponent(labelNAME)
                            .addComponent(labelDoB)
                            .addComponent(labelGender)
                            .addComponent(labelCYear))
                        .addGroup(panelProfileLayout.createParallelGroup()
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addGroup(panelProfileLayout.createParallelGroup()
                                    .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panelProfileLayout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addComponent(textNAME, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelProfileLayout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(textCYear)))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panelProfileLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(panelProfileLayout.createParallelGroup()
                                            .addGroup(panelProfileLayout.createSequentialGroup()
                                                .addComponent(rdMale)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdFemale))
                                            .addComponent(dcDoB, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelProfileLayout.createParallelGroup()
                                    .addGroup(panelProfileLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnReset)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEdit))
                                    .addGroup(panelProfileLayout.createSequentialGroup()
                                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panelProfileLayout.createSequentialGroup()
                                                .addGroup(panelProfileLayout.createParallelGroup()
                                                    .addComponent(labelDepartment)
                                                    .addComponent(labelMajor))
                                                .addGap(37, 37, 37)
                                                .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbMajor, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                                    .addComponent(cbDepartment, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                                            .addGroup(panelProfileLayout.createSequentialGroup()
                                                .addGroup(panelProfileLayout.createParallelGroup()
                                                    .addComponent(labelEduType)
                                                    .addComponent(labelClass))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelProfileLayout.createParallelGroup()
                                                    .addComponent(textEduType, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                                    .addComponent(textClass, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(panelProfileLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(textID, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
            panelProfileLayout.setVerticalGroup(
                panelProfileLayout.createParallelGroup()
                    .addGroup(panelProfileLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelID)
                            .addComponent(textID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNAME)
                            .addComponent(labelDepartment)
                            .addComponent(textNAME, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDepartment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDoB)
                            .addComponent(labelMajor)
                            .addComponent(dcDoB, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMajor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelGender)
                            .addComponent(labelEduType)
                            .addComponent(rdMale)
                            .addComponent(rdFemale)
                            .addComponent(textEduType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCYear)
                            .addComponent(labelClass)
                            .addComponent(textCYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(panelProfileLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(41, Short.MAX_VALUE))
            );
        }

        //======== panelCertificate ========
        {
            panelCertificate.setBackground(Color.white);
            panelCertificate.setBorder(new TitledBorder("Certificate"));
            panelCertificate.setForeground(new Color(0x333333));
            panelCertificate.setFont(new Font("Segoe UI", Font.BOLD, 20));
            panelCertificate.setOpaque(false);

            //---- labelIDCer ----
            labelIDCer.setText("ID");
            labelIDCer.setBackground(Color.black);
            labelIDCer.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelIDCer.setForeground(new Color(0x666666));

            //---- labelTITLE ----
            labelTITLE.setText("TITLE");
            labelTITLE.setBackground(Color.black);
            labelTITLE.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelTITLE.setForeground(new Color(0x666666));

            //---- labelDESCRIPTION ----
            labelDESCRIPTION.setText("DESCRIPTION");
            labelDESCRIPTION.setBackground(Color.black);
            labelDESCRIPTION.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelDESCRIPTION.setForeground(new Color(0x666666));

            //---- labelVALID ----
            labelVALID.setText("IS VALID");
            labelVALID.setBackground(Color.black);
            labelVALID.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelVALID.setForeground(new Color(0x666666));

            //---- labelISSUE ----
            labelISSUE.setText("ISSUE DATE");
            labelISSUE.setBackground(Color.black);
            labelISSUE.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelISSUE.setForeground(new Color(0x666666));

            //---- labelEXPIRED ----
            labelEXPIRED.setText("EXPIRED DATE");
            labelEXPIRED.setBackground(Color.black);
            labelEXPIRED.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelEXPIRED.setForeground(new Color(0x666666));

            //---- labelORGANIZATION ----
            labelORGANIZATION.setText("ORGANIZATION");
            labelORGANIZATION.setBackground(Color.black);
            labelORGANIZATION.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelORGANIZATION.setForeground(new Color(0x666666));

            //---- btnResetCer ----
            btnResetCer.setText("RESET");
            btnResetCer.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btnResetCer.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnResetCerKeyPressed(e);
                }
            });
            btnResetCer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnResetCerMouseClicked(e);
                }
            });

            //---- btnSaveCer ----
            btnSaveCer.setText("SAVE");
            btnSaveCer.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btnSaveCer.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnSaveCerKeyPressed(e);
                }
            });
            btnSaveCer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnSaveCerMouseClicked(e);
                }
            });

            //======== scrollPane1 ========
            {

                //---- tblCer ----
                tblCer.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, "", "", null, null, ""},
                    },
                    new String[] {
                        "id", "Title", "Organization", "Valid", "Issua Date", "Expired Date"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                {
                    TableColumnModel cm = tblCer.getColumnModel();
                    cm.getColumn(0).setResizable(false);
                }
                tblCer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblCerMouseDoubleClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblCer);
            }

            //---- btnExport ----
            btnExport.setText("Export");
            btnExport.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btnExport.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnExportMouseClicked(e);
                }
            });
            btnExport.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnExportKeyPressed(e);
                }
            });

            //---- btnImport ----
            btnImport.setText("Import");
            btnImport.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btnImport.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnImportMouseClicked(e);
                }
            });
            btnImport.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnImportKeyPressed(e);
                }
            });

            //---- btnCreate ----
            btnCreate.setText("Create");
            btnCreate.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btnCreate.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnCreateMouseClicked(e);
                }
            });
            btnCreate.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnCreateKeyPressed(e);
                }
            });

            GroupLayout panelCertificateLayout = new GroupLayout(panelCertificate);
            panelCertificate.setLayout(panelCertificateLayout);
            panelCertificateLayout.setHorizontalGroup(
                panelCertificateLayout.createParallelGroup()
                    .addGroup(panelCertificateLayout.createSequentialGroup()
                        .addGroup(panelCertificateLayout.createParallelGroup()
                            .addGroup(panelCertificateLayout.createSequentialGroup()
                                .addGroup(panelCertificateLayout.createParallelGroup()
                                    .addComponent(labelIDCer)
                                    .addComponent(labelTITLE)
                                    .addComponent(labelDESCRIPTION))
                                .addGap(13, 13, 13)
                                .addGroup(panelCertificateLayout.createParallelGroup()
                                    .addComponent(textIDCer, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textTITLE, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelCertificateLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textDESCRIPTION, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelCertificateLayout.createParallelGroup()
                                    .addGroup(panelCertificateLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnResetCer)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSaveCer))
                                    .addGroup(panelCertificateLayout.createSequentialGroup()
                                        .addGroup(panelCertificateLayout.createParallelGroup()
                                            .addComponent(labelEXPIRED)
                                            .addComponent(labelORGANIZATION)
                                            .addComponent(labelVALID)
                                            .addComponent(labelISSUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelCertificateLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(checkValid)
                                            .addComponent(textORGANIZATION, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                            .addComponent(dcEXPIRED, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                            .addComponent(dcISSUE, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, panelCertificateLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExport)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImport)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreate)
                        .addGap(5, 5, 5))
            );
            panelCertificateLayout.setVerticalGroup(
                panelCertificateLayout.createParallelGroup()
                    .addGroup(panelCertificateLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelCertificateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelIDCer)
                            .addComponent(textIDCer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCertificateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTITLE)
                            .addComponent(labelVALID)
                            .addComponent(textTITLE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkValid))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCertificateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDESCRIPTION)
                            .addComponent(labelISSUE)
                            .addComponent(dcISSUE, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCertificateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEXPIRED)
                            .addComponent(dcEXPIRED, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textDESCRIPTION, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCertificateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(labelORGANIZATION)
                            .addComponent(textORGANIZATION, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(panelCertificateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveCer, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnResetCer, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelCertificateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
            );
        }

        //======== panelDeleteSTUDENT ========
        {
            panelDeleteSTUDENT.setBackground(Color.white);
            panelDeleteSTUDENT.setForeground(new Color(0x333333));
            panelDeleteSTUDENT.setFont(new Font("Segoe UI", Font.BOLD, 20));
            panelDeleteSTUDENT.setOpaque(false);

            //---- labelDeleteStudent ----
            labelDeleteStudent.setText("Delete Student");
            labelDeleteStudent.setFont(new Font("Segoe UI", labelDeleteStudent.getFont().getStyle() | Font.BOLD, labelDeleteStudent.getFont().getSize() + 1));
            labelDeleteStudent.setForeground(Color.red);

            //---- txtWarningStudent ----
            txtWarningStudent.setFont(new Font("Segoe UI", txtWarningStudent.getFont().getStyle() | Font.BOLD, 12));
            txtWarningStudent.setText("Once you delete the student, there is no going back. Please be certain.");
            txtWarningStudent.setBorder(null);
            txtWarningStudent.setOpaque(false);

            //---- btnDeleteStudent ----
            btnDeleteStudent.setText("DELETE STUDENT");
            btnDeleteStudent.setFont(new Font("Segoe UI Light", btnDeleteStudent.getFont().getStyle(), 18));
            btnDeleteStudent.setBackground(Color.red);
            btnDeleteStudent.setForeground(Color.white);
            btnDeleteStudent.setBorder(new LineBorder(Color.red, 1, true));
            btnDeleteStudent.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnDeleteStudentMouseClicked(e);
                }
            });
            btnDeleteStudent.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnDeleteStudentEnterKeyPressed(e);
                }
            });

            GroupLayout panelDeleteSTUDENTLayout = new GroupLayout(panelDeleteSTUDENT);
            panelDeleteSTUDENT.setLayout(panelDeleteSTUDENTLayout);
            panelDeleteSTUDENTLayout.setHorizontalGroup(
                panelDeleteSTUDENTLayout.createParallelGroup()
                    .addGroup(panelDeleteSTUDENTLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelDeleteSTUDENTLayout.createParallelGroup()
                            .addComponent(labelDeleteStudent, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDeleteSTUDENTLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnDeleteStudent, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(txtWarningStudent, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelDeleteSTUDENTLayout.setVerticalGroup(
                panelDeleteSTUDENTLayout.createParallelGroup()
                    .addGroup(panelDeleteSTUDENTLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(labelDeleteStudent, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWarningStudent, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteStudent, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
            );
        }

        //======== panelDeleteCERTIFICATE ========
        {
            panelDeleteCERTIFICATE.setBackground(Color.white);
            panelDeleteCERTIFICATE.setForeground(new Color(0x333333));
            panelDeleteCERTIFICATE.setFont(new Font("Segoe UI", Font.BOLD, 20));
            panelDeleteCERTIFICATE.setOpaque(false);

            //---- labelDeleteCertificate ----
            labelDeleteCertificate.setText("Delete Certificate");
            labelDeleteCertificate.setFont(new Font("Segoe UI", labelDeleteCertificate.getFont().getStyle() | Font.BOLD, labelDeleteCertificate.getFont().getSize() + 1));
            labelDeleteCertificate.setForeground(Color.red);

            //---- txtWarningCertificate ----
            txtWarningCertificate.setFont(new Font("Segoe UI", txtWarningCertificate.getFont().getStyle() | Font.BOLD, 12));
            txtWarningCertificate.setText("Once you delete the certificate, there is no going back. Please be certain.");
            txtWarningCertificate.setBorder(null);
            txtWarningCertificate.setOpaque(false);

            //---- btnDeleteCertificate ----
            btnDeleteCertificate.setText("DELETE CERTIFICATE");
            btnDeleteCertificate.setFont(new Font("Segoe UI Light", btnDeleteCertificate.getFont().getStyle(), 18));
            btnDeleteCertificate.setBackground(Color.red);
            btnDeleteCertificate.setForeground(Color.white);
            btnDeleteCertificate.setBorder(new LineBorder(Color.red, 1, true));
            btnDeleteCertificate.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    btnDeleteCertificateKeyPressed(e);
                }
            });
            btnDeleteCertificate.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnDeleteCertificateMouseClicked(e);
                }
            });

            GroupLayout panelDeleteCERTIFICATELayout = new GroupLayout(panelDeleteCERTIFICATE);
            panelDeleteCERTIFICATE.setLayout(panelDeleteCERTIFICATELayout);
            panelDeleteCERTIFICATELayout.setHorizontalGroup(
                panelDeleteCERTIFICATELayout.createParallelGroup()
                    .addGroup(panelDeleteCERTIFICATELayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelDeleteCERTIFICATELayout.createParallelGroup()
                            .addComponent(labelDeleteCertificate, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDeleteCERTIFICATELayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnDeleteCertificate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(txtWarningCertificate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelDeleteCERTIFICATELayout.setVerticalGroup(
                panelDeleteCERTIFICATELayout.createParallelGroup()
                    .addGroup(panelDeleteCERTIFICATELayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(labelDeleteCertificate, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWarningCertificate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteCertificate, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panelProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panelDeleteSTUDENT, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                            .addComponent(panelDeleteCERTIFICATE, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(panelCertificate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 37, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panelProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panelDeleteSTUDENT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelDeleteCERTIFICATE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 103, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelCertificate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE))
        );
        setSize(1256, 687);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Duy Nguyen
    private JPanel panelProfile;
    private JLabel labelID;
    private JLabel labelNAME;
    private JLabel labelDoB;
    private JLabel labelGender;
    private JLabel labelCYear;
    private JLabel labelDepartment;
    private JLabel labelMajor;
    private JLabel labelEduType;
    private JLabel labelClass;
    private JTextField textID;
    private JTextField textNAME;
    private JTextField textCYear;
    private JDateChooser dcDoB;
    private JRadioButton rdMale;
    private JRadioButton rdFemale;
    private JComboBox cbDepartment;
    private JComboBox cbMajor;
    private JButton btnReset;
    private JButton btnEdit;
    private JTextField textEduType;
    private JTextField textClass;
    private JPanel panelCertificate;
    private JLabel labelIDCer;
    private JLabel labelTITLE;
    private JLabel labelDESCRIPTION;
    private JLabel labelVALID;
    private JLabel labelISSUE;
    private JLabel labelEXPIRED;
    private JLabel labelORGANIZATION;
    private JTextField textIDCer;
    private JTextField textTITLE;
    private JButton btnResetCer;
    private JButton btnSaveCer;
    private JTextField textDESCRIPTION;
    private JTextField textORGANIZATION;
    private JCheckBox checkValid;
    private JDateChooser dcISSUE;
    private JDateChooser dcEXPIRED;
    private JScrollPane scrollPane1;
    private JTable tblCer;
    private JButton btnExport;
    private JButton btnImport;
    private JButton btnCreate;
    private JPanel panelDeleteSTUDENT;
    private JLabel labelDeleteStudent;
    private JTextPane txtWarningStudent;
    private JButton btnDeleteStudent;
    private JPanel panelDeleteCERTIFICATE;
    private JLabel labelDeleteCertificate;
    private JTextPane txtWarningCertificate;
    private JButton btnDeleteCertificate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
