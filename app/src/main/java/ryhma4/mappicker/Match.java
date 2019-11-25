package ryhma4.mappicker;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Match extends Game implements Serializable,Parcelable {

    private ArrayList<String> teams = new ArrayList<>();
    private String gameFormat;
    private ArrayList<String> mapInfo = new ArrayList<>();
    private String teamAname;
    private String teamBname;

    protected Match(Parcel in) {
        teams = in.createStringArrayList();
        gameFormat = in.readString();
        mapInfo = in.createStringArrayList();
        teamAname = in.readString();
        teamBname = in.readString();
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

    public Match() {

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
    }
}
