package MonsterMash.DataClasses;

import MonsterMash.DataClasses.Player;
import MonsterMash.DataClasses.Monster;
import java.util.Date;

public class MarketOffer {

    /**
     * Attributes
     */
    private Player seller;
    private Monster monster;
    private Date offerSentTime;
    private int id;
    private int money;

    public Player getSeller() {
        return seller;
    }

    public void setSeller(Player seller) {
        this.seller = seller;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Date getOfferSentTime() {
        return offerSentTime;
    }

    public void setOfferSentTime(Date offerSentTime) {
        this.offerSentTime = offerSentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
