package Multi_threaded_sever;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class MyRunnable implements Runnable{

    /*
    * 为了传递socket对象给Runnable
    * ！！！创建构造方法
    * */
    Socket socket;
    public MyRunnable(Socket socket){
            this.socket = socket;
    }
    @Override
    public void run() {
        try {
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


        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(socket != null){
                try {
                    //5.释放资源
                    socket.close();
                    //ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
