package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TurnauksenHallinta extends AppCompatActivity implements View.OnClickListener {

    int tourID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turnauksen_hallinta);
        findViewById(R.id.addMatchBtn).setOnClickListener(this);

        tourID = getIntent().getIntExtra("TOURNAMENT_ID", -1);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addMatchBtn:
                Intent intent = new Intent(this, LisaaOttelu.class);
                intent.putExtra("TOURNAMENT_ID", tourID );
                startActivity(intent);
                break;
        }
    }
}