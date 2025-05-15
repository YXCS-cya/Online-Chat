package FinalWork.Server;


import FinalWork.Manage_Thread.ManageClient;
import User_data.Message;
import User_data.MessageType;
import User_data.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConClient {

    public ServerConClient(/*String userId*/) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(10000);




        while (true){
            Socket socket = ss.accept();
//            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
//            int b;
//            String userId = null;
//            while((b = isr.read()) != -1){//保证读入数据完整
//               userId = userId + (char)b;
//            }


            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //Object object = ois.readObject();
            //User user = new User();
            try {
                //user = (User) object;
                MyRunnable myRunnable = new MyRunnable(socket,ois);

                //ManageClient.addClientThread(user.getUserid(),myRunnable);
                myRunnable.start();
            }catch (ClassCastException e){
                e.printStackTrace();
                //user = (User) object;
//                try{
//                    //System.out.println("传入Message信息");
//                    Message message = (Message)object;
//                    //System.out.println(message.getMesType());
////                    if(message.getMesType().equals(MessageType.message_get_olFriend)){
////                        //把发送者在服务器中留存的好友返回去
////                        System.out.println("发送服务器在线成员信息");
////                        String olFriend = ManageClient.getAllOnlineUser();
////                        if(olFriend.equals("")){
////
////                        }
////                        Message message1 = new Message();
////                        message1.setMesType(MessageType.message_ret_olFriend);
////                        message1.setMes(olFriend);
////                        message1.setGetter(message.getSender());
////                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
////                        oos.writeObject(message1);
////                    }else{
//                        MyRunnable myRunnable = new MyRunnable(socket);
//                        ManageClient.addClientThread(message.getSender(),myRunnable);
//                        myRunnable.start();
//
//
//
//
//            }finally {
//
//                }
      }


            //User user = (User)ois.readObject();
            //System.out.println(user.getUserid());









        }








    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new ServerConClient();
    }


}