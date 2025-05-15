package basic_system;

import java.io.IOException;
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
        Scanner sc = new Scanner(System.in);
        OutputStream os = socket.getOutputStream();//转换成比特数据流输出

        while (true) {//方便多次发送信息
            System.out.println("请输入您想发送的文字：");
            String str = sc.nextLine();//从键盘录入数据

            if("886".equals(str)){//先通过886结束循环，后面更改结束条件以结束数据信息输出
                break;
            }

            os.write(str.getBytes(StandardCharsets.UTF_8));
        }
        //

        //3.释放资源
        os.close();//可省略，因为只要socket关了，连接通道里的流也就关了
        socket.close();
    }

}
