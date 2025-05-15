package Login_Rigister_try;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginUI extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI() {
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,200);
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("用户名: ");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("密码: ");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("登录");
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

    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean result = login(username, password);

        if (result) {
            JOptionPane.showMessageDialog(this, "登录成功");
            //FriendList friendList = new FriendList();
        } else {
            JOptionPane.showMessageDialog(this, "登录失败");
        }
    }

    public static boolean login(String username, String password) {
        String filePath = "Chat_system\\Data\\UsersData.txt"; // 指定文件路径
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}