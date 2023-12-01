package com.sms.StudentManagementSystem.Controllers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.sms.StudentManagementSystem.Models.Department;
import com.sms.StudentManagementSystem.Models.Major;
import com.sms.StudentManagementSystem.Models.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class UtilsController<T> {
    @Autowired
    private DepartmentController departmentController;
    @Autowired
    private MajorController majorController;

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{8,}$");
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");

    private static final String RESOURCES = "Resources";
    private static final String WORKING_DIR = System.getProperty("user.dir");
    private static final String DEFAULT_AVATAR_PATH = Paths.get(WORKING_DIR, "src", "main", "resources", "Images", "defaultAvatar.png").toString();

    public static boolean loadAvatar(JLabel avatarBox, String email) {
        String userPath = Paths.get(RESOURCES, email).toString();
        String folderPath = Paths.get(WORKING_DIR, userPath).toString();

        File folder = new File(folderPath);
        String avatarPath = DEFAULT_AVATAR_PATH;

        if (folder.exists()) {
            File[] matchingFiles = folder.listFiles((dir, name) -> name.startsWith("avatar."));
            if (matchingFiles != null && matchingFiles.length > 0) {
                Arrays.sort(matchingFiles);
                avatarPath = matchingFiles[0].getAbsolutePath();
            }
        } else
            JOptionPane.showMessageDialog(null, "Error: Folder not found.", "Error", JOptionPane.ERROR_MESSAGE);

        return setAvatar(avatarPath, avatarBox);
    }

    public static boolean saveAvatar(String email, File avatar) {
        String userPath = Paths.get(RESOURCES, email).toString();
        String folderPath = Paths.get(WORKING_DIR, userPath).toString();

        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                System.out.println("Error: Cannot create folder.");
                return false;
            }
        }

        if (avatar == null)
            avatar = new File(DEFAULT_AVATAR_PATH);

        String fileUploadedExtension = avatar.getName().substring(avatar.getName().lastIndexOf(".") + 1);
        String avatarPath = Paths.get(folderPath, "avatar." + fileUploadedExtension).toString();

        File avatarFile = new File(avatarPath);
        if (avatarFile.exists()) {
            if (!avatarFile.delete()) {
                System.out.println("Error: Cannot delete old avatar.");
                return false;
            }
        }

        try {
            Files.copy(avatar.toPath(), avatarFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean setAvatar(String avatarPath, JLabel avatarBox) {
        try {
            Image avatar = ImageIO.read(new File(avatarPath));
            avatar.getScaledInstance(avatarBox.getWidth(), avatarBox.getHeight(), Image.SCALE_SMOOTH);

            avatarBox.setIcon(new ImageIcon(avatar));
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static boolean removeUserFolder(String email) {
        String userPath = Paths.get(RESOURCES, email).toString();
        String folderPath = Paths.get(WORKING_DIR, userPath).toString();

        File folder = new File(folderPath);
        if (folder.exists()) {
            File[] matchingFiles = folder.listFiles();
            if (matchingFiles != null) {
                for (File file : matchingFiles) {
                    if (!file.delete()) {
                        System.out.println("Error: Cannot delete file.");
                        return false;
                    }
                }
            }
            if (!folder.delete()) {
                System.out.println("Error: Cannot delete folder.");
                return false;
            }
        }
        return true;
    }

    public static int calculateAge(Date date) {
        Date currentDate = new Date();
        long diffInMillis = Math.abs(currentDate.getTime() - date.getTime());
        long diff = diffInMillis / (24 * 60 * 60 * 1000);
        return (int) (diff / 365.25);
    }

    public void exportToExcel(List<T> dataList, String excelFilePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Create header row
            Row headerRow = sheet.createRow(0);

            // Assuming the first element in the list is not null
            T firstElement = dataList.get(0);
            if (firstElement != null) {
                Class<?> clazz = firstElement.getClass();
                java.lang.reflect.Field[] fields = clazz.getDeclaredFields();

                int cellNum = 0;
                for (java.lang.reflect.Field field : fields) {
                    if (isRemovedField(field.getName()))
                        continue;
                    Cell cell = headerRow.createCell(cellNum++);
                    cell.setCellValue(field.getName());
                }
            }

            // Create data rows
            int rowNum = 1;
            for (T data : dataList) {
                Row row = sheet.createRow(rowNum++);

                if (data != null) {
                    Class<?> clazz = data.getClass();
                    java.lang.reflect.Field[] fields = clazz.getDeclaredFields();

                    int cellNum = 0;
                    for (java.lang.reflect.Field field : fields) {
                        if (isRemovedField(field.getName()))
                            continue;

                        field.setAccessible(true);

                        try {
                            Object value = field.get(data);
                            Cell cell = row.createCell(cellNum++);
                            setCellValue(cell, value);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                }
            }

            // Write the workbook to the Excel file
            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void exportToCSV(List<T> dataList, String csvFilePath) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath))) {
            // Write header
            T firstElement = dataList.get(0);
            if (firstElement != null) {
                Class<?> clazz = firstElement.getClass();
                java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
                String[] header = new String[fields.length];

                int cellNum = 0;
                for (java.lang.reflect.Field field : fields) {
                    if (isRemovedField(field.getName()))
                        continue;
                    header[cellNum++] = field.getName();
                }

                csvWriter.writeNext(header);
            }

            // Write data
            for (T data : dataList) {
                if (data != null) {
                    Class<?> clazz = data.getClass();
                    java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
                    String[] row = new String[fields.length];

                    int cellNum = 0;
                    for (java.lang.reflect.Field field : fields) {
                        if (isRemovedField(field.getName()))
                            continue;

                        field.setAccessible(true);

                        try {
                            Object value = field.get(data);
                            row[cellNum++] = String.valueOf(value);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }

                    csvWriter.writeNext(row);
                }
            }
        }
    }

    private void setCellValue(Cell cell, Object value) {
        switch (value) {
            case null -> cell.setCellValue("");
            case String s -> cell.setCellValue(s);
            case Integer i -> cell.setCellValue(i);
            case Double v -> cell.setCellValue(v);
            case Date date -> {
                CellStyle dateStyle = cell.getSheet().getWorkbook().createCellStyle();
                CreationHelper createHelper = cell.getSheet().getWorkbook().getCreationHelper();
                dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
                cell.setCellValue(date);
                cell.setCellStyle(dateStyle);
            }
            case Boolean b -> cell.setCellValue(b);
            case Student student -> cell.setCellValue(student.getName());
            case Department department -> cell.setCellValue(department.getName());
            case Major major -> cell.setCellValue(major.getName());
            default -> cell.setCellValue(value.toString());
        }
    }

    private boolean isRemovedField(String field) {
        return field.equals("password") ||
                field.equals("loginHistories") ||
                field.equals("certificates") ||
                field.equals("student");
    }

    public List<T> importFromExcel(String excelFilePath, Class<T> clazz) throws IOException {
        List<T> dataList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            if (iterator.hasNext()) {
                Row headerRow = iterator.next();

                while (iterator.hasNext()) {
                    Row currentRow = iterator.next();
                    T data = mapRowToData(currentRow, clazz, headerRow);
                    dataList.add(data);
                }
            }
        }

        return dataList;
    }

    public List<T> importFromCSV(String csvFilePath, Class<T> clazz) throws IOException {
        List<T> dataList = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            String[] header = csvReader.readNext(); // Read header

            if (header != null) {
                String[] row;
                while ((row = csvReader.readNext()) != null) {
                    T data = mapRowToData(row, clazz, header);
                    dataList.add(data);
                }
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }

    private T mapRowToData(Object row, Class<T> clazz, Object header) {
        T data;
        try {
            try {
                data = clazz.getDeclaredConstructor().newInstance();
                java.lang.reflect.Field[] fields = clazz.getDeclaredFields();

                if (row instanceof Row && header instanceof Row) {
                    int cellNum = 0;
                    for (java.lang.reflect.Field field : fields) {
                        field.setAccessible(true);
                        if (isRemovedField(field.getName())) continue;
                        Cell cell = ((Row) row).getCell(cellNum++);
                        setValueToField(field, data, cell);
                    }
                } else if (row instanceof String[] rowData && header instanceof String[] headerData) {
                    for (int i = 0; i < rowData.length; i++) {
                        java.lang.reflect.Field field = findFieldByName(clazz, headerData[i]);
                        if (field != null && !isRemovedField(field.getName())) {
                            setValueToField(field, data, rowData[i]);
                        }
                    }
                }
                return data;
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setValueToField(java.lang.reflect.Field field, T data, Object cellValue) throws IllegalAccessException {
        field.setAccessible(true);

        if (field.getType() == String.class) {
            field.set(data, String.valueOf(cellValue));
        } else if (field.getType() == int.class || field.getType() == Integer.class) {
            field.set(data, (int) Double.parseDouble(String.valueOf(cellValue)));
        } else if (field.getType() == double.class || field.getType() == Double.class) {
            field.set(data, Double.parseDouble(String.valueOf(cellValue)));
        } else if (field.getType() == Date.class) {
            Date date = parseDateFromCellValue(cellValue);
            field.set(data, date);
        } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
            field.set(data, Boolean.parseBoolean(String.valueOf(cellValue)));
        } else if (field.getType() == Department.class) {
            Department department = departmentController.getByName(String.valueOf(cellValue));
            field.set(data, department);
        } else if (field.getType() == Major.class) {
            Major major = majorController.getByName(String.valueOf(cellValue));
            field.set(data, major);
        } else {
            field.set(data, String.valueOf(cellValue));
        }
    }

    private Date parseDateFromCellValue(Object cellValue) {
        switch (cellValue) {
            case Date date -> {
                return date;
            }
            case XSSFCell xssfCell -> {
                return xssfCell.getDateCellValue();
            }
            case String s -> {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    return dateFormat.parse(s);
                } catch (ParseException e) {
                    throw new RuntimeException("Error parsing date: " + e.getMessage());
                }
            }
            case null, default -> throw new IllegalArgumentException("Unsupported date cell value type");
        }
    }

    private java.lang.reflect.Field findFieldByName(Class<T> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

}
