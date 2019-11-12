package ryhma4.mappicker;

public class Tournament extends Match {

    private String tournamentID;
    private String tournamentName;
    private String mapPoolID;

    public String getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(String tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getMapPoolID() {
        return mapPoolID;
    }

    public void setMapPoolID(String mapPoolID) {
        this.mapPoolID = mapPoolID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
