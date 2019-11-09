package ryhma4.mappicker;
import java.util.ArrayList;

public class TournamentEngine {

    public class ManagerEngine {

        private ArrayList<Tournament> allTournaments = new ArrayList<>();

        public void addTournament(Tournament person) {
            allTournaments.add(person);
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
    }
}
