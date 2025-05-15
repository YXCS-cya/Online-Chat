package basic_system;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //服务端： 多次接收数据并打印
    public static void main(String[] args) throws IOException {
        //1.创建对象，绑定10000端口（详情间笔记）
        ServerSocket ss = new ServerSocket(10000);

        //2.等待客户端连接
        Socket socket = ss.accept();

        //3.读取数据（通过数据流）

        InputStreamReader isr = new InputStreamReader(socket.getInputStream());//避免中文乱码
        int b;
        while((b = isr.read()) != -1){//保证读入数据完整
            System.out.print((char)b);
        }

        //4. 释放资源
        socket.close();
        ss.close();//关服务器
    }
}
