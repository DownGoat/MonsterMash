package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Player {
    /** Attributes */
    private String userID;
    private String password;
    private String username;
    private int money;
    private int serverID;
    private ArrayList<Player> friends;
    private ArrayList<Notification> notifications; 
    private ArrayList<Monster> monsters;

    /**
     * Creates object of a new "friend".
     * @param email username (email address)
     * @param password encrypted password
     * @param money default amount of money
     * @param initialMonsterName name of random initial monster
     */
    public Player(String userID, String username, int serverID){
    	this.userID = userID; 
    	this.username = username;
    	this.password = null;
        this.friends = null;
        this.notifications = null;
        this.monsters = null;
        this.serverID = serverID;
    }
    
    /**
     * Creates object of a new player.
     * @param email username (email address)
     * @param password encrypted password
     * @param money default amount of money
     * @param initialMonsterName name of random initial monster
     */
    public Player(String userID, String username, String password, int money, String initialMonsterName){
    	this.userID = userID;
    	this.username = username;
    	this.password = password;
    	this.money = money;
        this.friends = new ArrayList<Player>();
        this.notifications = new ArrayList<Notification>();
        this.monsters = new ArrayList<Monster>();
        this.serverID = 12;
    	monsters.add(new Monster(initialMonsterName, this.userID));
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
    public Player(String userID, String username, String password, int money, ArrayList<Player> friends, ArrayList<Notification> notifications, ArrayList<Monster> monsters, int serverID){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.money = money;
        this.friends = friends;
        this.notifications = notifications;
        this.monsters = monsters;
        this.serverID = serverID;
    }
    
    public Player() {
        
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }

    
    public Player getFriend(int i){
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

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }
    
    
    
    /**
     * Operation
     *
     * @param friend
     * @return 
     */
    public void addFriend(Player friend){
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
