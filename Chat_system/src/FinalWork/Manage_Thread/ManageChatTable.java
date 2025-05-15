package FinalWork.Manage_Thread;

import FinalWork.Client.Client;

import java.util.HashMap;

/*
* 管理客户端聊天界面
* */
public class ManageChatTable {
    private static HashMap hm = new HashMap<String, Client>();
    public static void addChat(String UserAndFriend, Client chat){
        hm.put(UserAndFriend,chat);
    }
    public static Client getChatTable(String UserAndFriend){
        return (Client) hm.get(UserAndFriend);
    }









}
