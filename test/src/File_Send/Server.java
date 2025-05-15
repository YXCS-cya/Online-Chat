package File_Send;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/*
 * 如果不采用多线程，会出现一个用户上传未结束，另一个用户卡在循环体外的问题
 * 解决方案：多线程Multi_thread/线程池
 * */
public class Server {
    //服务端：接收客户端上传的文件，上传完毕后给出反馈
    public static void main(String[] args) throws IOException {

        //1. 创建并绑定端口
        ServerSocket ss = new ServerSocket(10000);

        //2.客户端连接
        Socket socket = ss.accept();

        //3. 读取数据并保存到指定文件夹
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

        //避免文件名重复
        String name = UUID.randomUUID().toString().replace("-","");

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("test\\serverdir\\"+ name +".jpg"));

        int len;
        byte[] bytes = new byte[1024];
        while((len = bis.read(bytes)) != -1){//循环结束，代表文件上传完毕
            bos.write(bytes,0,len);
        }
        //问题：是否要关闭本地IO流？

        //4.回写数据
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        /*分析
        * socket.getOutputStream：连接通道里获得的输出流（字节流）
        * OutputStreamWriter：转字符流
        * BufferedWriter：缓冲流包裹
        * */
        bw.write("上传成功！");
        bw.newLine();//换行
        bw.flush();

        //5.释放资源
        socket.close();
        ss.close();

    }
}
