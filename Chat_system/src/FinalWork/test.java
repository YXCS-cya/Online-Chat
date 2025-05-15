package FinalWork;



//import Two_person.Client_AcctionListen;
import User_data.Message;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class test extends JFrame{
    //客户端，多次发送数据



    public void  Chat(String userid,String friendid) throws IOException {


        Socket socket = new Socket("127.0.0.1",10000);



        //Scanner sc = new Scanner(System.in);

        Message message = new Message();
        message.setGetter(friendid);
        message.setSender(userid);
        //message.setMes(sc.nextLine());
        message.setMes("gongzhudianxia ");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //os.write(message.getBytes(StandardCharsets.UTF_8));
            oos.writeObject(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        //textField.addActionListener(new FinalWork.Client.Client_AcctionListen(textField, socket,oos,message));








    }

    public static void main(String[] args) throws IOException {
        test t = new test();
        t.Chat("YXCS","Miku");
    }






}

