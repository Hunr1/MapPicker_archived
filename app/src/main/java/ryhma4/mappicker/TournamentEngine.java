package ryhma4.mappicker;
import java.util.ArrayList;

public class TournamentEngine {

    private ArrayList<Tournament> allTournaments = new ArrayList<>();

    public void addTournament(Tournament tournament) {
        allTournaments.add(tournament);
    }

    public int countOfTournaments() {
        return allTournaments.size();
    }

    public Tournament tournamentByID(int index) {
        if (allTournaments.size() > index) {
            return allTournaments.get(index);
        }
        else {
            return null;
        }
    }

    public void deleteTournamentByID(int index) {
        if (allTournaments.size() > index) {
            allTournaments.remove(index);
        }
    }
}
