
package tournament;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Matthias
 */
public class TournamentListModel extends AbstractListModel{

    private ArrayList<Tournament> tournaments = new ArrayList<>();
    
    
    public void add (Tournament t){
        tournaments.add(t);
        this.fireIntervalAdded(this, tournaments.size()-1, tournaments.size()-1);
    }
    
    public void remove(int idx) {
        tournaments.remove(idx);
        this.fireContentsChanged(this, 0, tournaments.size()-1);
    }
    
    public void remove(int[] idx) {
        for(int i = idx.length-1; i >= 0; i--){
            tournaments.remove(idx[i]);
        }
        this.fireContentsChanged(this, 0, tournaments.size()-1);
    }
    
    @Override
    public int getSize() {
        return tournaments.size();
    }

    @Override
    public Object getElementAt(int i) {
        return tournaments.get(i);
    }
    
}
