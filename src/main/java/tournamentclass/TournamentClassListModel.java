
package tournamentclass;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Matthias
 */
public class TournamentClassListModel extends AbstractListModel{

    private ArrayList<TournamentClass> classes = new ArrayList<>();
    
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

}
