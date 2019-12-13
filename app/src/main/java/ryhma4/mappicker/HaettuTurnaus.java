package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class HaettuTurnaus extends AppCompatActivity implements View.OnClickListener {

    TextView tournamentId;
    private int tourID;
    private String game;


    ListView haettuTurnausListView;

    ArrayList<Match> matches;

    HaettuTurnausCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haettu_turnaus);
        haettuTurnausListView = findViewById(R.id.haettu_turnaus_listView);
        matches = SavedMatches.getInstance().getSavedMatches();
        Log.d("Applikaatio","On create");
        tourID = getIntent().getIntExtra("TOURNAMENT_ID", -1);
        game = getIntent().getStringExtra("GAME");
        adapter = new HaettuTurnausCustomAdapter(this, R.layout.match_list_csgo, matches, tourID);
        haettuTurnausListView.setAdapter(adapter);

        if (tourID >= 0) {
            Tournament tournament = TournamentApplication.getEngine().tournamentByID(tourID);
            tournamentId = findViewById(R.id.tournamentID);
            tournamentId.setText(getString(R.string.IDText,tournament.getTournamentID()));
            makeApiCall(tourID);
        }

        //TODO API kutsu getMatchesByTournamentID  turnausID:n kanssa. Jos palauttaa tyhjän arrayn ei tehdä mittään,
        //jos otteluita löytyy tehdään uusia matches olioja palautetulla datalla ja tallennetaan matches arraylistiin



        findViewById(R.id.ManageTournamentBtn).setOnClickListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("Applikaatio","On resume");
        //Vastaanottaa uuden luodun Match olion ja lisää sen matches arraylistaan


        //hakee aikaisemmin tallennetut ottelut
        matches = SavedMatches.getInstance().getSavedMatches();

        //hakee lisaaOttelu luokassa tehdyn ottelun
        Intent i = getIntent();
        Match haettuOttelu = i.getParcelableExtra("newMatch");

        if(haettuOttelu!=null) {
            Log.d("Applikaatio", "trying to add new match");
            //lisää ottelun matches listaan
            matches.add(haettuOttelu);
        }
        Collections.reverse(matches);

        adapter.notifyDataSetChanged();
        Log.d("Applikaatio","Matches = " + matches.toString());
        Log.d("Applikaatio", "Otteluita on nyt lisättynä " + matches.size());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ManageTournamentBtn:
                Intent intent = new Intent(HaettuTurnaus.this, LisaaOttelu.class);
                intent.putExtra("TOURNAMENT_ID", tourID );
                intent.putExtra("GAME",game);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList("SavedMatches",matches);

        if(!matches.toString().equals("[]"))
        {
            Log.d("Applikaatio", "Tallennetaan ottelut " + matches.toString());
            SavedMatches.getInstance().setSavedMatches(matches);
            super.onSaveInstanceState(savedInstanceState);
        }
    }

    public void makeApiCall(int tourID)
    {
        String url = getString(R.string.getMatchesAPI,TournamentApplication.getEngine().tournamentByID(tourID).getTournamentID());
        Log.d("AddingTournamentURL: ", url);

        RequestQueue queue = Volley.newRequestQueue(this);
        url = url.replaceAll(" ", "%20");

        JsonObjectRequest addTournamentRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.d("response: ", response.getString("Matches"));
                    JSONArray result = response.getJSONArray("Matches");
                    Match match;
                    if(result.length() != 0) {
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject matchObject = result.getJSONObject(i);
                            match = new Match();

                            match.setTeamAname(matchObject.getString("teamAname"));
                            match.setTeamBname(matchObject.getString("teamBname"));
                            match.setGameFormat(matchObject.getString("format"));
                            match.setMatchID(matchObject.getInt("idmatch"));

                            List<String> pickedMaps = Arrays.asList(matchObject.getString("matchInfo").split("\\|"));

                            for (String s: pickedMaps
                                 ) {

                                Log.d("PICKEDMAPS: ", s);
                                match.addToMapInfo(s);
                            }

                            matches.add(match);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    else{
                        adapter.clear();
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSONRequesterror: ", error.toString());
                // TODO: Handle error
                finish();
            }
        });

        queue.add(addTournamentRequest);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            adapter.clear();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}


