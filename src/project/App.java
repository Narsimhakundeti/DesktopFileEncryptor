package project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class App {
    JButton click;
    ImageIcon image;
    JLabel back;
    JLabel text, matter, matter1, matter2;
    JButton encrypt, decrypt;

    public App() {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            image = new ImageIcon(getClass().getResource("image1.png"));
            back = new JLabel(image);
            back.setBounds(400, 0, 800, 400);
            frame.add(back);
        } catch (Exception e) {
            System.out.println("Image not found");
        }
        text = new JLabel("Welcome");
        text.setBounds(160, 30, 100, 40);
        text.setFont(new Font("SansSerif", Font.BOLD, 23));
        text.setForeground(Color.BLACK);
        frame.add(text);
        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");
        encrypt.setBounds(60, 270, 90, 30);
        decrypt.setBounds(250, 270, 90, 30);
        encrypt.setBackground(new Color(111, 122, 237));
        decrypt.setBackground(new Color(111, 122, 237));
        encrypt.setForeground(Color.WHITE);
        decrypt.setForeground(Color.WHITE);
        encrypt.setFocusPainted(false);
        decrypt.setFocusPainted(false);
        frame.add(encrypt);
        frame.add(decrypt);
        matter = new JLabel("This Application provides you the ability");
        matter.setFont(new Font("SansSerif", Font.BOLD, 15));
        matter.setBounds(60, 100, 300, 40);
        matter1 = new JLabel("to Secure Files Through");
        matter1.setFont(new Font("SansSerif", Font.BOLD, 15));
        matter1.setBounds(120, 120, 300, 40);
        matter2 = new JLabel("Advanced Encryption Standard");
        matter2.setFont(new Font("SansSerif", Font.BOLD, 15));
        matter2.setBounds(100, 140, 300, 40);
        frame.add(matter);
        frame.add(matter1);
        frame.add(matter2);

        encrypt.addActionListener(e -> {
            System.out.println("Encrypt is pressed");
            new Encrypt(true);
            frame.dispose();
        });

        decrypt.addActionListener(e -> {
            System.out.println("Decrypt is pressed");
            new Encrypt(false);
            frame.dispose();
        });

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}
