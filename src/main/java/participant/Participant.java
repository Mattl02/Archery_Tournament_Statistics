
package participant;

import tournamentclass.TournamentClass;

/**
 *
 * @author Matthias
 */
public class Participant {
    private String name;
    private int score;
    private TournamentClass tournamentclass;

    public Participant(String name, TournamentClass c) {
        this.name = name;
        this.score = 0;
        this.tournamentclass = c;
    }
    
    public void addScore(int points){
        this.score += points;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public TournamentClass getTournamentclass() {
        return tournamentclass;
    }

    public void setTournamentclass(TournamentClass tournamentclass) {
        this.tournamentclass = tournamentclass;
    }

    @Override
    public String toString() {
        return String.format("%s(%s): %d", name, tournamentclass, score);
    }
}
