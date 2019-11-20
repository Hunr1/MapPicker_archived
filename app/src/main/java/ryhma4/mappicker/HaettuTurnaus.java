package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class HaettuTurnaus extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haettu_turnaus);

        int index = getIntent().getIntExtra("TOURNAMENT_ID", -1);

        if (index >= 0) {
            Tournament tournament = TournamentApplication.getEngine().tournamentByID(index);
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

                Intent intent = new Intent (HaettuTurnaus.this, LisaaOttelu.class);
                startActivity(intent);
                break;
        }
    }
}