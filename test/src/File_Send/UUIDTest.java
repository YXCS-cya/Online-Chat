package File_Send;

import java.util.UUID;

public class UUIDTest {
    //作用：生成随机标识符，用于文件名
    //！！解决传输文件的名字重复问题
    //注：只需要在服务端更改即可，或者说，接收者更改即可
    public static void main(String[] args) {
        /*System.out.println(UUID.randomUUID());//这种方法会产生'-'，如：21a3a5ff-974d-42b2-b84c-aec113c701c9
        * */
        String str = UUID.randomUUID().toString().replace("-","");//将生成随机标识符中间的'-'改成无（也就是删掉了'-'）
        System.out.println(str);
    }
}
