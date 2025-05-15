package FinalWork.Manage_Thread;

import FinalWork.Server.MyRunnable;

import java.util.HashMap;
import java.util.Iterator;


/*
* 通过哈希键值对，将用户名与其线程中的Socket通道存放，
* 达到一对一交流/建群组的操作
* 是否在线也可以实现
* */


//现在是，每建立一个对话，才建立线程
public class ManageClient {


    public static HashMap hm = new HashMap<String, MyRunnable>();//!!!必须是静态，因为只需要一个表

    public static void addClientThread(String uid, MyRunnable ct){
        hm.put(uid,ct);

        System.out.println(uid+ "的" + ct.getName());
    }

    public static MyRunnable getClientThread(String uid){
        return (MyRunnable) hm.get(uid);
    }

    public static String getAllOnlineUser(){
        //遍历
        Iterator it = hm.keySet().iterator();
        String res = "";
        while (it.hasNext()){
            res += it.next().toString() + " ";//加空格是为了保证格式相同，方便服务器接收后进行拆分
        }
        return res;
    }












}
