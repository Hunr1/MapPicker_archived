package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class HaettuTurnaus extends AppCompatActivity implements View.OnClickListener {

    private int tourID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haettu_turnaus);

        tourID = getIntent().getIntExtra("TOURNAMENT_ID", -1);

        if (tourID >= 0) {
            Tournament tournament = TournamentApplication.getEngine().tournamentByID(tourID);
            TextView textView = findViewById(R.id.tournamentID);
            textView.setText(getString(R.string.IDText,tournament.getTournamentID()));
        }

        findViewById(R.id.ManageTournamentBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ManageTournamentBtn:
                Intent intent = new Intent(this, TurnauksenHallinta.class);
                intent.putExtra("TOURNAMENT_ID", tourID );
                startActivity(intent);
                break;
        }
    }
}