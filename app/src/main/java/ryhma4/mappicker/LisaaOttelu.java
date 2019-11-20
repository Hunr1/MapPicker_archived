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
    String thirdPlayedMapSide;
    List<String> pickedMaps;
    ArrayList<Map> csgoMaplist;
    MapListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisaa_ottelu);

        //Haetaan strings.xml tiedostosta karttojen nimet ja kuvatiedostojen nimet
        List<String> mapNames = Arrays.asList(getResources().getStringArray(R.array.mapNames));
        List<String> mapImages = Arrays.asList(getResources().getStringArray(R.array.mapImages));


        mapList = findViewById(R.id.maps);


        //Tehdään arraylist Map olioista
        csgoMaplist = new ArrayList<>();

        //Luodaan karttaoliot haetuilla karttojen kuvilla ja nimillä
        for (int i = 0; i < mapImages.size(); i++) {
            String mapImage = mapImages.get(i);
            int mapImgID = getResources().getIdentifier(mapImage, "drawable", getPackageName());

            Log.d("applikaatio", "lisataan " + mapNames.get(i) + "    " + mapImgID);

            //lisätään olio csgoMaplist arraylistiin
            csgoMaplist.add(new Map(mapNames.get(i), mapImgID));
        }

        //luodaan custom adapteri listvieviä varten
        adapter = new MapListAdapter(this,R.layout.add_match_listview,csgoMaplist);
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
        pickingTeam = findViewById(R.id.CurrentTeam);
        action = findViewById(R.id.Action);

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

            pickingTeam.setText(teamBname);
            action.setText("Ban 1");

            Log.d("applikaatio","Painettiin valmiina nappia");
        }
        else
        {
            Log.d("applikaatio","Tiimit olivat samat");
        }


    }


    public void backButton (View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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

        Intent myIntent = new Intent(LisaaOttelu.this, HaettuTurnaus.class);
        //myIntent.putExtra("key", value);
        LisaaOttelu.this.startActivity(myIntent);
    }



    public void onItemClick(AdapterView parent, View v, final int position, long id) {

        pickingTeam = findViewById(R.id.CurrentTeam);
        action = findViewById(R.id.Action);
        teamAdropdown = findViewById(R.id.teamADropdown);
        teamBdropdown = findViewById(R.id.teamBDropdown);
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
        if (game == "CS:GO"
                && bestOf == 3
                && teamBdropdown.getSelectedItem().toString() != teamAdropdown.getSelectedItem().toString()) {


            Log.d("applikaatio", "Painettiin valintaa " + position);

            //katsotaan mikä kartta valittiin
            //jos kartta pickataan tehdään puolen valinta napeista näkyviä

            csgoMaplist.get(position).getSelected();
            switch (clickCounter) {
                case 0:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Ban 2");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 1/"+pickedMapName.getText().toString()+ "/" +teamBdropdown.getSelectedItem().toString());
                    break;



                case 1:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText("Pick 1");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 2/"+pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString());

                    break;



                case 2:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Pick 2");

                    startingSideFrame.setVisibility(View.VISIBLE);

                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Log.d("applikaatio", "Valittiin CT");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            firstPlayedMapSide ="CT/" + teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+ firstPlayedMapSide);
                        }


                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin Terrorist");
                            csgoMaplist.remove(position);
                            startingSideFrame.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();


                            firstPlayedMapSide = "Terrorist/"+teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+firstPlayedMapSide);
                        }
                    });


                    startingSide.setText("Side: " + teamBdropdown.getSelectedItem().toString());
                    clickCounter++;
                    break;

                    case 3:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText("Ban 3");
                    clickCounter++;
                    startingSideFrame.setVisibility(View.VISIBLE);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Log.d("applikaatio", "Valittiin CT");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            secondPlayedMapSide = "CT/"+teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 2/"+pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString()+"/"+ secondPlayedMapSide);
                        }
                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin Terrorist");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            secondPlayedMapSide = "Terrorist/"+teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 2/"+ pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString()+"/"+ secondPlayedMapSide);
                        }
                    });

                    startingSide.setText("Side: " + teamAdropdown.getSelectedItem().toString());
                    break;



                case 4:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Ban 4");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 3/"+pickedMapName.getText().toString()+ "/" +teamBdropdown.getSelectedItem().toString());
                    break;



                case 5:
                    pickingTeam.setText("Final map");
                    action.setText("");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 4/"+pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString());
                    break;



                case 6:

                    startingSide.setText("Knife");
                    pickingTeam.setText("DONE");
                    action.setText("");
                    clickCounter++;
                    v.setOnClickListener(null);
                    pickedMaps.add("Tiebreaker/"+pickedMapName.getText().toString());
                    break;
            }



            Log.d("Applikaatio", "click count = " + clickCounter);






        //##############################################################################################################################    Best of 5   #######################################
        } else if (game == "CS:GO"
                && bestOf == 5
                && teamBdropdown.getSelectedItem().toString() != teamAdropdown.getSelectedItem().toString()) {

            switch (clickCounter) {
                case 0:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Ban 2");;
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 1/"+pickedMapName.getText().toString()+ "/" +teamBdropdown.getSelectedItem().toString());

                    break;
                case 1:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText("Pick 1");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 2/"+pickedMapName.getText().toString()+ "/" +teamAdropdown.getSelectedItem().toString());
                    break;

                case 2:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Pick 2");

                    startingSideFrame.setVisibility(View.VISIBLE);

                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            firstPlayedMapSide = teamAdropdown.getSelectedItem().toString() + "/CT";
                            Log.d("applikaatio", "Valittiin CT");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            firstPlayedMapSide ="CT/" + teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+ firstPlayedMapSide);
                        }


                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin Terrorist");
                            csgoMaplist.remove(position);
                            startingSideFrame.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();

                            firstPlayedMapSide = "Terrorist/"+teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+firstPlayedMapSide);
                        }
                    });


                    startingSide.setText("Side: " + teamAdropdown.getSelectedItem().toString());
                    clickCounter++;
                    break;

                case 3:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText("Pick 3");
                    clickCounter++;
                    startingSideFrame.setVisibility(View.VISIBLE);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            secondPlayedMapSide = teamBdropdown.getSelectedItem().toString() + "/CT";
                            Log.d("applikaatio", "Valittiin CT");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            secondPlayedMapSide ="CT/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 2/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ firstPlayedMapSide);
                        }
                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin Terrorist");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            secondPlayedMapSide ="Terrorist/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 2/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ firstPlayedMapSide);
                        }
                    });

                    startingSide.setText("Side: " + teamBdropdown.getSelectedItem().toString());
                    break;

                case 4:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Pick 4");

                    startingSideFrame.setVisibility(View.VISIBLE);

                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            thirdPlayedMapSide = teamAdropdown.getSelectedItem().toString() + "/CT";
                            Log.d("applikaatio", "Valittiin CT");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);

                            secondPlayedMapSide ="CT/" + teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 3/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+ secondPlayedMapSide);
                        }


                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin Terrorist");
                            csgoMaplist.remove(position);
                            startingSideFrame.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();


                            secondPlayedMapSide ="Terrorist/" + teamAdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 3/"+pickedMapName.getText().toString()+ "/" + teamBdropdown.getSelectedItem().toString()+"/"+ secondPlayedMapSide);
                        }
                    });


                    startingSide.setText("Side: " + teamAdropdown.getSelectedItem().toString());
                    clickCounter++;
                    break;

                case 5:
                    pickingTeam.setText("Final Map");
                    action.setText("");
                    clickCounter++;
                    startingSideFrame.setVisibility(View.VISIBLE);
                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin CT");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            thirdPlayedMapSide ="CT/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 4/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ thirdPlayedMapSide);
                        }
                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin Terrorist");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);


                            thirdPlayedMapSide ="Terrorist/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 4/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ thirdPlayedMapSide);
                        }
                    });
                    startingSide.setText("Side: " + teamBdropdown.getSelectedItem().toString());
                    break;
                case 6:
                    startingSide.setText("Knife");
                    pickingTeam.setText("DONE");
                    action.setText("");
                    clickCounter++;
                    v.setOnClickListener(null);
                    pickedMaps.add("Tiebreaker/"+pickedMapName.getText().toString());
                    break;


            }
        }
        else if (game == "CS:GO"
                && bestOf == 1
                && teamBdropdown.getSelectedItem().toString() != teamAdropdown.getSelectedItem().toString()){

            switch (clickCounter) {
                case 0:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Ban 2");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 1/" + pickedMapName.getText().toString() + "/" + teamBdropdown.getSelectedItem().toString());
                    break;
                case 1:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText("Ban 3");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 2/" + pickedMapName.getText().toString() + "/" + teamAdropdown.getSelectedItem().toString());
                    break;

                case 2:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Ban 4");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 3/" + pickedMapName.getText().toString() + "/" + teamBdropdown.getSelectedItem().toString());
                    break;

                case 3:
                    pickingTeam.setText(teamBdropdown.getSelectedItem().toString());
                    action.setText("Ban 5");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 4/" + pickedMapName.getText().toString() + "/" + teamAdropdown.getSelectedItem().toString());
                    break;

                case 4:
                    pickingTeam.setText(teamAdropdown.getSelectedItem().toString());
                    action.setText("Ban 6");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 5/" + pickedMapName.getText().toString() + "/" + teamBdropdown.getSelectedItem().toString());
                    break;
                case 5:
                    pickingTeam.setText("Final map");
                    action.setText("");
                    clickCounter++;
                    csgoMaplist.remove(position);
                    adapter.notifyDataSetChanged();
                    pickedMaps.add("Ban 6/" + pickedMapName.getText().toString() + "/" + teamAdropdown.getSelectedItem().toString());
                    break;

                case 6:
                    pickingTeam.setText(" ");
                    action.setText("DONE");

                    startingSideFrame.setVisibility(View.VISIBLE);

                    //Puolten valinta nappien onClick kuuntelijat
                    CTChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin CT");
                            csgoMaplist.remove(position);
                            adapter.notifyDataSetChanged();
                            startingSideFrame.setVisibility(View.INVISIBLE);

                            firstPlayedMapSide ="CT/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ firstPlayedMapSide);
                        }


                    });
                    TerroristChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("applikaatio", "Valittiin Terrorist");
                            csgoMaplist.remove(position);
                            startingSideFrame.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();


                            firstPlayedMapSide ="Terrorist/" + teamBdropdown.getSelectedItem().toString();
                            pickedMaps.add("Pick 1/"+pickedMapName.getText().toString()+ "/" + teamAdropdown.getSelectedItem().toString()+"/"+ firstPlayedMapSide);
                        }
                    });


                    startingSide.setText("Side: " + teamBdropdown.getSelectedItem().toString());
                    clickCounter++;
                    break;
            }
            Log.d("Applikaatio", "click count = " + clickCounter);

        }
        else {
            Log.d("applikaatio", "Valittu formaatti Best of " + bestOfDropdown.getSelectedItem().toString() + " talla hetkella tuetaan vain BO3");
        }


    }
}

