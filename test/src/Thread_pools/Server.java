package Thread_pools;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 此情况是File_Send情况的线程池改写，基础代码是一样的
 *
 * 目的是避免多次创建销毁线程导致的系统资源浪费
 * */

/*
* 如果不采用多线程/线程池，会出现一个用户上传未结束，另一个用户卡在循环体外的问题
* */
public class Server {
    //服务端：接收客户端上传的文件，上传完毕后给出反馈
    public static void main(String[] args) throws IOException {

        //创建线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(//最多七个参数
                3,//核心线程数量
                16,//最大线程数量,根据公式计算->线程池总大小
                60,//存活时间->空闲时间
                TimeUnit.SECONDS,//秒->空闲时间的单位
                new ArrayBlockingQueue<>(2),//阻塞队列,假设是2
                Executors.defaultThreadFactory(),//线程工厂-想让线程池如何创建线程对象
                new ThreadPoolExecutor.AbortPolicy()//任务拒绝策略,这里采用默认方式AbortPolicy

        );




        //1. 创建并绑定端口
        ServerSocket ss = new ServerSocket(10000);

        while (true) {
            //2.客户端连接
            Socket socket = ss.accept();

            //开启一条线程
            //每个用户对应一个线程
            //new Thread(new MyRunnable(socket)).start();

            pool.submit(new MyRunnable(socket));
        }

    }
}