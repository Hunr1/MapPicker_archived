package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.newTournamentBtn).setOnClickListener(this);
        findViewById(R.id.searchBtn).setOnClickListener(this);
        ListView tournamentList = findViewById(R.id.TournamentsList);
        tournamentList.setOnItemClickListener(this);

        TournamentEngine tournamentEngine = TournamentApplication.getEngine();
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
                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
        Intent intent = new Intent(this, HaettuTurnaus.class);
        intent.putExtra("TOURNAMENT_INDEX", index);
        startActivity(intent);
    }
}
