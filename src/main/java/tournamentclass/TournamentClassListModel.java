
package tournamentclass;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Matthias
 */
public class TournamentClassListModel extends AbstractListModel{

    private ArrayList<TournamentClass> classes = new ArrayList<>();
    
    public void add (TournamentClass c) {
        classes.add(c);
        this.fireIntervalAdded(this, classes.size()-1, classes.size()-1);
    }
    
    public void remove(int idx) {
        classes.remove(idx);
        this.fireContentsChanged(this, 0, classes.size()-1);
    }
    
    public void remove(int[] idx) {
        for(int i = idx.length-1; i >= 0; i--){
            classes.remove(idx[i]);
        }
        this.fireContentsChanged(this, 0, classes.size()-1);
    }
    
    @Override
    public int getSize() {
        return classes.size();
    }

    @Override
    public TournamentClass getElementAt(int i) {
        return classes.get(i);
    }

}
