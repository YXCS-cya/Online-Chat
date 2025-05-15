package FinalWork.Client;

import FinalWork.Manage_Thread.MangeFriendList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoginUI extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    JButton loginButton;
    JButton loginTxt;
    JButton usingTxt;
    JButton Register;
    public LoginUI() {
        setTitle("Java即时聊天系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,200);
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 3));

        JLabel usernameLabel = new JLabel("用户名: ",JLabel.CENTER);
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("密码: ",JLabel.CENTER);
        passwordField = new JPasswordField();

        JLabel Projectname = new JLabel("软三 陈耀安 ",JLabel.CENTER);
        passwordField = new JPasswordField();
//        JLabel name = new JLabel(" ",JLabel.CENTER);
//        passwordField = new JPasswordField();


        loginButton = new JButton("登录");
        loginButton.addActionListener(this);

        loginTxt = new JButton("用户名册");
        loginTxt.addActionListener(this);
        usingTxt = new JButton("软件说明");
        usingTxt.addActionListener(this);
        //loginButton.addActionListener(this);
        Register = new JButton("注册");
        Register.addActionListener(this);


        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(Projectname);

        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        //panel.add(name);
//        panel.add(new JLabel());
        panel.add(loginTxt);
        panel.add(usingTxt);
        panel.add(Register);

        add(panel);

        //pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {

        LoginUI loginUI = new LoginUI();
        loginUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        JButton sourceButton = (JButton) e.getSource();

        if(sourceButton == loginButton){
            boolean result = login(username, password);

            if (result) {
                JOptionPane.showMessageDialog(this, "登录成功！");
                this.dispose();


                try {
                    FriendList friendList = new FriendList(username, password);
                    System.out.println("当前用户为："+username);
                    //MangeFriendList.addFriendList(username,friendList);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
//            User user = new User(username,password);
//            ObjectOutputStream oos = new ObjectOutputStream(user);


            } else {
                JOptionPane.showMessageDialog(this, "登录失败，请重试！");
            }
        } else if (sourceButton == loginTxt) {
            File file = new File("Chat_system\\Data\\UsersData.txt");
            if(file.exists()){
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else{
                System.out.println("文件不存在！");
            }
        } else if (sourceButton == usingTxt) {
            File file = new File("Chat_system\\Data\\usingTXT.txt");
            if(file.exists()){
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else{
                System.out.println("文件不存在！");
            }
        } else if (sourceButton == Register) {
            Register register = new Register();
            register.setVisible(true);
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