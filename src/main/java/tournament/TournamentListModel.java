
package tournament;

import database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import participant.Participant;
import participant.ParticipantListModel;
import tournamentclass.TournamentClass;

/**
 *
 * @author Matthias
 */
public class TournamentListModel extends AbstractListModel{

    private ArrayList<Tournament> tournaments = new ArrayList<>();
    private DatabaseManager dm;
    
    {
        try {
            dm = DatabaseManager.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(TournamentListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds a tournament into the list of tournaments.
     * @param t The tournament to be added into the list
     */
    public void add (Tournament t){
        tournaments.add(t);
        this.fireIntervalAdded(this, tournaments.size()-1, tournaments.size()-1);
    }
    
    public void add (int idx, Tournament t){
        tournaments.add(idx, t);
        this.fireIntervalAdded(this, idx, tournaments.size()-1);
    }
    
    /**
     * Removes a tournament at a specific index from the list of tournaments.
     * @param idx The index of the element to be removed.
     */
    public void remove(int idx) {
        tournaments.remove(idx);
        this.fireContentsChanged(this, 0, tournaments.size()-1);
    }
    
    /**
     * Removes multiple tournaments at specific indices from the list of tournaments.
     * @param idx The indices of the elements to be removed.
     */
    public void remove(int[] idx) {
        for(int i = idx.length-1; i >= 0; i--){
            tournaments.remove(idx[i]);
        }
        this.fireContentsChanged(this, 0, tournaments.size()-1);
    }
    
    /**
     * 
     * @return The number of entries in the list
     */
    @Override
    public int getSize() {
        return tournaments.size();
    }

    /**
     * 
     * @param i The index of the element to be returned.
     * @return The object of the element at a specific index.
     */
    @Override
    public Object getElementAt(int i) {
        return tournaments.get(i);
    }
    
    public LinkedList<ParticipantListModel> loadFromDatabase() throws SQLException {
        LinkedList<ParticipantListModel> partModels = new LinkedList<>();
        ResultSet res = dm.executeQuery("SELECT * FROM tournaments;");
        while(res.next()){
            Tournament t = new Tournament(res.getString("name"), res.getDate("date").toLocalDate());
            this.add(t);
            ParticipantListModel pm = new ParticipantListModel(t);
            try{
                ResultSet resP = dm.executeQuery("SELECT * FROM tournament"+
                        res.getInt("tournamentid"));
                while(resP.next()){
                    Participant p = new Participant(resP.getString("name"), new TournamentClass(resP.getString("tournamentClass")));
                    p.addScore(resP.getInt("score"));
                    pm.add(p);
                }
            }
            catch(Exception ex) {}
            partModels.add(pm);
        }
        this.fireIntervalAdded(this, 0, tournaments.size()-1);
        return partModels;
    }
    
    public void saveToDatabase() throws SQLException {
        for (Tournament t : tournaments) {
            ResultSet res = dm.executeQuery("SELECT * FROM tournaments WHERE name='"
                    +t.getName()+"' AND date=TO_DATE('"
                    +t.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    +"','DD.MM.YYYY');");
            if(res.next()){
                System.out.println(res.getInt("tournamentid")+"");
            }
            else{
                dm.executeUpdate("INSERT INTO tournaments(name,date) VALUES('"
                        +t.getName()+"',TO_DATE('"
                        +t.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        +"','DD.MM.YYYY'));");
            }
        }   
    }
}
