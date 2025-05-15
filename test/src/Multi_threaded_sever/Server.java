package Multi_threaded_sever;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/*
 * 此情况是File_Send情况的多线程改写，基础代码是一样的
 * 本质上是要求服务器一直不关闭，
 * 持续接受客户端传来的文件
 * 而且要能同时接受多个用户传来的消息
 * */

/*
* 如果不采用多线程，会出现一个用户上传未结束，另一个用户卡在循环体外的问题
* */
public class Server {
    //服务端：接收客户端上传的文件，上传完毕后给出反馈
    public static void main(String[] args) throws IOException {

        //1. 创建并绑定端口
        ServerSocket ss = new ServerSocket(10000);

        while (true) {
            //2.客户端连接
            Socket socket = ss.accept();

            //开启一条线程
            //每个用户对应一个线程
            new Thread(new MyRunnable(socket)).start();
        }

    }
}