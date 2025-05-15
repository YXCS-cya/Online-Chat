package FinalWork.Client;

import FinalWork.Manage_Thread.ManageChatTable;
import FinalWork.Manage_Thread.ManageClientConServer;
import FinalWork.Manage_Thread.MangeFriendList;
import User_data.Message;
import User_data.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;

/*
* 我的好友列表
* */
public class FriendList extends JFrame implements MouseListener {
    JPanel jphy1, jphy2;
    JButton jphy_lb;
    JScrollPane jsp;
    private  JLabel[] jbls ;
    private String userid;
    private String pass;
    private  Socket socket;
    private static ObjectOutputStream oos;

//    public static void main(String[] args) {
//        FriendList friendList = new FriendList();
//    }

    public FriendList(String userid,String pass) throws IOException {
        this.userid = userid;
        this.pass = pass;

        this.socket = new Socket("127.0.0.1",10000);

        MangeFriendList.addFriendList(userid,this);


        //创建客户端与服务器连接的线程
        ClientConServer ccs = new ClientConServer(socket);
        ccs.start();
        ManageClientConServer.addClientConServer(userid,ccs);




        //显示好友列表
        jphy1 = new JPanel(new BorderLayout());
        //先假定有20个好友
        jphy2 = new JPanel(new GridLayout(20,1,4,4));

        jphy_lb = new JButton(userid+"的好友");





        BufferedReader br = new BufferedReader(new FileReader("Chat_system\\Data\\UsersData.txt"));
        String line;
        int count = 0;
        String[] name = new String[20];
        while((line = br.readLine()) != null){
            String[] parts = line.split("[,]");
            name[count++] = parts[0].trim();
        }

        jbls = new JLabel[count];


        for (int i = 0;i < count;i++){

            jbls[i] = new JLabel(name[i],JLabel.LEFT);//后需更改成好友名称
            //jbls[0].setEnabled(false);
            setOnline(jbls[i]);
            jbls[i].addMouseListener(this);
            jphy2.add(jbls[i]);
        }


        //设置默认好友
//            jbls[0] = new JLabel("YXCS",JLabel.LEFT);//后需更改成好友名称
//            //jbls[0].setEnabled(false);
//            setOnline(jbls[0]);
//            jbls[0].addMouseListener(this);
//            jphy2.add(jbls[0]);
//
//            jbls[1] = new JLabel("Miku",JLabel.LEFT);//后需更改成好友名称
//            setOnline(jbls[1]);
//            jbls[1].addMouseListener(this);
//            jphy2.add(jbls[1]);
//
//            jbls[2] = new JLabel("云下成伞",JLabel.LEFT);//后需更改成好友名称
//            setOnline(jbls[2]);
//            jbls[2].addMouseListener(this);
//            jphy2.add(jbls[2]);
//
//            jbls[3] = new JLabel("唤云成诗",JLabel.LEFT);//后需更改成好友名称
//            setOnline(jbls[3]);
//            jbls[3].addMouseListener(this);
//            jphy2.add(jbls[3]);

//        for (int i = 0; i < jbls.length; i++) {
//            System.out.println("成员为："+ jbls[i].getText());
//        }
//        for (int i = 0; i < jbls.length; i++) {
//            jbls[i].setEnabled(false);
//            if(jbls[i].getText().equals(userid)){
//                jbls[i].setEnabled(true);
//            }
//        }


        oos = new ObjectOutputStream(socket.getOutputStream());

        //发送登录信息
        Message LoginMes = new Message();
        LoginMes.setMesType(MessageType.message_user);
        LoginMes.setSender(userid);
        System.out.println("传递登录信息");
        oos.writeObject(LoginMes);

        //发送请求在线好友的包
        //        System.out.println("发送请求在线好友的包");
        Message message = new Message();
        message.setMesType(MessageType.message_get_olFriend);
        message.setSender(userid);//让服务器知道，应该返回 当前用户 的好友在线情况
        oos.writeObject(message);

        //得到消息放在ClientConServer



//        for (int i = 0; i < jbls.length; i++){
//            jbls[i] = new JLabel(i+1 + "",JLabel.LEFT);//后需更改成好友名称
//            jbls[i].addMouseListener(this);
//            jphy2.add(jbls[i]);
//
//        }

        jsp = new JScrollPane(jphy2);

        //对底层窗口jph1的初始化
        jphy1.add(jphy_lb,"North");
        jphy1.add(jsp,"Center");

        //放到窗口上
        this.add(jphy1,"Center");
        this.setSize(180,400);
        this.setVisible(true);




    }
    public void setOnline(JLabel jbls){
        jbls.setEnabled(false);
            if(jbls.getText().equals(userid)){
                jbls.setEnabled(true);
            }
    }

    public void updateFriend(Message message){
        System.out.println("当前用户为："+userid);
        System.out.println("要更改的用户为"+message.getMes());
        String olFriend [] = message.getMes().split(" ");
        for (int i = 0; i < olFriend.length; i++) {
            for (int j = 0; j < jbls.length; j++) {
                if(jbls[j].getText().equals(olFriend[i])){
                    jbls[j].setEnabled(true);
                }
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //响应用户双击，得到好友编号
        if(e.getClickCount()==2){
            String friendNo = ((JLabel)e.getSource()).getText();
            if(!friendNo.equals(userid)){
                Client client = new Client();
                System.out.println("创建聊天框");
                //创建申请聊天记录的消息
//                Message message = new Message();
//                message.setMesType(MessageType.message_recoder);
//                message.setGetter(friendNo);
//                message.setSender(userid);
//                try {
//                    oos.writeObject(message);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }

                //管理聊天界面
                ManageChatTable.addChat(userid + " "+ friendNo, client);
                try {
                    client.Chat(socket,userid,friendNo,pass,oos);

//                Thread thread = new Thread(client);
//                thread.start();
                } catch (IOException ex) {

                    ex.printStackTrace();
                }
            }else{
                System.out.println("无法与自己聊天！");
            }

            //System.out.println("你希望和"+friendNo+"聊天");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel jl = (JLabel) e.getSource();
        jl.setForeground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel jl = (JLabel) e.getSource();
        jl.setForeground(Color.black);
    }
}
