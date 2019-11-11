package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LuoUusiTurnaus extends AppCompatActivity implements View.OnClickListener {

    private Button btnCancel;
    private Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luo_uusi_turnaus);

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnCancel) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}