
package tournamentclass;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Matthias
 */
public class TournamentClassListModel extends AbstractListModel<TournamentClass>{

    private ArrayList<TournamentClass> classes = new ArrayList<>();
    
    public void add (TournamentClass c) {
        classes.add(c);
        this.fireIntervalAdded(this, classes.size()-1, classes.size()-1);
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
