package com.sms.StudentManagementSystem.Controllers;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

@Controller
public class UtilsController {
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
}
