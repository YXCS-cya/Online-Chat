package Thread_pools;

import java.io.*;
import java.net.Socket;

public class Client {
    //客户端：将本地文件上传到服务器。接受服务器的反馈

    /*
    * 此情况是File_Send情况的线程池改写，基础代码是一样的
    * */
    public static void main(String[] args) throws IOException {
        //1.创建socket对象，连接服务器
        Socket socket = new Socket("127.0.0.1",10000);

        //2.读取本地文件中的数据，并写到服务器中
        /*
         * 由于文件较大，要使用缓冲流进行包裹
         * */
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("test\\clientdir\\a.jpg"));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

        byte[] bytes = new byte[1024];
        int len;
        while((len = bis.read(bytes)) != -1){
            //保证在本地文件中读到的，全部写进服务器中
            bos.write(bytes,0,len);
        }

        //往服务器写出结束标记，告知他已经全部上传
        socket.shutdownOutput();

        //3.接收服务器的回写数据
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //这一长串：从通道中获得输入字节流(socket)，再转成字符流(InputStreamReader)，再用缓冲流进行包裹

        String line = br.readLine();//因为反馈只需要一行信息，所以可以用该方法只读一行，不需要循环
        System.out.println(line);//line即服务器回写数据

        //4. 释放资源
        socket.close();



    }
}
