package Reception_feedback;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    //客户端，多次发送数据
    public static void main(String[] args) throws IOException {
        //1.创建Socket对象
        Socket socket = new Socket("127.0.0.1",10000);//尝试实现时，先发给自己的10000端口


        //2. 写出数据
        String str = "客户端发送：见到你很高兴！";
        OutputStream  os = socket.getOutputStream();
        os.write(str.getBytes(StandardCharsets.UTF_8));

        //需要一个结束语句
        socket.shutdownOutput();//告诉接收端，我发完了，避免read()阻塞

        //3.接受服务端返回的数据
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());//避免中文乱码
        int b;
        while((b = isr.read()) != -1){//保证读入数据完整
            System.out.print((char)b);
        }

        //释放资源
        os.close();//可省略，因为只要socket关了，连接通道里的流也就关了
        socket.close();
    }

}

