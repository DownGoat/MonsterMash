package data;

/**
 *
 * @author sjk4
 */
public class Friend {

    private int id;
    private String email;
    private boolean friendshipConfirmed;



    public Friend(int id, String email, String confirmed) {
        this.id = id;
        this.email = email;
        if (confirmed.equals("Y")) {
            friendshipConfirmed = true;
        } else {
            friendshipConfirmed = false;
        }
    }

    public int getId(Friend f) {
        return f.id;
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
