package participant;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Matthias
 */
public class ParticipantListModel extends AbstractListModel{

    private ArrayList<Participant> participants = new ArrayList<>();
    
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
    
    @Override
    public int getSize() {
        return participants.size();
    }

    @Override
    public Object getElementAt(int i) {
        return participants.get(i);
    }
    
}