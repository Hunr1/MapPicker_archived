package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HaettuTurnaus extends AppCompatActivity {

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
    }
}