package User_data;
/*
* 定义包的种类
* */
public interface MessageType {

    String message_user = "1";
  //  String message_friend = "2";//一开始希望只通过一个信息类型就能处理好友相关信息传输
    //但在实施过程中发现这种方式太冗杂，故拆分成4/5两个信息类型
    String message_recoder = "2";//聊天记录
    String message_mes = "3";
    String message_get_olFriend = "4";//请求在线好友
    String message_ret_olFriend = "5";//返回在线好友


}
