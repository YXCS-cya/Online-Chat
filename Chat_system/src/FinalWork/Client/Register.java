package FinalWork.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
//    public static void main(String[] args) {
////        String username = "new_username";
////        String password = "new_password";
////        boolean result = register(username, password);
////        if (result) {
////            System.out.println("注册成功");
////        } else {
////            System.out.println("注册失败");
////        }
//        Register register = new Register();
//        register.setVisible(true);
//    }
    public Register() {
        setTitle("注册界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,210);
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("用户名: ");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("密码: ");

        passwordField = new JPasswordField();

        JButton loginButton = new JButton("注册");
        loginButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);

        //pack();
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean result = register(username, password);

        if (result) {
            JOptionPane.showMessageDialog(this, "注册成功!\n为您跳转登陆界面");
            //LoginUI loginUI = new LoginUI();
            this.dispose();
            //loginUI.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "注册失败");
        }
    }

    public static boolean register(String username, String password) {
        String filePath = "Chat_system\\Data\\UsersData.txt"; // 指定文件路径
        if(searchInFile(filePath,username))return false;
        else {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(username + "," + password + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;}
    }
    private static boolean searchInFile(String filePath, String searchStr) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchStr)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}