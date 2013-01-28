package unused;

import MonsterMash.DataClasses.Player;
import unused.Male;
import java.util.Date;

public class FightRequest {

    /**
     * Attributes
     */
    private int id;
    private Player sender;
    private Player reciver;
    private int moneyOffer;
    private Male monster;
    private Male opponentMonster;
    private Date offerSentTime;
    private String figthKey;

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

    public int getMoneyOffer() {
        return moneyOffer;
    }

    public void setMoneyOffer(int moneyOffer) {
        this.moneyOffer = moneyOffer;
    }

    public Male getMonster() {
        return monster;
    }

    public void setMonster(Male monster) {
        this.monster = monster;
    }

    public Male getOpponentMonster() {
        return opponentMonster;
    }

    public void setOpponentMonster(Male opponentMonster) {
        this.opponentMonster = opponentMonster;
    }

    public Date getOfferSentTime() {
        return offerSentTime;
    }

    public void setOfferSentTime(Date offerSentTime) {
        this.offerSentTime = offerSentTime;
    }

    public String getFigthKey() {
        return figthKey;
    }

    public void setFigthKey(String figthKey) {
        this.figthKey = figthKey;
    }
}
