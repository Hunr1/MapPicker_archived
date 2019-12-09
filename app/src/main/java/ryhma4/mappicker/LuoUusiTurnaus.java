package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

public class LuoUusiTurnaus extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnCancel;
    private Button btnGenerate;
    private int selectedItem = -1;
    TournamentEngine tournamentEngine;
    Button btnAddTeam;
    Button deleteTeam;
    ListView lvTeams;
    EditText etGetTeam;
    EditText etTournamentName;
    Spinner gameName;
    Spinner gameFormat;
    String selectedameName;
    ArrayAdapter<String> adapter;
    ArrayList<String> listTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luo_uusi_turnaus);
        tournamentEngine = TournamentApplication.getEngine();
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnAddTeam = findViewById(R.id.btnAddTeam);
        btnAddTeam.setOnClickListener(this);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnGenerate.setOnClickListener(this);
        deleteTeam = findViewById(R.id.btnDeleteTeam);
        deleteTeam.setOnClickListener(this);
        gameName = findViewById(R.id.spinnerGame);
        gameFormat = findViewById(R.id.spinnerFormat);
        etTournamentName = findViewById(R.id.etTournamentName);
        lvTeams = findViewById(R.id.lvTeams);
        etGetTeam = findViewById(R.id.editTextTeams);
        listTeams = new ArrayList<>();


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTeams);
        lvTeams.setAdapter(adapter);

        lvTeams.setOnItemClickListener(this);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnCancel:
               //"Tuhoaa" luo uusi turnaus activityn ja näkyviin tulee MainActivity
                Intent intent = new Intent(LuoUusiTurnaus.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.btnAddTeam:

                if (etGetTeam.getText().length() > 0) {
                    listTeams.add(etGetTeam.getText().toString());
                    etGetTeam.setText("");
                    adapter.notifyDataSetChanged();
                }
                break;

            case R.id.btnDeleteTeam:

                 if (selectedItem != -1) {
                     listTeams.remove(selectedItem);
                     adapter.notifyDataSetChanged();
                     lvTeams.setSelector(R.color.colorTransparent);

                     selectedItem = -1;
                 }
                break;

            case R.id.btnGenerate:

                if(!etTournamentName.getText().toString().isEmpty() && !listTeams.isEmpty()) {
                    try {
                        String teams = new String();
                        Iterator it = listTeams.listIterator();

                        while (it.hasNext()) {
                            String s = (String) it.next();
                            if (it.hasNext()) {
                                teams += s + "/";
                            } else {
                                teams += s;
                            }
                        }

                        String url = getString(R.string.addTournamentAPI, etTournamentName.getText().toString(), String.valueOf(gameFormat.getSelectedItem()), teams, String.valueOf(gameName.getSelectedItem()));
                        Log.d("AddingTournamentURL: ", url);

                        RequestQueue queue = Volley.newRequestQueue(this);
                        url = url.replaceAll(" ", "%20");

                        JsonObjectRequest addTournamentRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    Log.d("response: ", response.getString("idtournament"));
                                    String result = response.getString("idtournament");

                                    if (!result.isEmpty()) {

                                        Toast.makeText(getApplicationContext(), "Tournament added succesfully!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LuoUusiTurnaus.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
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

                    } catch (Exception e) {
                        Log.d("NEW_TOUR_ERROR: ", e.toString());
                        finish();
                    }
                }
                    break;
                }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        selectedItem = position;
        lvTeams.setSelector(R.color.colorPrimaryDark);

    }
}