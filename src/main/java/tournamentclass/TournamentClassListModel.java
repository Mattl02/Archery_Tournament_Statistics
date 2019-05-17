
package tournamentclass;

import database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * ListModel for the list of tournament-classes.
 * @author Matthias
 */
public class TournamentClassListModel extends AbstractListModel{

    private ArrayList<TournamentClass> classes = new ArrayList<>();
    private DatabaseManager dm;
    
    {
        try{
            dm = DatabaseManager.getInstance();
        }
        catch(SQLException se) {
            //classes.add(new TournamentClass("undefined"));
            se.printStackTrace();
        }
    }
    
    /**
     * Adds a class into the list of tournament-classes.
     * @param c The class to be added to the list of tournament-classes.
     */
    public void add (TournamentClass c) {
        classes.add(c);
        this.fireIntervalAdded(this, classes.size()-1, classes.size()-1);
    }
    
    /**
     * Removes a class from the list of tournament-classes.
     * @param idx The index of the element to be removed.
     */
    public void remove(int idx) {
        if(classes.get(idx).getClassName().equals("undefined")) return;
        classes.remove(idx);
        this.fireContentsChanged(this, 0, classes.size()-1);
    }
    
    /**
     * Removes multiple elements from the list of tournamet-classes.
     * @param idx The indices of the elements to be removed.
     */
    public void remove(int[] idx) {
        for(int i = idx.length-1; i >= 0; i--){
            if(classes.get(i).getClassName().equals("undefined")) continue;
            classes.remove(idx[i]);
        }
        this.fireContentsChanged(this, 0, classes.size()-1);
    }

    public ArrayList<TournamentClass> getClasses() {
        return classes;
    }
    /**
     * 
     * @return The number of tournament-classes in the list.
     */
    @Override
    public int getSize() {
        return classes.size();
    }

    /**
     * 
     * @param i The index of the element to be returned.
     * @return The object of an element at a specific index.
     */
    @Override
    public TournamentClass getElementAt(int i) {
        return classes.get(i);
    }
    
    /**
     * Loads tournament-classes from the database.
     * @throws SQLException SQL Exception
     */
    public void loadFromDatabase() throws SQLException {
        ResultSet res = dm.executeQuery("SELECT * FROM tournamentclasses");
        boolean undefined = false;
        while(res.next()) {
            if(res.getString("classname").equals("undefined")) undefined = true;
            classes.add(new TournamentClass(res.getString("classname")));
        }
        if(!undefined) classes.add(0, new TournamentClass("undefined"));
        this.fireIntervalAdded(this, 0, classes.size()-1);
    }
    
    /**
     * Saves the tournament-classes to the database.
     * @throws SQLException SQL Exception
     */
    public void saveToDatabase() throws SQLException {
        for(int i = 1; i < classes.size(); i++){
            ResultSet res = dm.executeQuery("SELECT * FROM tournamentclasses "
                    + "WHERE classname='"+classes.get(i).getClassName()+"';");
            if(res.next()){
                System.out.println(res.getString("classname"));
            }
            else {
                dm.executeUpdate("INSERT INTO tournamentclasses(classname) "
                        + "VALUES('"+classes.get(i).getClassName()+"');");
            }
        }
    }

}
