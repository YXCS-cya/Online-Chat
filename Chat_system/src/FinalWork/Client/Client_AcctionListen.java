package FinalWork.Client;

import User_data.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client_AcctionListen implements ActionListener {

    private JTextField textField;
    private Socket socket;
    private ObjectOutputStream oos ;
    private static JFrame frame;



    Message message;
    public Client_AcctionListen(JTextField textField, Socket socket, ObjectOutputStream oos, Message message) {
        this.textField = textField;
        this.socket = socket;
        this.oos = oos;
        this.message =  message;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String str = textField.getText();
       message.setMes(str);




        try {
            //os.write(message.getBytes(StandardCharsets.UTF_8));
            oos.writeObject(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //System.out.println("zhixingdaozhele"+str);
        textField.setText("");

//        try {
//            socket.close();
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
    }



}

