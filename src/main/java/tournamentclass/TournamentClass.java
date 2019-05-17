
package tournamentclass;

/**
 *
 * @author Matthias
 */
public class TournamentClass {
    private String className;

    public TournamentClass(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return this.className;
    }
    
    
}
