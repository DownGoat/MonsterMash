package database;

import java.sql.*;

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
    
    public ResultSet select(String query){
        try{
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(query);
            results.close();
            stmt.close();
            return results;
        }catch (SQLException sqlExcept){
            this.error = sqlExcept.getMessage();
            return null;
        }
    }
    
    public String getErrorMessage(){
        return this.error;
    }
}
