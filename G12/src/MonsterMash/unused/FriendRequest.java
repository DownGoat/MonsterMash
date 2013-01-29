package unused;

import MonsterMash.DataClasses.Player;
import java.util.Date;

public class FriendRequest {

    /**
     * Attributes
     */
    private int id;
    private Player sender;
    private Player reciver;
    private Date offerSentTime;
    private String localKey;
    private String remoteKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Player getReciver() {
        return reciver;
    }

    public void setReciver(Player reciver) {
        this.reciver = reciver;
    }

    public Date getOfferSentTime() {
        return offerSentTime;
    }

    public void setOfferSentTime(Date offerSentTime) {
        this.offerSentTime = offerSentTime;
    }

    public String getLocalKey() {
        return localKey;
    }

    public void setLocalKey(String localKey) {
        this.localKey = localKey;
    }

    public String getRemoteKey() {
        return remoteKey;
    }

    public void setRemoteKey(String remoteKey) {
        this.remoteKey = remoteKey;
    }
}
