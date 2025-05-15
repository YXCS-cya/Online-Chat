package User_data;

public class User implements java.io.Serializable{



    public User(String userid, String passwd){
        this.userid = userid;
        this.passwd = passwd;
    }

    public User() {

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    private String userid;
    private String passwd;


}
