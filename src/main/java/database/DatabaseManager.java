
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * The DatabaseManager manages the access to the database.
 * @author Matthias
 */
public class DatabaseManager {
    
    private static DatabaseManager theInstance;
    private Connection conn;

    public DatabaseManager() throws SQLException {
        String s = JOptionPane.showInputDialog(null, "Please enter the name of the database:");
        if(s != null) {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+s, "postgres", "postgres");
        }
//        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/archery_tournament_statistics", "postgres", "postgres");
    }
    
    /**
     * Executes a query.
     * @param query The query to be executed
     * @return The ResultSet of the query
     * @throws SQLException SQL Exception
     */
    public ResultSet executeQuery(String query) throws SQLException {
        Statement s = conn.createStatement();
        return s.executeQuery(query);
    }
    
    /**
     * Executes a query and update.
     * @param query The query to be executed
     * @return The ResultSet of the query
     * @throws SQLException SQL Exception
     */
    public int executeUpdate(String query) throws SQLException {
        Statement s = conn.createStatement();
        return s.executeUpdate(query);
    }
    
    /**
     * 
     * @return The DatabaseManager Instance
     * @throws SQLException SQL Exception
     */
    public static synchronized DatabaseManager getInstance() throws SQLException{
        if(theInstance == null){
            theInstance = new DatabaseManager();
        }
        return theInstance;
    }
}
