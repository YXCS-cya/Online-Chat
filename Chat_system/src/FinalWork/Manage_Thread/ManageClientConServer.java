package FinalWork.Manage_Thread;

import FinalWork.Client.ClientConServer;

import java.util.HashMap;

/*
* 哈希容器管理客户端去连接服务器的线程
*
* */
public class ManageClientConServer {

    private static HashMap hm = new HashMap<String, ClientConServer>();

    //把创建好的线程放进来
    public static void addClientConServer(String id, ClientConServer ccs){
        hm.put(id,ccs);
    }

    public static ClientConServer getClientConServer(String id){
        return (ClientConServer) hm.get(id);
    }
}
