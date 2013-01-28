package unused;

// ## Implementation preserve start class opening. 
// ## Implementation preserve end class opening. 
import MonsterMash.DataClasses.Player;
import unused.Male;
import java.util.Date;
  

public class BreedingOffer   
 
{

    /**
     * Attributes
     */
    private int id;
    private Player player;
    private Male monster;
    private int moneyCost;
    private Date offerStartTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Male getMonster() {
        return monster;
    }

    public void setMonster(Male monster) {
        this.monster = monster;
    }

    public int getMoneyCost() {
        return moneyCost;
    }

    public void setMoneyCost(int moneyCost) {
        this.moneyCost = moneyCost;
    }

    public Date getOfferStartTime() {
        return offerStartTime;
    }

    public void setOfferStartTime(Date offerStartTime) {
        this.offerStartTime = offerStartTime;
    }
 
}
 