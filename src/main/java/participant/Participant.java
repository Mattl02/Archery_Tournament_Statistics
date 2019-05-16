
package participant;

/**
 *
 * @author Matthias
 */
public class Participant {
    private String name;
    private int score;

    public Participant(String name) {
        this.name = name;
        this.score = 0;
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
}
