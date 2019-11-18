package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LisaaOttelu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView mapList;
    Spinner teamAdropdown;
    TextView pickingTeam;
    TextView action;
    TextView startingSide;
    Spinner teamBdropdown;
    Spinner bestOfDropdown;
    FrameLayout startingSideFrame;
    LinearLayout bestOfLayout;
    LinearLayout tiimienValintaLayout;
    Button readyButton;
    TextView teamATextView;
    TextView teamBTextView;
    Button allReadyBtn;
    Button backBtn;
    TextView pickedMapName;
    Button CTChoose;
    Button TerroristChoose;

    int bestOf = 0;
    int clickCounter = 0;
    String teamAname;
    String teamBname;
    String game = "CS:GO";
    String firstPlayedMapSide;
    String secondPlayedMapSide;
    List<String> pickedMaps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisaa_ottelu);

        //Haetaan strings.xml tiedostosta karttojen nimet ja kuvatiedostojen nimet
        List<String> mapNames = Arrays.asList(getResources().getStringArray(R.array.mapNames));
        List<String> mapImages = Arrays.asList(getResources().getStringArray(R.array.mapImages));


        mapList = findViewById(R.id.maps);


        //Tehdään arraylist Map olioista
        ArrayList<Map> csgoMaplist = new ArrayList<>();

        //Luodaan karttaoliot haetuilla karttojen kuvilla ja nimillä
        for (int i = 0; i < mapImages.size(); i++) {
            String mapImage = mapImages.get(i);
            int mapImgID = getResources().getIdentifier(mapImage, "drawable", getPackageName());

            Log.d("applikaatio", "lisataan " + mapNames.get(i) + "    " + mapImgID);

            //lisätään olio csgoMaplist arraylistiin
            csgoMaplist.add(new Map(mapNames.get(i), mapImgID));
        }

        //luodaan custom adapteri listvieviä varten
        MapListAdapter adapter = new MapListAdapter(this,R.layout.add_match_listview,csgoMaplist);
        mapList.setOnItemClickListener(this);
        mapList.setAdapter(adapter);

    }


    public void swapButton(View v){
        teamAdropdown = findViewById(R.id.teamADropdown);
        teamBdropdown = findViewById(R.id.teamBDropdown);

        int tempA = teamAdropdown.getSelectedItemPosition();
        int tempB = teamBdropdown.getSelectedItemPosition();

        teamAdropdown.setSelection(tempB);
        teamBdropdown.setSelection(tempA);

        Log.d("applikaatio","Vaihdettiin tiimien puolia");
    }

    public void readyButton (View v){

        tiimienValintaLayout=findViewById(R.id.lisaa_ottelu_teams_layout);
        bestOfLayout=findViewById(R.id.lisaa_ottelu_bestOf_layout);
        readyButton = findViewById(R.id.readyButton);
        teamAdropdown = findViewById(R.id.teamADropdown);
        teamBdropdown = findViewById(R.id.teamBDropdown);
        teamATextView= findViewById(R.id.teamA);
        teamBTextView= findViewById(R.id.teamB);
        allReadyBtn = findViewById(R.id.allReady);
        backBtn = findViewById(R.id.backBtn);

        if (teamBdropdown.getSelectedItem().toString() != teamAdropdown.getSelectedItem().toString()){
            teamAname = teamAdropdown.getSelectedItem().toString();
            teamBname = teamBdropdown.getSelectedItem().toString();

            teamATextView.setText("Team A " + teamAname);
            teamBTextView.setText("Team B " + teamBname);

            tiimienValintaLayout.setVisibility(View.GONE);
            bestOfLayout.setVisibility(View.GONE);
            readyButton.setVisibility(View.GONE);

            allReadyBtn.setVisibility(View.VISIBLE);
            backBtn.setVisibility(View.VISIBLE);
            mapList.setVisibility(View.VISIBLE);


            pickedMaps = new ArrayList<String>();

            Log.d("applikaatio","Painettiin valmiina nappia");
        }
        else
        {
            Log.d("applikaatio","Tiimit olivat samat");
        }

    }


    public void backButton (View v){
        tiimienValintaLayout=findViewById(R.id.lisaa_ottelu_teams_layout);
        bestOfLayout=findViewById(R.id.lisaa_ottelu_bestOf_layout);
        readyButton = findViewById(R.id.readyButton);
        teamAdropdown = findViewById(R.id.teamADropdown);
        teamBdropdown = findViewById(R.id.teamBDropdown);
        teamATextView= findViewById(R.id.teamA);
        teamBTextView= findViewById(R.id.teamB);
        allReadyBtn = findViewById(R.id.allReady);
        backBtn = findViewById(R.id.backBtn);



        teamATextView.setText("Team A");
        teamBTextView.setText("Team B ");

        tiimienValintaLayout.setVisibility(View.VISIBLE);
        bestOfLayout.setVisibility(View.VISIBLE);
        readyButton.setVisibility(View.VISIBLE);

        allReadyBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.GONE);
        mapList.setVisibility(View.GONE);

        clickCounter = 0;


        Log.d("applikaatio","Painettiin takaisin nappia");
    }



    //tähän tietokantaan tallentelut sun muut
    public void allReadyButton (View v){

        Log.d("applikaatio","--------------------------------------------------------------------");
        Log.d("applikaatio","Lähetetään dataa eteenpäin ja palataan takaisin turnaushallintaan->>");
        Log.d("applikaatio","Team A name = " +teamAname);
        Log.d("applikaatio","Team B name = " +teamBname);
        Log.d("applikaatio","Tournament format = " +bestOf);
        Log.d("applikaatio","Game = " +game);


        for(int i = 0; i< pickedMaps.size();i++){
            Log.d("applikaatio","Map " + i + " = " + pickedMaps.get(i));
        }

        Log.d("applikaatio","Team/side = " +firstPlayedMapSide);
        Log.d("applikaatio","Team/side = " +secondPlayedMapSide);

        Intent myIntent = new Intent(LisaaOttelu.this, TurnauksenHallinta.class);
        //myIntent.putExtra("key", value);
        LisaaOttelu.this.startActivity(myIntent);
    }



    public void onItemClick(AdapterView parent, View v, int position, long id) {

        pickingTeam = findViewById(R.id.CurrentTeam);
        action = findViewById(R.id.Action);
        teamAdropdown = findViewById(R.id.teamADropdown);
        teamBdropdown = findViewById(R.id.teamBDropdown);
        startingSideFrame = v.findViewById(R.id.startingSideFrame);
        startingSide= v.findViewById(R.id.startingSide);
        pickedMapName = v.findViewById(R.id.add_match_listview_textEdit);
        bestOfDropdown = findViewById(R.id.bestOfDropdown);
        CTChoose = v.findViewById(R.id.CT);
        TerroristChoose = v.findViewById(R.id.Terrorist);



        bestOf = Integer.valueOf(bestOfDropdown.getSelectedItem().toString());

        //Peli / bestof / varmistetaan että tiimit eivät ole samat
        if(game == "CS:GO"
                && bestOf == 3
                && teamBdropdown.getSelectedItem().toString() != teamAdropdown.getSelectedItem().toString()){


            Log.d("applikaatio","Painettiin valintaa " + position);

            //katsotaan mikä kartta valittiin
            //jos kartta pickataan tehdään puolen valinta napeista näkyviä
            switch (clickCounter){
                case 0:
                    pickingTeam.setText("Next team " + teamAdropdown.getSelectedItem().toString());
                    action.setText("Ban 2");
                    clickCounter++;
                    pickedMaps.add(pickedMapName.getText().toString());
                    break;
                case 1:
                    pickingTeam.setText("Next team " + teamBdropdown.getSelectedItem().toString());
                    action.setText("Pick 1");
                    clickCounter++;
                    pickedMaps.add(pickedMapName.getText().toString());
                    break;
                case 2:
                    pickingTeam.setText("Next team " + teamAdropdown.getSelectedItem().toString());
                    action.setText("Pick 2");

                    startingSideFrame.setVisibility(View.VISIBLE);

                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            firstPlayedMapSide = teamAdropdown.getSelectedItem().toString()+"/CT";
                            Log.d("applikaatio","Valittiin CT");
                            startingSideFrame.setVisibility(View.INVISIBLE);
                        }
                     });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            firstPlayedMapSide = teamAdropdown.getSelectedItem().toString()+"/Terrorist";
                            Log.d("applikaatio","Valittiin Terrorist");
                            startingSideFrame.setVisibility(View.INVISIBLE);
                        }
                    });

                    startingSide.setText("Side: " + teamAdropdown.getSelectedItem().toString());
                    clickCounter++;
                    pickedMaps.add(pickedMapName.getText().toString());
                    break;
                case 3:
                    pickingTeam.setText("Next team " + teamBdropdown.getSelectedItem().toString());
                    action.setText("Ban 3");
                    clickCounter++;
                    startingSideFrame.setVisibility(View.VISIBLE);


                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            secondPlayedMapSide = teamBdropdown.getSelectedItem().toString()+"/CT";
                            Log.d("applikaatio","Valittiin CT");
                            startingSideFrame.setVisibility(View.INVISIBLE);
                        }
                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            secondPlayedMapSide = teamBdropdown.getSelectedItem().toString()+"/Terrorist";
                            Log.d("applikaatio","Valittiin Terrorist");
                            startingSideFrame.setVisibility(View.INVISIBLE);
                        }
                    });

                    startingSide.setText("Side: " + teamBdropdown.getSelectedItem().toString());
                    pickedMaps.add(pickedMapName.getText().toString());
                    break;
                case 4:
                    pickingTeam.setText("Next team " + teamAdropdown.getSelectedItem().toString());
                    action.setText("Ban 4");
                    clickCounter++;
                    pickedMaps.add(pickedMapName.getText().toString());
                    break;
                case 5:
                    pickingTeam.setText("Final map");
                    action.setText("");
                    clickCounter++;
                    pickedMaps.add(pickedMapName.getText().toString());
                    break;
                case 6:
                    pickingTeam.setText("");
                    action.setText("");
                    clickCounter++;
                    pickedMaps.add(pickedMapName.getText().toString());
                    break;
            }


            Log.d("Applikaatio","click count = " +clickCounter);

            //TODO Overlay elementtien lisääminen/näkyvyyden muuttaminen riippuen valitusta kartasta
            switch (position){
                case 0:

                    break;
                case 1:

                    break;

                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;

                case 6:

                    break;
            }

        }
        else if(teamBdropdown.getSelectedItem().toString() == teamAdropdown.getSelectedItem().toString()) {
            Log.d("applikaatio", "valitut tiimit ovat samat");

        }
        else {
            Log.d("applikaatio", "Valittu formaatti Best of " + bestOfDropdown.getSelectedItem().toString() + " talla hetkella tuetaan vain BO3");
        }

    }


}

