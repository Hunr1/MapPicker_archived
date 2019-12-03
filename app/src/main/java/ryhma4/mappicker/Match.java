package ryhma4.mappicker;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Match extends Game implements Serializable,Parcelable {


    private Integer matchID;
    private ArrayList<String> teams = new ArrayList<>();
    private String gameFormat;
    private ArrayList<String> mapInfo = new ArrayList<>();
    private String teamAname;
    private String teamBname;
    private String teamAMapScore;
    private String teamBMapScore;

    private String team_A_map1_score;
    private String team_B_map1_score;
    private String team_A_map2_score;
    private String team_B_map2_score;
    private String team_A_map3_score;
    private String team_B_map3_score;


    public Match() {

        teamAMapScore = "0";
        teamBMapScore = "0";
        team_A_map1_score = "0";
        team_B_map1_score = "0";
        team_A_map2_score = "0";
        team_B_map2_score ="0";
        team_A_map3_score ="0";
        team_B_map3_score ="0";
    }


    public Integer getMatchID() {
        return matchID;
    }

    public void setMatchID(Integer matchID) {
        this.matchID = matchID;
    }

    public String getTeam_A_map1_score() {
        return team_A_map1_score;
    }

    public void setTeam_A_map1_score(String team_A_map1_score) {
        this.team_A_map1_score = team_A_map1_score;
    }

    public String getTeam_B_map1_score() {
        return team_B_map1_score;
    }

    public void setTeam_B_map1_score(String team_B_map1_score) {
        this.team_B_map1_score = team_B_map1_score;
    }

    public String getTeam_A_map2_score() {
        return team_A_map2_score;
    }

    public void setTeam_A_map2_score(String team_A_map2_score) {
        this.team_A_map2_score = team_A_map2_score;
    }

    public String getTeam_B_map2_score() {
        return team_B_map2_score;
    }

    public void setTeam_B_map2_score(String team_B_map2_score) {
        this.team_B_map2_score = team_B_map2_score;
    }

    public String getTeam_A_map3_score() {
        return team_A_map3_score;
    }

    public void setTeam_A_map3_score(String team_A_map3_score) {
        this.team_A_map3_score = team_A_map3_score;
    }

    public String getTeam_B_map3_score() {
        return team_B_map3_score;
    }

    public void setTeam_B_map3_score(String team_B_map3_score) {
        this.team_B_map3_score = team_B_map3_score;
    }

    public String getTeamBMapScore() {
        return teamBMapScore;
    }

    public void setTeamBMapScore(String teamBMapScore) {
        this.teamBMapScore = teamBMapScore;
    }

    public String getTeamAMapScore() {
        return teamAMapScore;
    }

    public void setTeamAMapScore(String teamAMapScore) {
        this.teamAMapScore = teamAMapScore;
    }

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

    public ArrayList<String> getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(ArrayList<String> mapInfo) {
        this.mapInfo = mapInfo;
    }

    public void addToMapINfo(String mapInfo) {
        this.mapInfo.add(mapInfo);
    }

    public String getTeamAname() {
        return teamAname;
    }

    public void setTeamAname(String teamAname) {
        this.teamAname = teamAname;
    }

    public String getTeamBname() {
        return teamBname;
    }

    public void setTeamBname(String teamBname) {
        this.teamBname = teamBname;
    }


    protected Match(Parcel in) {
        teams = in.createStringArrayList();
        gameFormat = in.readString();
        mapInfo = in.createStringArrayList();
        teamAname = in.readString();
        teamBname = in.readString();
        teamAMapScore = in.readString();
        teamBMapScore = in.readString();
        team_A_map1_score = in.readString();
        team_B_map1_score = in.readString();
        team_A_map2_score = in.readString();
        team_B_map2_score = in.readString();
        team_A_map3_score = in.readString();
        team_B_map3_score = in.readString();
    }


    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(teams);
        parcel.writeString(gameFormat);
        parcel.writeStringList(mapInfo);
        parcel.writeString(teamAname);
        parcel.writeString(teamBname);
        parcel.writeString(teamAMapScore);
        parcel.writeString(teamBMapScore);
        parcel.writeString(team_A_map1_score);
        parcel.writeString(team_B_map1_score);
        parcel.writeString(team_A_map2_score);
        parcel.writeString(team_B_map2_score);
        parcel.writeString(team_A_map3_score);
        parcel.writeString(team_B_map3_score);
    }
}
