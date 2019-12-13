package ryhma4.mappicker;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Serializable;
import java.util.ArrayList;

public class Match extends Game implements Parcelable {


    private Integer matchID;
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
    private String team_A_map4_score;
    private String team_B_map4_score;
    private String team_A_map5_score;
    private String team_B_map5_score;
    private String team_A_map6_score;
    private String team_B_map6_score;
    private String team_A_map7_score;
    private String team_B_map7_score;


    public Match() {
        teamAMapScore = "0";
        teamBMapScore = "0";
        team_A_map1_score = "0";
        team_B_map1_score = "0";
        team_A_map2_score = "0";
        team_B_map2_score ="0";
        team_A_map3_score ="0";
        team_B_map3_score ="0";
        team_A_map4_score ="0";
        team_B_map4_score ="0";
        team_A_map5_score ="0";
        team_B_map5_score ="0";
        team_A_map6_score ="0";
        team_B_map6_score ="0";
        team_A_map7_score ="0";
        team_B_map7_score ="0";
    }

    public String getTeam_A_map4_score() {
        return team_A_map4_score;
    }

    public void setTeam_A_map4_score(String team_A_map4_score) {
        this.team_A_map4_score = team_A_map4_score;
    }

    public String getTeam_B_map4_score() {
        return team_B_map4_score;
    }

    public void setTeam_B_map4_score(String team_B_map4_score) {
        this.team_B_map4_score = team_B_map4_score;
    }

    public String getTeam_A_map5_score() {
        return team_A_map5_score;
    }

    public void setTeam_A_map5_score(String team_A_map5_score) {
        this.team_A_map5_score = team_A_map5_score;
    }

    public String getTeam_B_map5_score() {
        return team_B_map5_score;
    }

    public void setTeam_B_map5_score(String team_B_map5_score) {
        this.team_B_map5_score = team_B_map5_score;
    }

    public String getTeam_A_map6_score() {
        return team_A_map6_score;
    }

    public void setTeam_A_map6_score(String team_A_map6_score) {
        this.team_A_map6_score = team_A_map6_score;
    }

    public String getTeam_B_map6_score() {
        return team_B_map6_score;
    }

    public void setTeam_B_map6_score(String team_B_map6_score) {
        this.team_B_map6_score = team_B_map6_score;
    }

    public String getTeam_A_map7_score() {
        return team_A_map7_score;
    }

    public void setTeam_A_map7_score(String team_A_map7_score) {
        this.team_A_map7_score = team_A_map7_score;
    }

    public String getTeam_B_map7_score() {
        return team_B_map7_score;
    }

    public void setTeam_B_map7_score(String team_B_map7_score) {
        this.team_B_map7_score = team_B_map7_score;
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

    public ArrayList<String> getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(ArrayList<String> mapInfo) {
        this.mapInfo = mapInfo;
    }

    public void addToMapInfo(String mapInfo) {
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

    public void updateScore(final Context c, int mapNumber, String tournID) {

       // Tournament tournament = TournamentApplication.getEngine().tournamentByID(tournID);
       // String tid = tournament.getTournamentID();
        String url;
        String map_scoreA = this.getTeam_A_map1_score();
        String map_scoreB = this.getTeam_B_map1_score();

        int matchID = this.getMatchID();

        switch (mapNumber) {

            case 1:

                url = c.getString(R.string.insertPickXScores, mapNumber, matchID, tournID, mapNumber, map_scoreA, mapNumber, map_scoreB);
                url = Uri.encode(url, "@#&=*+-_.,:!?()/~'%");

                break;

            case 2:

                map_scoreA = this.getTeam_A_map2_score();
                map_scoreB = this.getTeam_B_map2_score();

                url = c.getString(R.string.insertPickXScores, mapNumber, matchID, tournID, mapNumber, map_scoreA, mapNumber, map_scoreB);
                url = Uri.encode(url, "@#&=*+-_.,:!?()/~'%");
                break;

            case 3:

                map_scoreA = this.getTeam_A_map3_score();
                map_scoreB = this.getTeam_B_map3_score();

                url = c.getString(R.string.insertPickXScores, mapNumber, matchID, tournID, mapNumber, map_scoreA, mapNumber, map_scoreB);
                url = Uri.encode(url, "@#&=*+-_.,:!?()/~'%");
                break;

            default:
                url = c.getString(R.string.insertPickXScores, mapNumber, matchID, tournID, mapNumber, map_scoreA, mapNumber, map_scoreB);
                url = Uri.encode(url, "@#&=*+-_.,:!?()/~'%");
                break;

        }

        RequestQueue queue = Volley.newRequestQueue(c);
        Log.d("HTCAURL", url );

        StringRequest insertScore = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Stringrequest", response);
                Toast.makeText(c, "Score saved", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError", error.toString());
            }
        });

        queue.add(insertScore);


    }
    public void updateMapScore (final Context c, String tournID){

        String map_scoreA = this.getTeamAMapScore();
        String map_scoreB = this.getTeamBMapScore();
        int matchID = this.getMatchID();
        String url = c.getString(R.string.insertMapScores, matchID, tournID, map_scoreA, map_scoreB);

        url = Uri.encode(url, "@#&=*+-_.,:!?()/~'%");

        RequestQueue queue = Volley.newRequestQueue(c);
        Log.d("HTCAURL", url );

        StringRequest insertScore = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Stringrequest", response);
                Toast.makeText(c, "Score saved", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError", error.toString());
            }
        });

        queue.add(insertScore);

    }


    protected Match(Parcel in) {
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
