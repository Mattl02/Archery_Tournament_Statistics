
package tournament;

import bl.Participant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Matthias
 */
public class Tournament {
    private String name;
    private ArrayList<Participant> participants;
    private LocalDate date;

    public Tournament(String name, LocalDate start) {
        this.name = name;
        this.date = start;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
