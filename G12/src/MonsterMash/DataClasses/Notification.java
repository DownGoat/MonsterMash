package MonsterMash.DataClasses;

import MonsterMash.DataClasses.Player;
import java.util.Date;

public class Notification {

    /**
     * Attributes
     */
    private int id;
    private String text;
    private Player player;
    private Date timeSent;
    /**
     * Associations
     */
    private Player unnamed;

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

    public Player getUnnamed() {
        return unnamed;
    }

    public void setUnnamed(Player unnamed) {
        this.unnamed = unnamed;
    }
}
