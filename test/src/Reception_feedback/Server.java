package Reception_feedback;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
            /*
            * 注意！！
            * read方法虽然会从通道中接受数据
            * 但是，如果没有结束标记，循环就会一直卡在read这里，等待下一个输入流
            * 所以，输出流一定要有结束标记
            * */
            System.out.print((char)b);
        }

        //4.回写数据
        String str = "服务端回写：多高兴呢？";
        OutputStream os = socket.getOutputStream();
        os.write(str.getBytes(StandardCharsets.UTF_8));

        //释放资源
        socket.close();
        ss.close();//关服务器
    }
}
