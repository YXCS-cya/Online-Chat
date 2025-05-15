package FinalWork;

import User_data.Message;

import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class test_Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(10000);
        Socket socket = ss.accept();


        Message message = new Message();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        message = (Message) ois.readObject();

        System.out.println(message.getSender() + "给" + message.getGetter() + "发送："+message.getMes());

        socket.close();

    }
}
