package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class HaettuTurnaus extends AppCompatActivity implements View.OnClickListener {

    TextView tournamentId;
    private int tourID;


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
        adapter = new HaettuTurnausCustomAdapter(this, R.layout.match_list_csgo, matches);
        haettuTurnausListView.setAdapter(adapter);
        if (tourID >= 0) {
            Tournament tournament = TournamentApplication.getEngine().tournamentByID(tourID);
            tournamentId = findViewById(R.id.tournamentID);
            tournamentId.setText(getString(R.string.IDText,tournament.getTournamentID()));
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
                startActivity(intent);
                finish();
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


}


