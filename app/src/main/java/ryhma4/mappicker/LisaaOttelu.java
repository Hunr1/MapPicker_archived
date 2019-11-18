package ryhma4.mappicker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LisaaOttelu extends AppCompatActivity {

    ListView mapList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisaa_ottelu);

        //Haetaan strings.xml tiedostosta karttojen nimet ja kuvatiedostojen nimet
        List<String> mapNames = Arrays.asList(getResources().getStringArray(R.array.mapNames));
        List<String> mapImages = Arrays.asList(getResources().getStringArray(R.array.mapImages));


        mapList = findViewById(R.id.maps);


        //Tehdään arraylist Map olioista
        ArrayList<Map> csgoMaplist = new ArrayList<>();

        //Luodaan karttaoliot haetuilla karttojen kuvilla ja nimillä
        for (int i = 0; i < mapImages.size(); i++) {
            String mapImage = mapImages.get(i);
            int mapImgID = getResources().getIdentifier(mapImage, "drawable", getPackageName());

            Log.d("applikaatio", "lisataan " + mapNames.get(i) + "    " + mapImgID);

            //lisätään olio csgoMaplist arraylistiin
            csgoMaplist.add(new Map(mapNames.get(i), mapImgID));
        }

        //luodaan custom adapteri listvieviä varten
        MapListAdapter adapter = new MapListAdapter(this,R.layout.add_match_listview,csgoMaplist);
        mapList.setAdapter(adapter);
    }

}

