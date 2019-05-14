
package tournament;

import bl.Participant;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Matthias
 */
public class Tournament {
    private String name;
    private ArrayList<Participant> participants;
    private LocalDateTime start;
    private LocalDateTime end;

    public Tournament(String name, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
    
    
}
