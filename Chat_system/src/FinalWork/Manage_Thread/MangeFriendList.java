package FinalWork.Manage_Thread;

import FinalWork.Client.FriendList;

import java.util.HashMap;

public class MangeFriendList {
    public static FriendList getFriendList(String id) {
        return (FriendList) hm.get(id);
    }

    public static void addFriendList(String id, FriendList friendList) {
        hm.put(id,friendList);
    }

    private static HashMap hm = new HashMap<String, FriendList>();


}
