
package tournament;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Matthias
 */
public class TournamentListModel extends AbstractListModel{

    private ArrayList<Tournament> tournaments = new ArrayList<>();
    
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
    
}
