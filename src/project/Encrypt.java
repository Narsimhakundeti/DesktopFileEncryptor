package project;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

public class Encrypt {
    ImageIcon image;
    JLabel background, fileselect, password, cofirm, b128, b256;
    JButton next, back, select;
    File[] files;
    JPasswordField pass, config;
    JRadioButton bit128, bit256;
    int radio = 0;
    boolean valid = false;
    boolean isenc;

    Encrypt(boolean temp) {
        if (temp == true)
            isenc = true;
        else
            isenc = false;
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(800, 400);
        // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            image = new ImageIcon(getClass().getResource("image1.png"));
            background = new JLabel(image);
            background.setBounds(400, 0, 800, 400);
            frame.add(background);
        } catch (Exception e) {
            System.out.println("Image not found");
        }

        next = new JButton("Next");
        back = new JButton("Back");
        back.setBounds(40, 300, 65, 25);
        next.setBounds(300, 300, 65, 25);
        back.setBackground(new Color(111, 122, 237));
        next.setBackground(new Color(111, 122, 237));
        back.setForeground(Color.WHITE);
        next.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        next.setFocusPainted(false);
        select = new JButton("File Chooser");
        select.setBounds(140, 30, 110, 30);
        select.setForeground(Color.WHITE);
        select.setFocusPainted(false);
        select.setBackground(new Color(111, 122, 237));
        fileselect = new JLabel("Choose File : ");
        fileselect.setFont(new Font("SansSerif", Font.BOLD, 15));
        fileselect.setBounds(30, 30, 200, 40);

        password = new JLabel("Password :");
        password.setFont(new Font("SansSerif", Font.BOLD, 15));
        password.setBounds(30, 130, 110, 30);

        cofirm = new JLabel("Confirm Password :");
        cofirm.setFont(new Font("SansSerif", Font.BOLD, 15));
        cofirm.setBounds(30, 190, 150, 30);

        pass = new JPasswordField();
        pass.setBounds(30, 160, 200, 20);

        config = new JPasswordField();
        config.setBounds(30, 220, 200, 20);

        bit128 = new JRadioButton();
        bit128.setBounds(90, 80, 40, 30);

        bit256 = new JRadioButton();
        bit256.setBounds(200, 80, 40, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(bit128);
        group.add(bit256);

        b128 = new JLabel("128 bit");
        b128.setFont(new Font("SansSerif", Font.BOLD, 15));
        b128.setBounds(30, 80, 60, 30);

        b256 = new JLabel("256 bit");
        b256.setFont(new Font("SansSerif", Font.BOLD, 15));
        b256.setBounds(140, 80, 60, 30);

        select.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            chooser.showOpenDialog(frame);
            files = chooser.getSelectedFiles();
            for (int i = 0; i < files.length; i++)
                System.out.println(files[i]);
        });

        bit128.addActionListener(e -> {
            if (e.getSource() == bit128)
                radio = 128;
        });

        bit256.addActionListener(e -> {
            if (e.getSource() == bit256)
                radio = 256;
        });

        back.addActionListener(e -> {
            frame.dispose();
            new App();
        });

        next.addActionListener(e -> {

            System.out.println(radio);
            if (radio == 0) {
                JOptionPane.showMessageDialog(frame, "Select bit size");
                return;
            }
            System.out.println(pass.getPassword());
            if (pass.getPassword().length == 0) {
                JOptionPane.showMessageDialog(frame, "Enter Password");
                return;
            }
            if (pass.getPassword().length != 8) {
                JOptionPane.showMessageDialog(frame, "Password Length should be 8");
                return;
            }
            System.out.println(config.getPassword());
            String p = new String(pass.getPassword());
            String c = new String(config.getPassword());
            if (!p.equals(c)) {
                JOptionPane.showMessageDialog(frame, "Incorrect Password");
                return;
            } else {
                if (isenc) {
                    new EncryptionDecryption();
                    try {
                        EncryptionDecryption.enc(frame, files, p, radio);
                    } catch (Exception ex) {
                    }
                } else {
                    new EncryptionDecryption();
                    EncryptionDecryption.dec(frame, files, p, radio);
                }

            }
            frame.dispose();
            new App();
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });

        frame.add(b128);
        frame.add(b256);
        frame.add(bit128);
        frame.add(bit256);
        frame.add(pass);
        frame.add(config);
        frame.add(password);
        frame.add(cofirm);
        frame.add(fileselect);
        frame.add(select);
        frame.add(back);
        frame.add(next);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
