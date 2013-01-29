package data;

/**
 *
 * @author sjk4
 */
public class Friend {
    private String friendshipID;
    private String remoteUserID, localUserID;
    private String remoteAddress;
    private boolean friendshipConfirmed;
    
    public Friend(String friendshipID, String remoteUserID, String localUserID, String remoteAddress, String confirmed){
        this.friendshipID = friendshipID;
        this.remoteUserID = remoteUserID;
        this.remoteAddress = remoteAddress;
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

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
}
