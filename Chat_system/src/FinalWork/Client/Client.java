package FinalWork.Client;



import User_data.Message;
import User_data.MessageType;
import User_data.User;
import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client extends JFrame implements ActionListener{
    //客户端，多次发送数据
    //为了实现接收信息，因此要做成线程

    public  Socket socket;
    private  JFrame frame;
    private  String input;
    private ObjectOutputStream oos;
    String userid;
    String pass;
    String friendid;
    JTextField jtf;
    JTextArea jta;
    JButton jb;
    JPanel jp;
    JScrollPane scrollPane;

    public void  Chat(Socket socket,String userid,String friendid,String pass,ObjectOutputStream oos) throws IOException{
        this.socket = socket;
        this.userid = userid;
        this.friendid = friendid;
        this.pass = pass;
        this.oos = oos;

        //this.socket = new Socket("127.0.0.1",10000);

        //User user = new User(userid,pass);
        //ObjectOutputStream oos =
        //        new ObjectOutputStream(socket.getOutputStream());
        //oos.writeObject(user);

//        Message message = new Message();
//        message.setMesType(MessageType.message_user);
//
//        message.setSender(userid);
//        ObjectOutputStream oos =
//                new ObjectOutputStream(socket.getOutputStream());


        initFrame();
        JTextField textField = new JTextField();
        textField.setBounds(0,300,520,300);

        showMessageRecoder();

        //frame.add(textField);
        //frame.setVisible(true);

        //textField.addActionListener(new FinalWork.Client.Client_AcctionListen(textField, socket,oos,message));



    }

//    public static void main(String[] args) throws IOException {
//        Client client = new Client();
//        client.Chat("YXCS","Miku");
//    }

public void showMessage (Message message){
    String info =
            message.getSender() + "对" + message.getGetter() + "说：" + message.getMes() + "\r\n";

    this.jta.append(info);

}
public void showMessageRecoder() throws IOException {

    StringBuilder stringBuilder = new StringBuilder();

    //录入聊天时间
    BufferedWriter writer1=
            new BufferedWriter(new FileWriter("Chat_system\\Data\\Chatdata\\"+userid+"_with_"+friendid,true));

    writer1.write(new java.util.Date().toString()+"\n");
    writer1.close();






    //取出聊天记录
    BufferedReader reader =
            new BufferedReader(new FileReader("Chat_system\\Data\\Chatdata\\"+userid+"_with_"+friendid));
    String line ;
    while((line = reader.readLine()) != null){
        stringBuilder.append(line);
        stringBuilder.append("\n");//文件里自带了换行吧？//但是读取出来后不带换行了
    }
    reader.close();
    String messageRecoder = stringBuilder.toString();

    //System.out.println(message.getMes());
    this.jta.append(messageRecoder);
}



    private  void initFrame(){

        jta = new JTextArea();
        jtf = new JTextField(15);
        jb = new JButton("发送");
        jb.addActionListener(this);
        jp = new JPanel();
        jp.add(jtf);
        jp.add(jb);
        scrollPane = new JScrollPane(jta);

        this.add(scrollPane);
        //this.add(jta,"Center");
        this.add(jp,"South");
        this.setTitle(userid + "与" + friendid+ "的聊天");
        this.setSize(500,350);
        this.setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jb){
                Message message = new Message();
                message.setGetter(friendid);
                message.setSender(userid);
                String str = jtf.getText();
                message.setMes(str);
                message.setMesType(MessageType.message_mes);
                //message.setGetter();

                String info =
                        userid + "对" + friendid + "说："+ str + "\r\n";

                this.jta.append(info);

                message.setSendTime(new java.util.Date().toString());
                try {
                    //想办法传一个oos进来
//                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message);
                    jtf.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
    }

    //@Override
//    public void run() {
//        while (true){
//            //读取
//            try {
//                ObjectInputStream ois =
//                        new ObjectInputStream(this.socket.getInputStream());
//                Message message = (Message) ois.readObject();
//
//                //显示在文本域
//                String info =
//                        message.getSender() + "对" + message.getGetter() + "说："+ message.getMes() + "\r\n";
//
//                this.jta.append(info);
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }
}
