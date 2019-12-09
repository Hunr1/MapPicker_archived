package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;



public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, DatabaseAsyncTask.OnSleepProgressUpdate{

    private DatabaseAsyncTask getFromDB = new DatabaseAsyncTask();
    TournamentEngine tournamentEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.newTournamentBtn).setOnClickListener(this);
        findViewById(R.id.searchBtn).setOnClickListener(this);
        ListView tournamentList = findViewById(R.id.TournamentsList);
        tournamentList.setOnItemClickListener(this);
        tournamentEngine = TournamentApplication.getEngine();
        getFromDB.setCallback(this);
        getFromDB.execute("");

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.newTournamentBtn:
                Intent intent = new Intent(this, LuoUusiTurnaus.class);
                startActivity(intent);
                break;

            case R.id.searchBtn:
                boolean found = false;
                EditText editTextSearch = findViewById(R.id.EditTextSearch);
                ListView tournamentList = findViewById(R.id.TournamentsList);
                TournamentEngine engine = TournamentApplication.getEngine();
                ArrayList<String> listaItemit = new ArrayList<>();

                try {

                    for (int i = 0; i < engine.countOfTournaments(); i++) {
                        Tournament tournament = engine.tournamentByID(i);
                        listaItemit.add(tournament.getTournamentID());
                        Log.d("id:t ",listaItemit.get(i));
                        Log.d("edit text ",editTextSearch.getText().toString());

                        if (Objects.equals(listaItemit.get(i), editTextSearch.getText().toString())) {
                            onItemClick(tournamentList, tournamentList, i, 1);
                            found = true;
                        }
                    }

                    if(!found)
                    {
                        Toast.makeText(getApplicationContext(),"No such tournament ID..",Toast.LENGTH_SHORT).show();
                    }
                }

                catch (Exception e)
                {
                    Log.d("SearchError ",e.toString());
                }
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
        Intent intent = new Intent(this, HaettuTurnaus.class);
        intent.putExtra("TOURNAMENT_ID", index);
        startActivity(intent);
    }

   /* public Tournament luoTestiturnaus()
    {
        Tournament testiTurnee = new Tournament();
        ArrayList<String> maps = new ArrayList<>();
        maps.add("Oulu");

        testiTurnee.setTeam("Team 1");
        testiTurnee.setTeam("Team 2");
        testiTurnee.setTournamentID("testi1");
        testiTurnee.setGameName("Counter Strike");
        testiTurnee.setGameFormat("Best of 3");
        testiTurnee.setMaps(maps);

        return testiTurnee;

    }*/

    private void paivitaLista() {
        ListView listaNakyma = findViewById(R.id.TournamentsList);
        ArrayList<String> listaItemit = new ArrayList<>();
        TournamentEngine engine = TournamentApplication.getEngine();

        for (int i = 0; i < engine.countOfTournaments(); i++) {

            Tournament tournament = engine.tournamentByID(i);
            listaItemit.add(tournament.getTournamentName() + " : " + tournament.getTournamentID());

        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listaItemit);

        listaNakyma.setAdapter(itemsAdapter);
    }

    @Override
    public void sleepDone() {
        try {

            JSONObject object = new JSONObject(getFromDB.getResult());
            JSONArray array = object.getJSONArray("Tournaments");
            checkTournamentsArray allTournamentsIDs = new checkTournamentsArray();
            allTournamentsIDs.addAll(tournamentEngine.getAllTournamentsIDs());


            for (int i = 0; i < array.length(); i++) {
                Tournament fromDB = new Tournament();
                JSONObject obj = array.optJSONObject(i);
                fromDB.setTournamentID(obj.getString("Tournament ID"));
                fromDB.setTournamentName(obj.getString("Tournament name"));
                fromDB.setGameFormat(obj.getString("Tournament format"));


                ArrayList<String> teams = new  ArrayList<>(Arrays.asList(obj.getString("Teams").split("/")));
                Log.d("TNAME: ", fromDB.getTournamentName());
                for (String team: teams
                     ) {
                    fromDB.addTeam(team);
                }
                Log.d("TEAMS: ", fromDB.getTeams().toString());

                //Ei lisätä jo olemassa olevaa turnausta tournamentEngineen
                if(!allTournamentsIDs.contains(fromDB.getTournamentID())){
                    tournamentEngine.addTournament(fromDB);
                }

            }

            Log.d("Tietokannasta: ", getFromDB.getResult());
            paivitaLista();

        }catch (Exception e)
        {
            Log.d("SLEEPDONE_ERROR", e.toString());
        }
    }


    // Tarkistus luokka turnausten id:tä varten
    //https://stackoverflow.com/questions/21724948/how-to-check-arraylist-contains-this-particular-word-in-android
    public class checkTournamentsArray extends ArrayList<String> {
        private static final long serialVersionUID = 2178228925760279677L;

        @Override
        public boolean contains(Object o) {
            return indexOf(o) >= 0;
        }

        @Override
        public int indexOf(Object o) {
            int size = this.size();
            if (o == null) {
                for (int i = 0; i < size ; i++) {
                    if (this.get(i) == null) {
                        return i;
                    }
                }
            } else {
                for (int i = 0; i < size ; i++) {
                    if (this.get(i).contains(String.valueOf(o))) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }
}
