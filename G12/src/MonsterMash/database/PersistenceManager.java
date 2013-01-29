package database;

import data.Monster;
import data.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author sjk4
 */
public class PersistenceManager {
    private final String dbname = "MonsterMash";
    private final String dbuser = "root";
    private final String dbpassword = "root";
    private final String dbhost = "localhost";
    private final String dbport = "1527";
    
    private Connection connection;
    private String error;
    
    public PersistenceManager(){
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String connectionURL = "jdbc:derby://"+dbhost+":"+dbport+"/"+dbname+";create=true;user="+dbuser+";password="+dbpassword;
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks if there is an account with specified email address.
     * @param email user's email address
     * @return true if account exists
     */
    public boolean accountExists(String email){
        int count = 0;
        try{
            Statement stmt = connection.createStatement();
            stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT count(\"id\") FROM \"Player\" WHERE \"email\" = '"+email+"'");
            results.next();
            count = results.getInt(1);
            results.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
        if(count > 0){
            return true;
        }
        return false;
    }
    
    /**
     * Store player with monsters and notifications in DB.
     * @param email user's email address
     * @param password encrypted password (md5)
     * @param money start amount of money
     * @return true when account created successfully
     */
    public void storePlayer(Player p){
        try{
            Statement stmt = connection.createStatement();
            // Add player to PLAYER table
            stmt.execute("INSERT INTO \"Player\" (\"email\", \"password\", \"money\") VALUES ('"+p.getEmail()+"', '"+p.getPassword()+"', "+p.getMoney()+")", Statement.RETURN_GENERATED_KEYS);
            // Set new player id
            ResultSet rs = stmt.getGeneratedKeys(); 
            if(rs != null && rs.next()) {
                p.setId(rs.getInt(1));
            }
            stmt.close();
            // Save first notification in DB
            this.storeNotifications(p);
            // Save initial monster in DB
            this.storeMonsters(p);
        }catch(SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
    }
    
    /**
     * Stores all notifications with ID = 0 (which haven't been saved in DB)
     * @param p object of Player class
     */
    public void storeNotifications(Player p){
        for(Notification n: p.getNotifications()){
            // If ID equals 0, notifications hasn't been saved in DB
            if(n.getId() == 0){
                try{
                    Statement stmt = connection.createStatement();
                    Timestamp ts = new Timestamp(n.getTimeSent().getTime());
                    // Insert notification into DB
                    stmt.execute("INSERT INTO \"Notification\" (\"message\", \"long_message\", \"player_id\", \"time\") VALUES ('"+n.getShortText()+"', '"+n.getLongText()+"', "+n.getPlayer().getId()+", '"+ts.toString()+"')", Statement.RETURN_GENERATED_KEYS);
                    // Set ID of notification
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs != null && rs.next()){
                        n.setId(rs.getInt(1));
                    }
                }catch(SQLException sqlExcept){
                    System.err.println(sqlExcept.getMessage());
                    this.error = sqlExcept.getMessage();
                }
            }
        }
    }
    
    /**
     * Stores all monsters with ID = 0 (which haven't been saved in DB)
     * @param p object of Player class
     */
    public void storeMonsters(Player p){
        for(Monster m: p.getMonsters()){
            // If ID equals 0, monster hasn't been saved in DB
            if(m.getId() == 0){ 
                try{
                    Statement stmt = connection.createStatement();
                    Timestamp ts = new Timestamp(m.getDob().getTime());
                    // Insert monster into DB
//                    stmt.execute("INSERT INTO \"Monster\" (\"name\", \"dob\", \"genetic_strength\", \"speed\", \"accuracy\", \"armor\", \"dodge\", \"age_rate\", \"fertility\", \"health\", \"owner_id\") "
//                            + "VALUES ('"+m.getName()+"', '"+ts.toString()+"', "+m.getGenetic_strength()+", "+m.getSpeed()+", "+m.getAccuracy()+", "+m.getArmor()+", "+m.getDodge()+", "+m.getAge_rate()+", "+m.getFertility()+", "+m.getHealth()+", "+p.getId()+")", Statement.RETURN_GENERATED_KEYS);
                    // Set ID of monster
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs != null && rs.next()){
                        m.setId(rs.getInt(1));
                    }
                }catch(SQLException sqlExcept){
                    this.error = sqlExcept.getMessage();
                }
            }
        }
    }
    
    /**
     * Gets player object of DB, returns null when user doesn't exist
     * @param email user's email address
     * @param password encrypted password using MD5
     * @return object of player class with all monsters, notifications and friends
     */
    public Player doLogin(String email, String password){
        Player selected = null;
        try{
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Player\" WHERE \"email\" = '"+email+"' AND \"password\" = '"+password+"'");
            r.next();
            selected = new Player(r.getInt("id"), r.getString("email"), r.getString("password"), r.getInt("money"), this.getFriendList(r.getInt("id")), this.getNotificationList(r.getInt("id")), this.getMonsterList(r.getInt("id")));
            r.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
        return selected;
    }
    
    /**
     * Gets all friends and friend requests from DB of specified player
     * @param playerID ID of player
     * @return list of friends and friend requests
     */
    public ArrayList<Friend> getFriendList(int playerID){
        ArrayList<Friend> friendList = new ArrayList<Friend>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM \"Friendship\" WHERE (\"sender_id\" = "+playerID+" OR \"receiver_id\" = "+playerID+")");
            while(result.next()){
                // If friendship confirmed check whether logged player is sender or receiver
                if(result.getString("CONFIRMED").equals("Y")){
                    if(result.getInt("sender_id") == playerID){
                        friendList.add(new Friend(result.getInt("id"), result.getInt("receiver_id"), result.getInt("receiver_server_id"), this.getPlayersEmail(result.getInt("receiver_id"), result.getInt("receiver_server_id")), result.getString("CONFIRMED")));
                    }else{
                        friendList.add(new Friend(result.getInt("id"), result.getInt("sender_id"), result.getInt("sender_server_id"), this.getPlayersEmail(result.getInt("sender_id"), result.getInt("sender_server_id")), result.getString("CONFIRMED")));
                    }
                }else{
                    // If friendship unconfirmed, select only friend requests (logged player id == receiver id)
                    if(result.getInt("receiver_id") == playerID){
                        friendList.add(new Friend(result.getInt("id"), result.getInt("sender_id"), result.getInt("sender_server_id"), this.getPlayersEmail(result.getInt("sender_id"), result.getInt("sender_server_id")), result.getString("CONFIRMED")));
                    }
                }
            }
            result.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
        return friendList;
    }
    
    /**
     * Gets all notifications from DB ordered by date.
     * @param playerID id of player
     * @return list of notifications
     */
    public ArrayList<Notification> getNotificationList(int playerID){
        ArrayList<Notification> notificationList = new ArrayList<Notification>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM \"Notification\" WHERE \"player_id\" = "+playerID+" ORDER BY \"time\" DESC");
            while(result.next()){
                notificationList.add(new Notification(result.getInt("id"), result.getString("message"), result.getString("long_message"), result.getDate("time")));
            }
            result.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return notificationList;
    }
    
    /**
     * Gets a list of monsters owned by this player
     * @param playerID id of player
     * @return list of monsters owned
     */
    public ArrayList<Monster> getMonsterList(int playerID){
        // TODO: Implement this method.
        return new ArrayList<Monster>();
    }
    
    /**
     * Gets player from DB with all monsters, notifications, friends.
     * @param id id of player
     * @return object of Player class, null when player doesn't exist
     */
    public Player getPlayer(int playerID){
        Player selected = null;
        try{
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Player\" WHERE \"id\" = "+playerID);
            r.next();
            selected = new Player(r.getInt("id"), r.getString("email"), r.getString("password"), r.getInt("money"), this.getFriendList(r.getInt("id")), this.getNotificationList(r.getInt("id")), this.getMonsterList(r.getInt("id")));
            r.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
        return selected;
    }
    
    /**
     * Confirms friendship between players (senderID and receiverID)
     * @param senderID id of player who sent request
     * @param receiverID id of player who accepted request
     */
    public void confirmFriendship(int senderID, int receiverID){
        try{
            Statement stmt = connection.createStatement();
            stmt.execute("UPDATE \"Friendship\" SET \"CONFIRMED\" = 'Y' WHERE \"receiver_id\" = "+receiverID+" AND \"sender_id\" = "+senderID+"");
            stmt.close();
        }catch(SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
    }
    
    /**
     * Cancle friendship request between players (senderID and receiverID)
     * @param senderID id of player who sent request
     * @param receiverID id of player who accepted request
     */
    public void rejectFriendship(int senderID, int receiverID){
        try{
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM \"Friendship\" WHERE \"sender_id\" = "+senderID+" AND \"receiver_id\" = "+receiverID+"");
            stmt.close();
        }catch(SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
    }
    
    
    public ArrayList<String> getHighscores(){
         ArrayList<String> toReturn = new ArrayList<String>();
         try{
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM \"Player\" ORDER BY \"money\" DESC");
            int i = 1;
            while(result.next()){
                toReturn.add("<tr><td>"+i+".</td><td><b>"+result.getString("email")+"</b></td><td>"+result.getInt("money")+"$</td></tr>");
                i++;
            }
            result.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
        return toReturn;
    }
    
    
    
    
    
    public void addNewFriends(Player p){
        ArrayList<Friend> toRemove = new ArrayList<Friend>();
        for(Friend f: p.getFriends()){
            // If friendship ID equals 0 it means user sent friend request and friendship hasn't been saved in DB
            if(f.getFriendshipID() == 0){
                try{
                    Statement stmt = connection.createStatement();
                    // Insert notification into DB
                    String confirmed = "N";
                    if(f.isFriendshipConfirmed()){
                        confirmed = "Y";
                    }
                    stmt.execute("INSERT INTO \"Friendship\" (\"sender_id\", \"receiver_id\", \"sender_server_id\", \"receiver_server_id\", \"CONFIRMED\") VALUES ("+p.getId()+", "+f.getFriendID()+", 0, "+f.getServerID()+", '"+confirmed+"')", Statement.RETURN_GENERATED_KEYS);
                    toRemove.add(f);
                }catch(SQLException sqlExcept){
                    System.err.println(sqlExcept.getMessage());
                    this.error = sqlExcept.getMessage();
                }
            }
        }
        for(Friend f: toRemove){
            p.getFriends().remove(f);
        }
    }
    
    public int getPlayerID(String email){
        int id = 0;
        try{
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT \"id\" FROM \"Player\" WHERE \"email\" = '"+email+"'");
            results.next();
            id = results.getInt("id");
            results.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return id;
    }
    
    
    
    
    
    
    
    // TODO: needs server-server communication
    public String getPlayersEmail(int playerID, int serverID){
        if(serverID == 0){
            String email = null;
            try{
                Statement stmt = connection.createStatement();
                ResultSet r = stmt.executeQuery("SELECT \"email\" FROM \"Player\" WHERE \"id\" = "+playerID+"");
                r.next();
                email = r.getString("email");
                r.close();
                stmt.close();
            }catch (SQLException sqlExcept){
                System.err.println(sqlExcept.getMessage());
                this.error = sqlExcept.getMessage();
            }
            return email;
        }else{
            return "Player Outside";
        }
    }
    
    
    
    
    
    public boolean isFriendRequestSent(int playerOne, int playerTwo){
        int count = 0;
        try{
            Statement stmt = connection.createStatement();
            stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT \"id\" FROM \"Friendship\" WHERE (\"sender_id\" = "+playerOne+" AND \"receiver_id\" = "+playerTwo+") OR (\"sender_id\" = "+playerTwo+" AND \"receiver_id\" = "+playerOne+")");
            while(results.next()){
                count++;
            }
            results.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
            count = -1;
        }
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean insert(String query){
        try{
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            stmt.close();
            return true;
        }catch(SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
            return false;
        }
    }
    
    public int count(String query){
        try{
            Statement stmt = connection.createStatement();
            stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(query);
            int count = 0;
            while(results.next()){
                count++;
            }
            results.close();
            stmt.close();
            return count;
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
            return -1;
        }
    }

     public ArrayList<String> getFriendRequestList(long id){
        ArrayList<String> toReturn = new ArrayList<String>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM \"Friendship\" WHERE \"receiver_id\" = "+id+" AND \"CONFIRMED\" = 'N'");
            while(results.next()){
                int tmp = 0;
                if(results.getInt(2) == id){
                    tmp = results.getInt(3);
                }else{
                    tmp = results.getInt(2);
                }
                ResultSet res = stmt.executeQuery("SELECT * FROM \"Player\" WHERE \"id\" = "+tmp+"");
                res.next();
                toReturn.add(res.getString(2));
                res.close();
            }
            results.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
        return toReturn;
    }
    
    public String getErrorMessage(){
        return this.error;
    }
}
