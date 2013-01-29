package data;

/**
 *
 * @author sjk4
 */
public class Friend {
    private String friendshipID;
    private String remoteUserID, localUserID;
    private int serverID;
    private boolean friendshipConfirmed;
    
    public Friend(String friendshipID, String remoteUserID, String localUserID, int serverID, String confirmed){
        this.friendshipID = friendshipID;
        this.remoteUserID = remoteUserID;
        this.serverID = serverID;
        this.localUserID = localUserID;
        if (confirmed.equals("Y")) {
            friendshipConfirmed = true;
        } else {
            friendshipConfirmed = false;
        }
    }
    /*
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
    }*/

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

    public String getFriendshipID() {
        return friendshipID;
    }

    public void setFriendshipID(String friendshipID) {
        this.friendshipID = friendshipID;
    }

    public String getRemoteUserID() {
        return remoteUserID;
    }

    public void setRemoteUserID(String remoteUserID) {
        this.remoteUserID = remoteUserID;
    }

    public String getLocalUserID() {
        return localUserID;
    }

    public void setLocalUserID(String localUserID) {
        this.localUserID = localUserID;
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }
}
