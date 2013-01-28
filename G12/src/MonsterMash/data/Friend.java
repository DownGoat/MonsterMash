package data;

/**
 *
 * @author sjk4
 */
public class Friend {
    private int friendshipID;
    private int friendID, serverID;
    private String email;
    private boolean friendshipConfirmed;
    
    public Friend(int friendID, int serverID, String email, String confirmed){
        this.friendshipID = 0;
        this.friendID = friendID;
        this.serverID = serverID;
        this.email = email;
        if (confirmed.equals("Y")) {
            friendshipConfirmed = true;
        } else {
            friendshipConfirmed = false;
        }
    }

    public int getFriendshipID() {
        return friendshipID;
    }

    public void setFriendshipID(int friendshipID) {
        this.friendshipID = friendshipID;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFriendshipConfirmed() {
        return friendshipConfirmed;
    }

    public void setFriendshipConfirmed(boolean friendshipConfirmed) {
        this.friendshipConfirmed = friendshipConfirmed;
    }
    
    public void setFriendConfirmed(String confirmed) {
        if (confirmed.equals("Y")) {
            friendshipConfirmed = true;
        } else {
            friendshipConfirmed = false;
        }
    }
    
        public boolean isFriendshipConfirmed(Friend f) {
        return f.friendshipConfirmed;
    }
}
