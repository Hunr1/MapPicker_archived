package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class LuoUusiTurnaus extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnCancel;
    private Button btnGenerate;
    private int selectedItem = -1;
    Button btnAddTeam;
    Button deleteTeam;
    ListView lvTeams;
    EditText etGetTeam;
    ArrayAdapter<String> adapter;
    ArrayList<String> listTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luo_uusi_turnaus);

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnAddTeam = findViewById(R.id.btnAddTeam);
        btnAddTeam.setOnClickListener(this);
        deleteTeam = findViewById(R.id.btnDeleteTeam);
        deleteTeam.setOnClickListener(this);

        lvTeams = findViewById(R.id.lvTeams);
        etGetTeam = findViewById(R.id.editTextTeams);
        listTeams = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTeams);
        lvTeams.setAdapter(adapter);

        lvTeams.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnCancel:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnAddTeam:

                if (etGetTeam.getText().length() > 0) {
                    listTeams.add(etGetTeam.getText().toString());
                    etGetTeam.setText("");
                    adapter.notifyDataSetChanged();
                }
                break;

            case R.id.btnDeleteTeam:

                 if (selectedItem != -1) {
                     listTeams.remove(selectedItem);
                     adapter.notifyDataSetChanged();
                     lvTeams.setSelector(R.color.colorTransparent);


                     selectedItem = -1;
                 }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        selectedItem = position;
        lvTeams.setSelector(R.color.colorPrimaryDark);

    }
}