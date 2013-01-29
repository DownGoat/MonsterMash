/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import data.Friend;
import data.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sis13
 */
public class OtherPersistenceManager extends PersistenceManager {

    private final String dbname = "MonsterMash";
    private final String dbuser = "root";
    private final String dbpassword = "root";
    private final String dbhost = "localhost";
    private final String dbport = "1527";
    private Connection connection;
    private String error;

    public OtherPersistenceManager() {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String connectionURL = "jdbc:derby://" + dbhost + ":" + dbport + "/" + dbname + ";create=true;user=" + dbuser + ";password=" + dbpassword;
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

    public void acceptFriendRequest(Friend friend) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("UPDATE \"Friendship\" SET \"CONFIRMED\" = 'Y' WHERE \"id\" = '" + friend.getFriendshipID() + "'");
            stmt.close();
        } catch (SQLException sqlExcept) {
            this.error = sqlExcept.getMessage();
        }
    }

    public Friend getFriend(String friendID) {
        Friend friend = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM \"Friendship\" WHERE \"id\" = '" + friendID + "'");
            results.next();
            friend = new Friend(results.getString("id"), results.getString("reciver_id"), results.getString("sender_id"), results.getInt("reciver_server_id"), results.getString("confirmed"));
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }

        return friend;
    }

    public void rejectFriend(Friend friend) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM \"Friendship\" WHERE \"id\" = '" + friend.getFriendshipID() + "'");
            stmt.close();
        } catch (SQLException sqlExcept) {
            this.error = sqlExcept.getMessage();
        }
    }

    public void addFriend(Friend friend) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.execute("INSERT INTO \"Friendship\" (\"id\", \"sender_id\", \"receiver_id\", \"sender_server_id\", \"receiver_server_id\", \"CONFIRMED\") VALUES ("+
                friend.getFriendshipID()+", "+
                    friend.getLocalUserID()+", 0, "+
                    friend.getRemoteAddress()+", '"+
                    confirmed+"')",
                    Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            this.error = ex.getMessage();
            Logger.getLogger(PersistenceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM \"Player\"");
            while (r.next()) {
                players.add(new Player(r.getString("id"), r.getString("email"), r.getString("password"), r.getInt("money"), this.getFriendList(r.getString("id")), this.getNotificationList(r.getString("id")), this.getMonsterList(r.getString("id")), r.getInt("server_id")));
            }
            r.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            System.err.println(sqlExcept.getMessage());
            this.error = sqlExcept.getMessage();
        }
        return players;
    }
}
