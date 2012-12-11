package MonsterMash.DataClasses;

// ## Implementation preserve start class opening. 
// ## Implementation preserve end class opening. 
import MonsterMash.DataClasses.Player;
import MonsterMash.DataClasses.Monster;
import MonsterMash.DataClasses.FriendRequest;
import MonsterMash.DataClasses.FightRequest;
import MonsterMash.DataClasses.MarketOffer;
import MonsterMash.DataClasses.BreedingOffer;
import java.util.ArrayList;

public class Player {

    /**
     * Attributes
     */
    private int id;
    private String password;
    private String email;
    private ArrayList<Player> friends;
    private int money;
    /**
     * Associations
     */
    private ArrayList<Monster> monsters;
    private ArrayList<Notification> notifications;

    /**
     * Operation
     *
     * @return
     */
    public void createInitialMonster() {
    }

    /**
     * Operation
     *
     * @param friend
     * @return
     */
    public void addFriend(Player friend) {
        friends.add(friend);
    }

    /**
     * Operation
     *
     * @param friend
     * @return
     */
    public void removeFriend(Player friend) {
        friends.remove(friend);
    }

    /**
     * Operation
     *
     * @param monster
     * @return
     */
    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    /**
     * Operation
     *
     * @param monster
     * @return
     */
    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }

    /**
     * Operation
     *
     * @param notefication
     * @return
     */
    public void addNotification(Notification notefication) {
        notifications.add(notefication);
    }

    /**
     * Operation
     *
     * @return
     */
    public void updateMonsters() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Player> friends) {
        this.friends = friends;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Monster>  getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster>  monsters) {
        this.monsters = monsters;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }
}
