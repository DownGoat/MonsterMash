package data;
import java.util.Date;

public class Notification{
    /** Attributes */
    private int id;
    private String shortText;
    private String longText;
    private Player player;
    private Date timeSent;
    
    public Notification(String shortText, String longText, Player player){
        this.id = 0;
        this.shortText = shortText;
        this.longText = longText;
        this.player = player;
        this.timeSent = new Date();
    }
    
    public Notification(int id, String shortText, String longText, Date timeSent){
        this.id = id;
        this.shortText = shortText;
        this.longText = longText;
        this.player = null;
        this.timeSent = timeSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
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