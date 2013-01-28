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
     * Checks if there is account with that email address.
     * @param email user's email address
     * @return true if account exists
     */
    public boolean accountExists(String email){
        int count = 0;
        try{
            Statement stmt = connection.createStatement();
            stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT \"id\" FROM \"Player\" WHERE \"email\" = '"+email+"'");
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
    
    /**
     * Creates new account, saves error message, when exception catched.
     * @param email user's email address
     * @param password encrypted password (md5)
     * @param money start amount of money
     * @return true when account created successfully
     */
    public void addNewPlayer(Player p){
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
            this.addNewNotifications(p);
            // Save initial monster in DB
            this.addNewMonsters(p);
        }catch(SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
    }
    
    /**
     * Updates notifications, which haven't been saved in DB.
     * @param p object of Player class
     */
    public void addNewNotifications(Player p){
        for(Notification n: p.getNotifications()){
            // If ID equals 0, notifications hasn't been saved in DB
            if(n.getId() == 0){
                try{
                    Statement stmt = connection.createStatement();
                    Timestamp ts = new Timestamp(n.getTimeSent().getTime());
                    // Insert notification into DB
                    stmt.execute("INSERT INTO \"Notification\" (\"message\", \"player_id\", \"time\") VALUES ('"+n.getText()+"', "+n.getPlayer().getId()+", '"+ts.toString()+"')", Statement.RETURN_GENERATED_KEYS);
                    // Set ID of notification
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs != null && rs.next()){
                        n.setId(rs.getInt(1));
                    }
                }catch(SQLException sqlExcept){
                    this.error = sqlExcept.getMessage();
                }
            }
        }
    }
    
    /**
     * Updates monsters, which haven't been saved in DB.
     * @param p object of Player class
     */
    public void addNewMonsters(Player p){
        for(Monster m: p.getMonsters()){
            // If ID equals 0, monster hasn't been saved in DB
            if(m.getId() == 0){ 
                try{
                    Statement stmt = connection.createStatement();
                    Timestamp ts = new Timestamp(m.getDob().getTime());
                    // Insert monster into DB
                    stmt.execute("INSERT INTO \"Monster\" (\"name\", \"dob\", \"genetic_strength\", \"speed\", \"accuracy\", \"armor\", \"dodge\", \"age_rate\", \"fertility\", \"health\", \"owner_id\") "
                            + "VALUES ('"+m.getName()+"', '"+ts.toString()+"', "+m.getGenetic_strength()+", "+m.getSpeed()+", "+m.getAccuracy()+", "+m.getArmor()+", "+m.getDodge()+", "+m.getAge_rate()+", "+m.getFertility()+", "+m.getHealth()+", "+p.getId()+")", Statement.RETURN_GENERATED_KEYS);
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
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return selected;
    }
    
    public ArrayList<Friend> getFriendList(int playerID){
        ArrayList<Friend> friendList = new ArrayList<Friend>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet friendsResults = stmt.executeQuery("SELECT * FROM \"Friendship\" WHERE (\"sender_id\" = "+playerID+" OR \"receiver_id\" = "+playerID+")");
            while(friendsResults.next()){
                int friendID;
                if(friendsResults.getInt("sender_id") == playerID){
                    friendID = friendsResults.getInt("receiver_id");
                }else{
                    friendID = friendsResults.getInt("sender_id");
                }
                ResultSet singleFriendResult = stmt.executeQuery("SELECT \"id\", \"email\" FROM \"Player\" WHERE \"id\" = "+friendID+"");
                singleFriendResult.next();
                friendList.add(new Friend(singleFriendResult.getInt("id"), singleFriendResult.getString("email"), singleFriendResult.getString("CONFIRMED")));
                singleFriendResult.close();
            }
            friendsResults.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return friendList;
    }
    
    public ArrayList<Notification> getNotificationList(int playerID){
        ArrayList<Notification> notificationList = new ArrayList<Notification>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM \"Notification\" WHERE \"player_id\" = "+playerID+"");
            while(result.next()){
                notificationList.add(new Notification(result.getInt("id"), result.getString("message"), result.getDate("time")));
            }
            result.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return notificationList;
    }
    
    public ArrayList<Monster> getMonsterList(int playerID){
        return null;
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
    
    public long getPlayerID(String email){
        long id = 0;
        try{
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT \"id\" FROM \"Player\" WHERE \"email\" = '"+email+"'");
            results.next();
            id = results.getLong(1);
            results.close();
            stmt.close();
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
        }
        return id;
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
    
    public void sendFriendRequest(long fromID, String emailTo){
        long toID = getPlayerID(emailTo);
        if(toID != 0){
            insert("INSERT INTO \"Friendship\" (\"sender_id\", \"receiver_id\", \"sender_server_id\", \"receiver_server_id\", \"CONFIRMED\") VALUES ("+fromID+", "+toID+", 0, 0, 'N')");
        }
    }
    
    public void acceptFriendRequest(long fromID, String toEmail){
        long toID = getPlayerID(toEmail);
        if(toID != 0){
            insert("UPDATE \"Friendship\" SET \"CONFIRMED\" = 'Y' WHERE \"sender_id\" = "+toID+" AND \"receiver_id\" = "+fromID+"");
        }
    }
    
    public void cancelFriendRequest(long fromID, String toEmail){
        long toID = getPlayerID(toEmail);
        if(toID != 0){
            insert("DELETE FROM \"Friendship\" WHERE \"sender_id\" = "+toID+" AND \"receiver_id\" = "+fromID+"");
        }
    }
    
    public String getErrorMessage(){
        return this.error;
    }
}
