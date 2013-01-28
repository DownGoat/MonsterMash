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
}
