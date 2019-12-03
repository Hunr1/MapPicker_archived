package ryhma4.mappicker;

public class Tournament extends Match {

    private String tournamentID;
    private String tournamentName;
    private Integer tournamentFormat;


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


}
