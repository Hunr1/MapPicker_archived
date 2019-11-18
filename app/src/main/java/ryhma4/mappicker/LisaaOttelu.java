package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LisaaOttelu extends AppCompatActivity {

    int tourID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisaa_ottelu);
        tourID = getIntent().getIntExtra("TOURNAMENT_ID", -1);
    }
}