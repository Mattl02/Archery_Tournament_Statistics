
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
    private static Connection conn;

    public DatabaseManager() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/archery_tournament_statistics", "postgres", "postgres");
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
        Statement s = conn.createStatement();
        return s.executeQuery(query);
    }
    
//    public static void main(String[] args) {
//        try {
//            DatabaseManager dm = new DatabaseManager();
//            ResultSet res = dm.executeQuery("SELECT * FROM tournaments");
//            res.next();
//            System.out.println(res.getString(2));
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
