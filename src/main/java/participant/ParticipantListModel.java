package participant;

import database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import tournament.Tournament;
import tournamentclass.TournamentClass;

/**
 *
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
    
    public void add (Participant p){
        participants.add(p);
        this.fireIntervalAdded(this, participants.size()-1, participants.size()-1);
    }
    
    public void add (int idx, Participant p){
        participants.add(idx, p);
        this.fireIntervalAdded(this, idx, participants.size()-1);
    }
    
    public void remove(int idx) {
        participants.remove(idx);
        this.fireContentsChanged(this, 0, participants.size()-1);
    }
    
    public void remove(int[] idx) {
        for(int i = idx.length-1; i >= 0; i--){
            participants.remove(idx[i]);
        }
        this.fireContentsChanged(this, 0, participants.size()-1);
    }
    
    public void setClasses(int[] indices, TournamentClass c){
        for(int i = indices.length-1; i >= 0; i--) {
            participants.get(indices[i]).setTournamentclass(c);
        }
        this.fireContentsChanged(this, 0, participants.size()-1);
    }
    
    public void addPointsTo(int points, int idx){
        participants.get(idx).addScore(points);
        
    }
    
    @Override
    public int getSize() {
        return participants.size();
    }

    @Override
    public Object getElementAt(int i) {
        return participants.get(i);
    }
    
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
}
