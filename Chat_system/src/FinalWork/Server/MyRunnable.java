package FinalWork.Server;

import FinalWork.Manage_Thread.ManageClient;
import User_data.Message;
import User_data.MessageType;
import User_data.User;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;


public class MyRunnable extends Thread implements Runnable{

    /*
     * 为了传递socket对象给Runnable
     * ！！！创建构造方法
     * */
    Socket socket;
    private String sendder;
    Message message ;
    ObjectInputStream ois;

        public MyRunnable(Socket socket, ObjectInputStream ois){
        this.socket = socket;
        this.ois = ois;
    }

    public String getMesSender(){
            return sendder;
    }

    @Override
    public void run() {



       while (true){
           try {
//               ObjectInputStream ois =
//                       new ObjectInputStream(socket.getInputStream());

               try {
                   message = (Message) ois.readObject();


                   this.sendder = message.getSender();
                   //System.out.println(message.getMesType());
               } catch (IOException e) {
                   throw new RuntimeException(e);
               } catch (ClassNotFoundException e) {
//                   throw new RuntimeException(e);
               }catch (ClassCastException e){
                   //User user = (User) ois.readObject();
                   e.printStackTrace();
               }

               System.out.println("传入信息类型为："+message.getMesType());
               //System.out.println(message.getSender() + "给" + message.getGetter() + "发送："+message.getMes());

               //对得到的信息进行判断
               if(message.getMesType().equals(MessageType.message_mes)){
                   //实现转发
                   MyRunnable getter = ManageClient.getClientThread(message.getGetter());
                   ObjectOutputStream oos =
                           new ObjectOutputStream(getter.socket.getOutputStream());
                   message.setMesType(MessageType.message_mes);
                   oos.writeObject(message);
                   String message_records = message.getSender() + "对" + message.getGetter() + "说：" + message.getMes() + "\r\n";
                   System.out.println(message_records);

                   //聊天记录放入文件
                   BufferedWriter writer1=
                           new BufferedWriter(new FileWriter("Chat_system\\Data\\Chatdata\\"+message.getGetter()+"_with_"+message.getSender(),true));
                   BufferedWriter writer2=
                           new BufferedWriter(new FileWriter("Chat_system\\Data\\Chatdata\\"+message.getSender()+"_with_"+message.getGetter(),true));
                    writer1.write(message_records);
                    writer1.close();
                    writer2.write(message_records);
                    writer2.close();

               }else if(message.getMesType().equals(MessageType.message_get_olFriend)){
                   //把发送者在服务器中留存的好友返回去
                   //如果，表内没有成员怎么办？

                    String olFriend = ManageClient.getAllOnlineUser();
                    Message message1 = new Message();
                    message1.setMesType(MessageType.message_ret_olFriend);
                    message1.setMes(olFriend);
                    message1.setGetter(message.getSender());

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);
                   System.out.println("发送服务器在线成员信息为："+ olFriend);
               }else if(message.getMesType().equals(MessageType.message_user)) {

                   System.out.println("得到登录信息");

                   ManageClient.addClientThread(message.getSender(),this);//把用户名和对应线程存入控制类

                   //通知其他在线用户
                   NoticeOther(message.getSender());

               }else if(message.getMesType().equals(MessageType.message_recoder)){
                   ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                   //读取聊天记录
                   StringBuilder stringBuilder = new StringBuilder();
                   BufferedReader reader =
                           new BufferedReader(new FileReader("Chat_system\\Data\\Chatdata\\"+message.getSender()+"_with_"+message.getGetter()));
                   String line ;
                   while((line = reader.readLine()) != null){
                       stringBuilder.append(line);
                       stringBuilder.append("\n");//文件里自带了换行吧？//但是读取出来后不带换行了
                   }
                   reader.close();
                   String messageRecoder = stringBuilder.toString();

                   Message message1 = new Message();
                   message1.setMesType(MessageType.message_recoder);
                   message1.setSender(sendder);
                   message1.setGetter(message.getGetter());
                   message1.setMes(messageRecoder);

                   oos.writeObject(message1);
               }








           } catch (Exception e){//IOException | ClassNotFoundException e) {
               e.printStackTrace();
           }


       }



    }

    public void NoticeOther(String newOnline) throws IOException {

        HashMap hm = ManageClient.hm;
        Iterator it = hm.keySet().iterator();//迭代器

        while(it.hasNext()){
            Message message1 = new Message();
            message1.setMes(newOnline);
            message1.setMesType(MessageType.message_ret_olFriend);

            String onLineUser = it.next().toString();
            message1.setGetter(onLineUser);
            ObjectOutputStream oos = new ObjectOutputStream(ManageClient.getClientThread(onLineUser).socket.getOutputStream());
            oos.writeObject(message1);

        }







        }










}

