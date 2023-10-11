package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EncryptionDecryption {
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt() {
        this.salt = "SeCuRiTy";
    }

    public static void enc(JFrame f, File[] files, String pass, int radio)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        EncryptionDecryption en = new EncryptionDecryption();
        en.setSalt();
        String password = pass;
        if (radio == 128)
            password += en.getSalt();
        else if (radio == 256) {
            password += en.getSalt();
            password += pass;
            password += en.getSalt();
        }
        for (int i = 0; i < files.length; i++) {
            try {
                encryptedFile(password, files[i].toString());
            } catch (Exception exe) {
                JOptionPane.showMessageDialog(f, exe);
                return;
            }
        }
        JOptionPane.showMessageDialog(f, "Encryption Successful...");
    }

    public static void dec(JFrame f, File[] files, String pass, int radio) {
        EncryptionDecryption en = new EncryptionDecryption();
        en.setSalt();
        String password = pass;
        if (radio == 128)
            password += en.getSalt();
        else if (radio == 256) {
            password += en.getSalt();
            password += pass;
            password += en.getSalt();
        }

        for (int i = 0; i < files.length; i++) {
            try {
                decryptedFile(password, files[i].toString());
            } catch (Exception exe) {
                JOptionPane.showMessageDialog(f, "Incorrect Password");
                return;
            }
        }
        JOptionPane.showMessageDialog(f, "Decryption Successful...");

    }

    public static void encryptedFile(String secretKey, String fileInputPath)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IOException,
            IllegalBlockSizeException, BadPaddingException {
        var key = new SecretKeySpec(secretKey.getBytes(), "AES");
        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        var fileInput = new File(fileInputPath);
        var inputStream = new FileInputStream(fileInput);
        var inputBytes = new byte[(int) fileInput.length()];
        inputStream.read(inputBytes);

        var outputBytes = cipher.doFinal(inputBytes);

        var fileEncryptOut = new File(fileInputPath);
        var outputStream = new FileOutputStream(fileEncryptOut);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        fileInput.setReadable(true);
        fileInput.setWritable(false);
        fileInput.setExecutable(false);

        System.out.println("File successfully encrypted!");

        System.out.println("New File: " + fileInputPath);
    }

    public static void decryptedFile(String secretKey, String fileInputPath)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IOException,
            IllegalBlockSizeException, BadPaddingException {
        var key = new SecretKeySpec(secretKey.getBytes(), "AES");
        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        var fileInput = new File(fileInputPath);
        fileInput.setWritable(true);
        fileInput.setExecutable(true);
        var inputStream = new FileInputStream(fileInput);
        var inputBytes = new byte[(int) fileInput.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        var fileEncryptOut = new File(fileInputPath);
        var outputStream = new FileOutputStream(fileEncryptOut);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File successfully decrypted!");
        System.out.println("New File: " + fileInputPath);
    }

}
