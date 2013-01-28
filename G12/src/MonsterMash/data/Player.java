package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Player {
    /** Attributes */
    private int id;
    private String password;
    private String email;
    private int money;
    private ArrayList<Friend> friends;
    private ArrayList<Notification> notifications; 
    private ArrayList<Monster> monsters;

    /**
     * Creates object of a new player.
     * @param email username (email address)
     * @param password encrypted password
     * @param money default amount of money
     * @param initialMonsterName name of random initial monster
     */
    public Player(String email, String password, int money, String initialMonsterName){
    	this.id = 0; 
    	this.email = email;
    	this.password = password;
    	this.money = money;
        this.friends = new ArrayList<Friend>();
        this.notifications = new ArrayList<Notification>();
        this.monsters = new ArrayList<Monster>();
    	monsters.add(new Monster(initialMonsterName));
        // Add first notifications:
        notifications.add(new Notification("Account created successfully.", "LONG MESSAGE HERE", this));
        notifications.add(new Notification("Meet "+initialMonsterName+" - your first monster", "LONG MESSAGE HERE (you can access some monster attributes)", this));
    }
    
    /**
     * Creates object of a player selected from DB
     * @param id player's id from DB
     * @param email player's email address
     * @param password encrypted password
     * @param money current amount of money
     * @param friends list of player's friends
     * @param notifications list of player's notifications
     * @param monsters list of player's monsters
     */
    public Player(int id, String email, String password, int money, ArrayList<Friend> friends, ArrayList<Notification> notifications, ArrayList<Monster> monsters){
        this.id = id;
        this.email = email;
        this.password = password;
        this.money = money;
        this.friends = friends;
        this.notifications = notifications;
        this.monsters = monsters;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    
    public Friend getFriend(int i){
        return friends.get(i);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }
    
    
    
    /**
     * Operation
     *
     * @param friend
     * @return 
     */
    public void addFriend(Friend friend){
        friends.add(friend);
    }
    /**
     * Operation
     *
     * @param friend
     * @return 
     */
    public void removeFriend(Friend friend){
        // ## Implementation preserve start class method.removeFriend@@@@Player 
        // ## Implementation preserve end class method.removeFriend@@@@Player 
    	friends.remove(friend);
    }
    /**
     * Operation
     *
     * @param monster
     * @return 
     */
    public void addMonster ( Monster monster )
    {
        // ## Implementation preserve start class method.addMonster@@@@Monster 
        // ## Implementation preserve end class method.addMonster@@@@Monster 
    	monsters.add(monster);
    }
    /**
     * Operation
     *
     * @param monster
     * @return 
     */
    public void removeMonster ( Monster monster )
    {
        // ## Implementation preserve start class method.removeMonster@@@@Monster 
        // ## Implementation preserve end class method.removeMonster@@@@Monster 
    	monsters.remove(monster);
    }
    /**
     * Operation
     *
     * @param notefication
     * @return 
     */
    public void addNotification ( Notification notification )
    {
        // ## Implementation preserve start class method.addNotification@@@@Notefication 
        // ## Implementation preserve end class method.addNotification@@@@Notefication 
    	notifications.add(notification);
    }
    /**
     * Operation
     *
     * @return 
     */
    public void updateMonsters (  )
    {
        // ## Implementation preserve start class method.updateMonsters@@@ 
        // ## Implementation preserve end class method.updateMonsters@@@ 
//        for(int i = 0; i < monsters.size(); i++)
//        {
//                if(monsters.get(i).health <= 0)
//                        monsters.remove(i);
//        }
    }
    // ## Implementation preserve start class other.operations. 
    // ## Implementation preserve end class other.operations. 
}

// ## Implementation preserve start class closing. 
// ## Implementation preserve end class closing. 
