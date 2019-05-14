
package tournament;

import bl.Participant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String getName() {
        return name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
    
    

    @Override
    public String toString() {
        return String.format("%s: %s", name, start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    }
}
