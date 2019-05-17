
package tournament;

import database.DatabaseManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JFileChooser;
import participant.Participant;
import participant.ParticipantListModel;
import tournamentclass.TournamentClass;

/**
 * ListModel for tournaments
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
    
    /**
     * Loads tournaments from the database.
     * @return A list of the participant-listmodels for each tournament.
     * @throws SQLException SQL Exception
     */
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
    
    /**
     * Saves the tournaments to the database.
     * @throws SQLException SQL Exception
     */
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
    
    /**
     * Loads a tournament from a file.
     * @return A list of participants in the tournament loaded
     * @throws IOException IOException
     */
    public ArrayList<Participant> loadFromFile() throws IOException {
        JFileChooser chooser = new JFileChooser();
        ArrayList<Participant> participants = new ArrayList<>();
        Tournament tournament = null;
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] parts = line.split(",");
            tournament = new Tournament(parts[0], LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            while((line = br.readLine()) != null){
                parts = line.split(",");
                Participant p = new Participant(parts[0], new TournamentClass(parts[1]));
                p.addScore(Integer.parseInt(parts[2]));
                participants.add(p);
            }
        }
        
        this.add(tournament);
        return participants;
    }
}
