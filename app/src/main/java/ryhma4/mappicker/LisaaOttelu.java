package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LisaaOttelu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView mapList;
    Spinner teamAdropdown;
    Spinner teamBdropdown;
    Spinner bestOfDropdown;
    TextView pickingTeam;
    TextView action;
    TextView startingSide;
    TextView pickedMapName;
    TextView teamATextView;
    TextView teamBTextView;
    FrameLayout startingSideFrame;
    LinearLayout bestOfLayout;
    LinearLayout chooseTeamsLayout;
    Button readyButton;
    Button allReadyBtn;
    Button backBtn;
    Button CTChoose;
    Button TerroristChoose;
    TournamentEngine engine;

    int bestOf = 0;
    int clickCounter = 0;
    int tourID;

    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    String teamAname;
    String teamBname;
    String game = "CS:GO";
    String playedMapSide;
    String matchInfoString = "";
    String tournamentID;

    List<String> pickedMaps;
    ArrayList<Map> csgoMaplist;
    MapListAdapter adapter;

    String[] banActions;
    String[] pickActions;

    Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisaa_ottelu);

        tourID = getIntent().getIntExtra("TOURNAMENT_ID", -1);

        teamAdropdown = findViewById(R.id.teamADropdown);
        teamBdropdown = findViewById(R.id.teamBDropdown);
        chooseTeamsLayout=findViewById(R.id.addMatch_teams_layout);
        bestOfLayout=findViewById(R.id.addMatch_bestOf_layout);
        readyButton = findViewById(R.id.readyButton);
        teamATextView= findViewById(R.id.teamA);
        teamBTextView= findViewById(R.id.teamB);
        allReadyBtn = findViewById(R.id.allReady);
        backBtn = findViewById(R.id.backBtn);
        pickingTeam = findViewById(R.id.CurrentTeam);
        action = findViewById(R.id.Action);


        //Haetaan strings.xml tiedostosta karttojen nimet ja kuvatiedostojen nimet
        List<String> mapNames = Arrays.asList(getResources().getStringArray(R.array.mapNames));
        List<String> mapImages = Arrays.asList(getResources().getStringArray(R.array.mapImages));
        banActions = getResources().getStringArray(R.array.BanActions);
        pickActions = getResources().getStringArray(R.array.PickActions);


        mapList = findViewById(R.id.maps);


        //muilla if statementeillä voidaan lisätä muiden pelien karttoja
        if (game.equals("CS:GO"))
        {
            //Tehdään arraylist Map olioista
            csgoMaplist = new ArrayList<>();
            //Luodaan karttaoliot haetuilla karttojen kuvilla ja nimillä
            for (int i = 0; i < mapImages.size(); i++) {
                String mapImage = mapImages.get(i);
                int mapImgID = getResources().getIdentifier(mapImage, "drawable", getPackageName());


                //lisätään olio csgoMaplist arraylistiin
                csgoMaplist.add(new Map(mapNames.get(i), mapImgID));
            }

        }
        //luodaan custom adapteri listvieviä varten
        adapter = new MapListAdapter(this,R.layout.add_match_listview,csgoMaplist);
        mapList.setOnItemClickListener(this);
        mapList.setAdapter(adapter);
    }


    public void swapButton(View v){

        int tempA = teamAdropdown.getSelectedItemPosition();
        int tempB = teamBdropdown.getSelectedItemPosition();

        teamAdropdown.setSelection(tempB);
        teamBdropdown.setSelection(tempA);

        Log.d("applikaatio","Vaihdettiin tiimien puolia");
    }


    public void readyButton (View v){

        if (!teamBdropdown.getSelectedItem().toString().equals(teamAdropdown.getSelectedItem().toString())){


            teamAname = teamAdropdown.getSelectedItem().toString();
            teamBname = teamBdropdown.getSelectedItem().toString();


            teamATextView.setText(teamAname);
            teamBTextView.setText(teamBname);

            chooseTeamsLayout.setVisibility(View.GONE);
            bestOfLayout.setVisibility(View.GONE);
            readyButton.setVisibility(View.GONE);

            allReadyBtn.setVisibility(View.VISIBLE);
            backBtn.setVisibility(View.VISIBLE);
            mapList.setVisibility(View.VISIBLE);

            pickedMaps = new ArrayList<>();

            pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
            action.setText(banActions[1]);

            Log.d("applikaatio","Painettiin valmiina nappia");
        }
        else
        {
            Log.d("applikaatio","Tiimit olivat samat");
            Toast.makeText(getApplicationContext(),"Teams are same",Toast.LENGTH_SHORT).show();
        }
    }


    public void backButton (View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        Log.d("applikaatio","Painettiin takaisin nappia");
        tourID = getIntent().getIntExtra("TOURNAMENT_ID", -1);
    }

    //tähän tietokantaan tallentelut sun muut
    public void allReadyButton (View v){
        Log.d("applikaatio","Lähetetään dataa eteenpäin ja palataan takaisin turnaushallintaan->>");
        match = new Match();
        match.setTeamAname(teamAname);
        match.setTeamBname(teamBname);
        match.setGameFormat(bestOfDropdown.getSelectedItem().toString());

        for(int i = 0; i< pickedMaps.size();i++){
            matchInfoString = matchInfoString  + pickedMaps.get(i) + "|";
            match.addToMapInfo(pickedMaps.get(i));
        }

        matchInfoString = matchInfoString.substring(0, matchInfoString.length() - 1);
        //TODO tähän väliin uuden ottelun tallentaminen (TournamentID, TeamA ja Team B nimet, Formaatti,MatchInfo(matchInfoString))
        //API palauttaa matchIDn joka tallennetaan luotuun match olioon

        Tournament tournament = TournamentApplication.getEngine().tournamentByID(tourID);
        tournamentID = tournament.getTournamentID();

        Log.d("matchInfoString", matchInfoString);
        String url = getString(R.string.addMatchAPI, tournamentID, teamAname, teamBname, Integer.valueOf(bestOfDropdown.getSelectedItem().toString()), matchInfoString);
        url = Uri.encode(url, ALLOWED_URI_CHARS);

        Log.d("APIURL", url);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest addMatchRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.d("addMatchResponse", response.toString());
                    // JSONObject result = response.getJSONObject("idmatch");
                    int matchID = response.getInt("idmatch");

                    match.setMatchID(matchID);
                    Log.d("MATCHID", match.getMatchID().toString());


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AddmatchError", error.toString());

            }
        });
        queue.add(addMatchRequest);

        //match.setMatchID(BLAA);

        //Näiden lisäksi tietokantaan tulee karttavoitot ja karttojen tulokset, joita muutetaan HaettuTurnausCustomAdapter luokassa save scores onclick funktiossa

        //palauttaa luodun uuden Match olion
        Intent i = new Intent(LisaaOttelu.this, HaettuTurnaus.class);
        i.putExtra("newMatch", (Serializable)match);
        i.putExtra("TOURNAMENT_ID", tourID);
        startActivity(i);
    }


    public void onItemClick(AdapterView parent, View v, final int position, long id) {

        startingSideFrame = v.findViewById(R.id.startingSideFrame);
        startingSide = v.findViewById(R.id.startingSide);
        pickedMapName = v.findViewById(R.id.add_match_listview_textEdit);
        bestOfDropdown = findViewById(R.id.bestOfDropdown);
        CTChoose = v.findViewById(R.id.CT);
        TerroristChoose = v.findViewById(R.id.Terrorist);

        bestOf = Integer.valueOf(bestOfDropdown.getSelectedItem().toString());
        parent.refreshDrawableState();

        //Peli / bestof / varmistetaan että tiimit eivät ole samat
        //##############################################################################################################################    Best of 3   #######################################
        if (game.equals("CS:GO")
                && bestOf == 3
                && !teamBdropdown.getSelectedItem().toString().equals(teamAdropdown.getSelectedItem().toString())) {

            switch (clickCounter) {
                case 0:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(banActions[2]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 1/"+pickedMapName.getText().toString()+ "/" +teamBdropdown.getSelectedItem().toString());
                    break;

                case 1:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText(pickActions[1]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 2/"+pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString());
                    break;

                case 2:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(pickActions[2]);
                    startingSideFrame.setVisibility(View.VISIBLE);
                    adapter.setPickSide(true);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            playedMapSide ="CT/" + teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }


                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            startingSideFrame.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();


                            playedMapSide = "Terrorist/"+teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });
                    startingSide.setText(teamAdropdown.getSelectedItem().toString());
                    break;

                case 3:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText(banActions[3]);
                    startingSideFrame.setVisibility(View.VISIBLE);
                    adapter.setPickSide(true);

                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            playedMapSide = "CT/"+teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 2/"+pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            playedMapSide = "Terrorist/"+teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 2/"+ pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });
                    startingSide.setText(teamBdropdown.getSelectedItem().toString());
                    break;

                case 4:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(banActions[4]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 3/"+pickedMapName.getText().toString()+ "/" +teamBdropdown.getSelectedItem().toString());
                    break;

                case 5:
                    pickingTeam.setText(getResources().getString(R.string.FinalPick));
                    action.setText("");
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 4/"+pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString());
                    break;

                case 6:

                    startingSide.setText(getResources().getString(R.string.Knife));
                    pickingTeam.setText(getResources().getString(R.string.PicksDone));
                    action.setText("");
                    csgoMaplist.remove(position);
                    pickedMaps.add("Tiebreaker/"+pickedMapName.getText().toString());
                    break;
            }

            clickCounter++;
        //##############################################################################################################################    Best of 5   #######################################
        } else if (game.equals("CS:GO")
                && bestOf == 5
                && !teamBdropdown.getSelectedItem().toString().equals(teamAdropdown.getSelectedItem().toString())) {

            switch (clickCounter) {
                case 0:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(banActions[2]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 1/"+pickedMapName.getText().toString()+ "/" +teamBdropdown.getSelectedItem().toString());
                    break;

                case 1:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText(pickActions[1]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 2/"+pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString());
                    break;

                case 2:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(pickActions[2]);

                    startingSideFrame.setVisibility(View.VISIBLE);
                    adapter.setPickSide(true);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            playedMapSide = teamAdropdown.getSelectedItem().toString() + "/CT";
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            playedMapSide ="CT/" + teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            startingSideFrame.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();

                            playedMapSide = "Terrorist/"+teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });

                    startingSide.setText(teamAdropdown.getSelectedItem().toString());
                    break;

                case 3:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText(pickActions[3]);
                    startingSideFrame.setVisibility(View.VISIBLE);
                    adapter.setPickSide(true);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            playedMapSide ="CT/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 2/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            playedMapSide ="Terrorist/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 2/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });

                    startingSide.setText(teamBdropdown.getSelectedItem().toString());
                    break;

                case 4:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(pickActions[4]);

                    startingSideFrame.setVisibility(View.VISIBLE);
                    adapter.setPickSide(true);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);

                            playedMapSide ="CT/" + teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 3/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }


                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            startingSideFrame.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();


                            playedMapSide ="Terrorist/" + teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 3/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });

                    startingSide.setText(teamAdropdown.getSelectedItem().toString());
                    break;

                case 5:
                    pickingTeam.setText(getResources().getString(R.string.FinalPick));
                    action.setText("");
                    startingSideFrame.setVisibility(View.VISIBLE);
                    adapter.setPickSide(true);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            playedMapSide ="CT/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 4/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            playedMapSide ="Terrorist/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 4/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });
                    startingSide.setText(teamBdropdown.getSelectedItem().toString());
                    break;

                case 6:
                            startingSide.setText(getResources().getString(R.string.Knife));
                            pickingTeam.setText(getResources().getString(R.string.PicksDone));
                            action.setText("");
                            csgoMaplist.remove(position);
                            pickedMaps.add("Tiebreaker/"+pickedMapName.getText().toString());
                    break;

            }
            clickCounter++;
        }
        //##############################################################################################################################    Best of 1   #######################################
        else if (game.equals("CS:GO")
                && bestOf == 1
                && !teamBdropdown.getSelectedItem().toString().equals(teamAdropdown.getSelectedItem().toString())){

            switch (clickCounter) {
                case 0:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(banActions[2]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 1/" + pickedMapName.getText().toString() + "/" + teamBdropdown.getSelectedItem().toString());
                    break;

                case 1:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText(banActions[3]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 2/" + pickedMapName.getText().toString() + "/" + teamAdropdown.getSelectedItem().toString());
                    break;

                case 2:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(banActions[4]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 3/" + pickedMapName.getText().toString() + "/" + teamBdropdown.getSelectedItem().toString());
                    break;

                case 3:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText(banActions[5]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 4/" + pickedMapName.getText().toString() + "/" + teamAdropdown.getSelectedItem().toString());
                    break;

                case 4:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText(banActions[6]);
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 5/" + pickedMapName.getText().toString() + "/" + teamBdropdown.getSelectedItem().toString());
                    break;

                case 5:
                    pickingTeam.setText(getResources().getString(R.string.FinalPick));
                    action.setText("");
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 6/" + pickedMapName.getText().toString() + "/" + teamAdropdown.getSelectedItem().toString());
                    break;

                case 6:
                    pickingTeam.setText(" ");
                    action.setText(getResources().getString(R.string.PicksDone));
                    startingSideFrame.setVisibility(View.VISIBLE);
                    adapter.setPickSide(true);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);

                            playedMapSide ="CT/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }


                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            csgoMaplist.remove(position);
                            startingSideFrame.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();


                            playedMapSide ="Terrorist/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ playedMapSide);
                            adapter.setPickSide(false);
                        }
                    });
                    startingSide.setText(teamBdropdown.getSelectedItem().toString());
                    break;
            }
            clickCounter++;
        }
    }
}

