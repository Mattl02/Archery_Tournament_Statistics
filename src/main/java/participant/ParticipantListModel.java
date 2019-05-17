package participant;

import database.DatabaseManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.JFileChooser;
import tournament.Tournament;
import tournamentclass.TournamentClass;

/**
 * The ListModel for participants.
 * @author Matthias
 */
public class ParticipantListModel extends AbstractListModel{

    private ArrayList<Participant> participants = new ArrayList<>();
    private Tournament tournament;
    private DatabaseManager dm;
    
    {
        try{
            dm = DatabaseManager.getInstance();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    public ParticipantListModel(Tournament tournament) {
        this.tournament = tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    /**
     * Adds a participant to the list of participants.
     * @param p The participant to be added
     */
    public void add (Participant p){
        participants.add(p);
        this.fireIntervalAdded(this, participants.size()-1, participants.size()-1);
    }
    
    /**
     * Adds a particpant to the list of participant at a given index.
     * @param idx The position at which the participant will be added
     * @param p The participant to be added
     */
    public void add (int idx, Participant p){
        participants.add(idx, p);
        this.fireIntervalAdded(this, idx, participants.size()-1);
    }
    
    /**
     * Removes a participant from the list of participants.
     * @param idx The index of the participant to be removed
     */
    public void remove(int idx) {
        participants.remove(idx);
        this.fireContentsChanged(this, 0, participants.size()-1);
    }
    
    /**
     * Removes a number of participants from the list of participants.
     * @param idx The indices of the participants to be removed
     */
    public void remove(int[] idx) {
        for(int i = idx.length-1; i >= 0; i--){
            participants.remove(idx[i]);
        }
        this.fireContentsChanged(this, 0, participants.size()-1);
    }
    
    /**
     * Sets the classes of participants.
     * @param indices The indices of the participants to be modified
     * @param c The class to be assigned to the participants
     */
    public void setClasses(int[] indices, TournamentClass c){
        for(int i = indices.length-1; i >= 0; i--) {
            participants.get(indices[i]).setTournamentclass(c);
        }
        this.fireContentsChanged(this, 0, participants.size()-1);
    }
    
    /**
     * Adds points to the score of a participant at a certain index.
     * @param points The amount of points to be added
     * @param idx The index of the participant to be modified.
     */
    public void addPointsTo(int points, int idx){
        participants.get(idx).addScore(points);
        
    }
    
    /**
     * 
     * @return The number of entries in the list
     */
    @Override
    public int getSize() {
        return participants.size();
    }

    /**
     * 
     * @param i The index of the element to be returned.
     * @return The object of the element at a specific index.
     */
    @Override
    public Object getElementAt(int i) {
        return participants.get(i);
    }
    
    /**
     * Saves the participants of a tournament to the database.
     * Every tournament makes its own table of participants in the database.
     * @throws SQLException SQL Exception
     */
    public void saveToDatabase() throws SQLException{
        ResultSet res;
        res = dm.executeQuery("SELECT tournamentid FROM tournaments "
                + "WHERE name='"+tournament.getName()+"' AND "
                + "date=TO_DATE('"+tournament.getDate()
                                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                +"', 'DD.MM.YYYY');");
        if (res.next()) {
            String tablename = "tournament" + (res.getInt("tournamentid") + "");
            try{
                dm.executeUpdate("DROP TABLE " + tablename + ";");
            }
            catch(Exception ex){
                
            }
            dm.executeUpdate("CREATE TABLE " + tablename + "("
                    + "name TEXT PRIMARY KEY, score INT, tournamentClass TEXT);");
            for (Participant p : participants) {
                dm.executeUpdate("INSERT INTO " + tablename + "(name,score,tournamentClass)"
                        + " VALUES('" + p.getName() + "'," + p.getScore() + ",'"
                        + p.getTournamentclass().getClassName() + "');");
            }
        }
        else System.out.println("alarm");
    }
    
    /**
     * Saves the participants of a tournament to a file at a specified directory.
     * @throws IOException IOException
     */
    public void saveToFile() throws IOException{
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File directory = chooser.getSelectedFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(directory+
                    "/"+tournament.getName()+"_"+
                    tournament.getDate().format(DateTimeFormatter.ofPattern("ddMMyyyy"))+
                    ".csv")));
            bw.write(tournament.getName()+","+
                    tournament.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+
                    "\n");
            for (Participant p : participants) {
                bw.write(p.getName()+","+p.getTournamentclass()+","+p.getScore()+"\n");
            }
            bw.close();
        }
    }
}
