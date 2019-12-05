package ryhma4.mappicker;

import java.util.ArrayList;

public class Tournament extends Match {

    private String tournamentID;
    private String tournamentName;
    private Integer tournamentFormat;
    private ArrayList<String> teams = new ArrayList<>();

    public Integer getTournamentFormat() {
        return tournamentFormat;
    }

    public void setTournamentFormat(Integer tournamentFormat) {
        this.tournamentFormat = tournamentFormat;
    }


    public String getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(String tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeam(String team) {
        this.teams.add(team);
    }

}
