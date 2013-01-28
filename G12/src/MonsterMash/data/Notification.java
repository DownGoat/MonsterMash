package data;
import java.util.Date;

public class Notification{
    /** Attributes */
    private int id;
    private String text;
    private Player player;
    private Date timeSent;
    
    public Notification(String text, Player player){
        this.id = 0;
        this.text = text;
        this.player = player;
        this.timeSent = new Date();
    }
    
    public Notification(int id, String text, Date timeSent){
        this.id = id;
        this.text = text;
        this.player = null;
        this.timeSent = timeSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }
    
    
}