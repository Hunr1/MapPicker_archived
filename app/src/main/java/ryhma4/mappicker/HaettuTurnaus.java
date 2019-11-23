package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HaettuTurnaus extends AppCompatActivity implements View.OnClickListener {

    TextView tournamentId;
    private int tourID;

    LinearLayout bestOf1;
    LinearLayout bestOf3;

    TextView teamAName;
    TextView teamBName;

    EditText teamAMapScore;
    EditText teamBMapScore;

    TextView pick1Title;
    TextView pick2Title;
    TextView pick3Title;

    TextView ban1Title;
    TextView ban2Title;
    TextView ban3Title;
    TextView ban4Title;
    TextView ban5Title;
    TextView ban6Title;

    ImageView pick1Image;
    ImageView pick2Image;
    ImageView pick3Image;

    TextView pick1Name;
    TextView pick2Name;
    TextView pick3Name;

    EditText pick1_teamA_rounds;
    EditText pick1_teamB_rounds;

    EditText pick2_teamA_rounds;
    EditText pick2_teamB_rounds;

    EditText pick3_teamA_rounds;
    EditText pick3_teamB_rounds;

    ImageView ban1Image;
    ImageView ban2Image;
    ImageView ban3Image;
    ImageView ban4Image;
    ImageView ban5Image;
    ImageView ban6Image;

    TextView ban1Name;
    TextView ban2Name;
    TextView ban3Name;
    TextView ban4Name;
    TextView ban5Name;
    TextView ban6Name;


    int matchMode = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haettu_turnaus);

        tourID = getIntent().getIntExtra("TOURNAMENT_ID", -1);


        //poista kommentit ja kommentoi rivit 151,152 ja 156 nähdäksesi esimerkin otteluiden viewistä
        //haettu turnays layouttiin tulee custom adapterilla listview joka täytetään match_list_csgo layouteilla

        /* POISTA
        setContentView(R.layout.match_list_csgo);

        bestOf1 = findViewById(R.id.bestOf1);
        bestOf3 = findViewById(R.id.bestOf3);

        teamAName = findViewById(R.id.teamANameTextView);
        teamBName = findViewById(R.id.teamBNameTextView);

        teamAMapScore = findViewById(R.id.teamAMapScore);
        teamBMapScore = findViewById(R.id.teamBMapScore);

        pick1Title = findViewById(R.id.pick1Title);
        pick2Title = findViewById(R.id.pick2Title);
        pick3Title = findViewById(R.id.pick3Title);

        ban1Title = findViewById(R.id.ban1Title);
        ban2Title = findViewById(R.id.ban2Title);
        ban3Title = findViewById(R.id.ban3Title);
        ban4Title = findViewById(R.id.ban4Title);
        ban5Title = findViewById(R.id.ban5Title);
        ban6Title = findViewById(R.id.ban6Title);

        pick1Image = findViewById(R.id.pick1Image);
        pick2Image = findViewById(R.id.pick2Image);
        pick3Image = findViewById(R.id.pick3Image);

        pick1Name = findViewById(R.id.pick1Name);
        pick2Name = findViewById(R.id.pick2Name);
        pick3Name = findViewById(R.id.pick3Name);

        pick1_teamA_rounds = findViewById(R.id.pick1TeamARounds);
        pick1_teamB_rounds = findViewById(R.id.pick1TeamBRounds);
        pick2_teamA_rounds = findViewById(R.id.pick2TeamARounds);
        pick2_teamB_rounds = findViewById(R.id.pick2TeamBRounds);
        pick3_teamA_rounds = findViewById(R.id.pick3TeamARounds);
        pick3_teamB_rounds = findViewById(R.id.pick3TeamBRounds);

        ban1Image = findViewById(R.id.ban1Image);
        ban2Image = findViewById(R.id.ban2Image);
        ban3Image = findViewById(R.id.ban3Image);
        ban4Image = findViewById(R.id.ban4Image);
        ban5Image = findViewById(R.id.ban5Image);
        ban6Image = findViewById(R.id.ban6Image);

        ban1Name = findViewById(R.id.ban1Name);
        ban2Name = findViewById(R.id.ban2Name);
        ban3Name = findViewById(R.id.ban3Name);
        ban4Name = findViewById(R.id.ban4Name);
        ban5Name = findViewById(R.id.ban5Name);
        ban6Name = findViewById(R.id.ban6Name);

        switch (matchMode){
            case 1:
                bestOf1.setVisibility(View.VISIBLE);
                break;
            case 3:
                bestOf3.setVisibility(View.VISIBLE);
                break;
            case 5:
                Log.d("applikaatio","Ei viela valmis");
                break;

        }
        POISTA*/

        if (tourID >= 0) {
            Tournament tournament = TournamentApplication.getEngine().tournamentByID(tourID);
            tournamentId = findViewById(R.id.tournamentID);
            tournamentId.setText(getString(R.string.IDText,tournament.getTournamentID()));
        }

       findViewById(R.id.ManageTournamentBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ManageTournamentBtn:
                Intent intent = new Intent(HaettuTurnaus.this, LisaaOttelu.class);
                intent.putExtra("TOURNAMENT_ID", tourID );
                startActivity(intent);
                break;
        }
    }
}