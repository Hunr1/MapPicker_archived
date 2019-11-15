package ryhma4.mappicker;

import java.util.ArrayList;

public class Match extends Game {

    private ArrayList<String> teams = new ArrayList<>();
    private String gameFormat;

    public String getGameFormat() {
        return gameFormat;
    }

    public void setGameFormat(String format) {
        this.gameFormat = format;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeam(String team) {
        this.teams.add(team);
    }
}
