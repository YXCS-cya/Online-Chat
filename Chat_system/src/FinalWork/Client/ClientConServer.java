package FinalWork.Client;

import FinalWork.Manage_Thread.ManageChatTable;
import FinalWork.Manage_Thread.MangeFriendList;
import User_data.Message;
import User_data.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
/*
* 客户端和服务端保持通讯
* 为了避免客户端多账号争抢Socket，对每一个开线程
* */
public class ClientConServer extends Thread{

    private Socket socket;




    public ClientConServer(Socket socket){
        this.socket = socket;

    }


    public void run(){


        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();

                System.out.println("从服务器发来消息类型为："+message.getMesType());
                if(message.getMesType().equals(MessageType.message_mes)){
                    //显示
                    Client client = ManageChatTable.getChatTable(message.getGetter() + " " + message.getSender());

                    client.showMessage(message);
                }else if(message.getMesType().equals(MessageType.message_ret_olFriend)){
                    String olFriend = message.getMes();
                    String [] olFriendList = olFriend.split(" ");//拆分出在线好友的列表
                    String getter = message.getGetter();
                    System.out.println("Getter = "+ getter);

                    //修改自己的好友列表
                    System.out.println("即将修改好友列表");
                    FriendList friendList =  MangeFriendList.getFriendList(getter);

                    if(friendList != null){
                        System.out.println("再来一个"+message.getMes());
                        friendList.updateFriend(message);

                    }
                } else if (message.getMesType().equals(MessageType.message_recoder)) {
                    Client client = ManageChatTable.getChatTable(message.getSender() + " " + message.getGetter());
                    System.out.println("当前用户为"+client.userid + "另一人是："+ message.getGetter());
                    client.showMessageRecoder();
                }


//                String info =
//                        message.getSender() + "对" + message.getGetter() + "说："+ message.getMes() + "\r\n";
//
//                this.jta.append(info);

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }














}
