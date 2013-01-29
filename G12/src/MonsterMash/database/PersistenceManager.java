package database;

import data.Monster;
import data.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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
     * Generates random string, which will be used as IDs for monsters/players etc.
     * @param length length of a string
     * @return random string with specified length
     */
    private String randomString(final int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
            sb.append(c);
        }
        return sb.toString();
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
            String id = p.getId();
            if(id.equals("0")){
                id = this.randomString(16);
            }
            // Add player to PLAYER table
            stmt.execute("INSERT INTO \"Player\" (\"id\", \"email\", \"password\", \"money\") VALUES ('"+id+"', "+p.getEmail()+"', '"+p.getPassword()+"', "+p.getMoney()+")");
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
                    stmt.execute("INSERT INTO \"Notification\" (\"short_message\", \"long_message\", \"player_id\", \"time\") VALUES ('"+n.getShortText()+"', '"+n.getLongText()+"', '"+n.getPlayer().getId()+"', '"+ts.toString()+"')", Statement.RETURN_GENERATED_KEYS);
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
            if(m.getId().equals("0")){ 
                try{
                    Statement stmt = connection.createStatement();
                    // Insert monster into DB
                    m.setId(this.randomString(16));
                    stmt.execute("INSERT INTO \"Monster\" (\"name\", \"dob\", \"dod\", \"base_strength\", \"current_strength\", \"base_defence\", \"current_defence\", \"base_health\", \"current_health\", \"fertility\", \"user_id\", \"sale_offer\", \"breed_offer\") "
                            + "VALUES ('"+m.getId()+"', "+m.getName()+"', "+m.getDob().getTime()+", "+m.getDod().getTime()+", "+m.getBaseStrength()+", "+m.getCurrentStrength()+", "+m.getBaseDefence()+", "+m.getCurrentDefence()+", "+m.getBaseHealth()+", "+m.getCurrentHealth()+", "+m.getFertility()+", '"+m.getUserID()+"', "+m.getSaleOffer()+", "+m.getBreedOffer()+")", Statement.RETURN_GENERATED_KEYS);

                }catch(SQLException sqlExcept){
                    System.err.println("Adding monster to DB error:\n"+sqlExcept.getMessage());
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
            selected = new Player(r.getString("id"), r.getString("email"), r.getString("password"), r.getInt("money"), this.getFriendList(r.getString("id")), this.getNotificationList(r.getString("id")), this.getMonsterList(r.getString("id")), r.getInt("server_id"));
            r.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
        return selected;
    }
    
    /**
     * Gets all friends from DB of specified player
     * @param playerID ID of player
     * @return list of friends and friend requests
     */
    public ArrayList<Player> getFriendList(String playerID){
        ArrayList<Player> friendList = new ArrayList<Player>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM \"Friendship\" WHERE (\"sender_id\" = '"+playerID+"' OR \"receiver_id\" = '"+playerID+"') AND \"confirmed\" = 'Y'");
            while(result.next()){
                if(result.getString("sender_id").equals(playerID+"")){
                    // Sender String id, String name, int serverID
                    friendList.add(new Player(result.getString("receiver_id"), this.getPlayerEmail(result.getString("receiver_id"), result.getInt("receiver_server_id")), result.getInt("receiver_server_id")));
                }else{
                    // Receiver
                    friendList.add(new Player(result.getString("sender_id"), this.getPlayerEmail(result.getString("sender_id"), result.getInt("sender_server_id")), result.getInt("sender_server_id")));
                }
            }
            result.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            System.err.println("Selecting friendships from DB error:\n"+sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return friendList;
    }
    
    /**
     * Gets all notifications from DB ordered by date.
     * @param playerID id of player
     * @return list of notifications
     */
    public ArrayList<Notification> getNotificationList(String playerID){
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
    public ArrayList<Monster> getMonsterList(String playerID){
        // TODO: Implement this method.
        return new ArrayList<Monster>();
    }
    
    /**
     * Gets player from DB with all monsters, notifications, friends.
     * @param id id of player
     * @return object of Player class, null when player doesn't exist
     */
    public Player getPlayer(String playerID){
        Player selected = null;
        try{
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Player\" WHERE \"id\" = '"+playerID+"'");
            r.next();
            selected = new Player(r.getString("id"), r.getString("email"), r.getString("password"), r.getInt("money"), this.getFriendList(r.getString("id")), this.getNotificationList(r.getString("id")), this.getMonsterList(r.getString("id")), r.getInt("server_id"));
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
     * @param senderServer address of sender's server
     * @param receiverID id of player who accepted request
     * @param receiverServer address of receivers's server
     */
    public void confirmFriendship(String senderID, int senderServer, String receiverID, int receiverServer){
        try{
            Statement stmt = connection.createStatement();
            stmt.execute("UPDATE \"Friendship\" SET \"confirmed\" = 'Y' WHERE \"receiver_id\" = '"+receiverID+"' AND \"receiver_server_id\" = "+receiverServer+" AND \"sender_id\" = '"+senderID+"' AND \"sender_server_id\" = "+senderServer+"");
            stmt.close();
        }catch(SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
    }
    
    /**
     * Cancle friendship request between players (senderID and receiverID)
     * @param senderID id of player who sent request
     * @param senderServer address of sender's server
     * @param receiverID id of player who accepted request
     * @param receiverServer address of receivers's server
     */
    public void rejectFriendship(String senderID, int senderServer, String receiverID, int receiverServer){
        try{
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM \"Friendship\" WHERE \"receiver_id\" = '"+receiverID+"' AND \"receiver_server_id\" = "+receiverServer+" AND \"sender_id\" = '"+senderID+"' AND \"sender_server_id\" = "+senderServer+"");
            stmt.close();
        }catch(SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
    }
    
    
    /**
     * Get highscores for logged player, ordered by amount of money
     * @param playerID id of logged player
     * @return ArrayList of HTML table rows
     */
    public ArrayList<String> getHighscores(String playerID){
        ArrayList<Player> friends =  this.getFriendList(playerID);
        ArrayList<String> friendIDs = new ArrayList<String>();
        ArrayList<String> toReturn = new ArrayList<String>();
        for(Player p: friends){
            if(p.getServerID() == 12){
                friendIDs.add(p.getId());
            }else{
                // TODO: get player's money amount from different server
            }
        }
        // Preparing query
        String query = "SELECT * FROM \"Player\" WHERE ";
        for(String s: friendIDs){
            query += "\"id\" = '"+s+"' OR ";
        }
        query = query.substring(0, query.length()-4);
        query += "ORDER BY \"money\"";
        try{
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
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
    
    
    
    
    
//    public void addNewFriends(Player p){
//        ArrayList<Player> toRemove = new ArrayList<Friend>();
//        for(Friend f: p.getFriends()){
//            // If friendship ID equals 0 it means user sent friend request and friendship hasn't been saved in DB
//            if(f.getFriendshipID() == 0){
//                try{
//                    Statement stmt = connection.createStatement();
//                    // Insert notification into DB
//                    String confirmed = "N";
//                    if(f.isFriendshipConfirmed()){
//                        confirmed = "Y";
//                    }
//                    stmt.execute("INSERT INTO \"Friendship\" (\"sender_id\", \"receiver_id\", \"sender_server_id\", \"receiver_server_id\", \"CONFIRMED\") VALUES ("+p.getId()+", "+f.getFriendID()+", 0, "+f.getServerID()+", '"+confirmed+"')", Statement.RETURN_GENERATED_KEYS);
//                    toRemove.add(f);
//                }catch(SQLException sqlExcept){
//                    System.err.println(sqlExcept.getMessage());
//                    this.error = sqlExcept.getMessage();
//                }
//            }
//        }
//        for(Friend f: toRemove){
//            p.getFriends().remove(f);
//        }
//    }
    
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
    public String getPlayerEmail(String playerID, int serverID){
        if(serverID == 12){
            String email = null;
            try{
                Statement stmt = connection.createStatement();
                ResultSet r = stmt.executeQuery("SELECT \"email\" FROM \"Player\" WHERE \"id\" = '"+playerID+"'");
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

    public Monster getMonster(int monsterID) {
        return null;
    }
    
//    public void addFriend(Friend friend) {
//        
//                    // Insert notification into DB
//        String confirmed = "N";
//        if(friend.isFriendshipConfirmed()){
//            confirmed = "Y";
//        }
//        Statement stmt;
//        try {
//            stmt = connection.createStatement();
//            stmt.execute("INSERT INTO \"Friendship\" (\"sender_id\", \"receiver_id\", \"sender_server_id\", \"receiver_server_id\", \"CONFIRMED\") VALUES ("+
//                friend.getRemoteAddress()+", "+friend.getLocalUserID()+", 0, "+friend.getRemoteAddress()+", '"+confirmed+"')", Statement.RETURN_GENERATED_KEYS);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            this.error = ex.getMessage();
//            Logger.getLogger(PersistenceManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}