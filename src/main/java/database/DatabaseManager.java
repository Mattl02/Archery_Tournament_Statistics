
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Matthias
 */
public class DatabaseManager {
    
    private static DatabaseManager theInstance;
    private Connection conn;

    public DatabaseManager() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/archery_tournament_statistics", "postgres", "postgres");
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
        Statement s = conn.createStatement();
        return s.executeQuery(query);
    }
    
    public int executeUpdate(String query) throws SQLException {
        Statement s = conn.createStatement();
        return s.executeUpdate(query);
    }
    
    public static synchronized DatabaseManager getInstance() throws SQLException{
        if(theInstance == null){
            theInstance = new DatabaseManager();
        }
        return theInstance;
    }
}
