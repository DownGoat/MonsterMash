package data;

/**
 *
 * @author sjk4
 */
public class Friend {
    private int id;
    private String email;
    private boolean friendshipConfirmed;
    
    public Friend(int id, String email, String confirmed){
        this.id = id;
        this.email = email;
        if(confirmed.equals("Y")){
            friendshipConfirmed = true;
        }else{
            friendshipConfirmed = false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
}
