package ryhma4.mappicker;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HaettuTurnausCustomAdapter extends ArrayAdapter<Match> {
    private static final String TAG ="MapListAdapter";
    private Context mContext;
    private  int mResource;
    private String game;

    ArrayList<Map> csgoMaplist;

    HaettuTurnausCustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Match> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        game =  "CS:GO";
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        final ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.match_list_csgo, parent, false);
            holder = new ViewHolder();

            holder.bestOf1 = convertView.findViewById(R.id.bestOf1);
            holder.bestOf3 = convertView.findViewById(R.id.bestOf3);

            holder.Bo3teamAName = convertView.findViewById(R.id.Bo3teamAName);
            holder.Bo3teamBName = convertView.findViewById(R.id.Bo3teamBName);

            holder.Bo3teamAMapScore = convertView.findViewById(R.id.Bo3teamAMapScore);
            holder.Bo3teamBMapScore = convertView.findViewById(R.id.Bo3teamBMapScore);

            holder.Bo3pick1Title = convertView.findViewById(R.id.Bo3pick1Title);
            holder.Bo3pick2Title = convertView.findViewById(R.id.Bo3pick2Title);
            holder.Bo3pick3Title = convertView.findViewById(R.id.Bo3pick3Title);

            holder.Bo3ban1Title = convertView.findViewById(R.id.Bo3ban1Title);
            holder.Bo3ban2Title = convertView.findViewById(R.id.Bo3ban2Title);
            holder.Bo3ban3Title = convertView.findViewById(R.id.Bo3ban3Title);
            holder.Bo3ban4Title = convertView.findViewById(R.id.Bo3ban4Title);


            holder.Bo3pick1Image = convertView.findViewById(R.id.Bo3pick1Image);
            holder.Bo3pick2Image = convertView.findViewById(R.id.Bo3pick2Image);
            holder.Bo3pick3Image = convertView.findViewById(R.id.Bo3pick3Image);

            holder.Bo3pick1Name = convertView.findViewById(R.id.Bo3pick1Name);
            holder.Bo3pick2Name = convertView.findViewById(R.id.Bo3pick2Name);
            holder.Bo3pick3Name = convertView.findViewById(R.id.Bo3pick3Name);

            holder.Bo3pick1_teamA_rounds = convertView.findViewById(R.id.Bo3pick1TeamARounds);
            holder.Bo3pick1_teamB_rounds = convertView.findViewById(R.id.Bo3pick1TeamBRounds);
            holder.Bo3pick2_teamA_rounds = convertView.findViewById(R.id.Bo3pick2TeamARounds);
            holder.Bo3pick2_teamB_rounds = convertView.findViewById(R.id.Bo3pick2TeamBRounds);
            holder.Bo3pick3_teamA_rounds = convertView.findViewById(R.id.Bo3pick3TeamARounds);
            holder.Bo3pick3_teamB_rounds = convertView.findViewById(R.id.Bo3pick3TeamBRounds);

            holder.Bo3ban1Image = convertView.findViewById(R.id.Bo3ban1Image);
            holder.Bo3ban2Image = convertView.findViewById(R.id.Bo3ban2Image);
            holder.Bo3ban3Image = convertView.findViewById(R.id.Bo3ban3Image);
            holder.Bo3ban4Image = convertView.findViewById(R.id.Bo3ban4Image);


            holder.Bo3ban1Name = convertView.findViewById(R.id.Bo3ban1Name);
            holder.Bo3ban2Name = convertView.findViewById(R.id.Bo3ban2Name);
            holder.Bo3ban3Name = convertView.findViewById(R.id.Bo3ban3Name);
            holder.Bo3ban4Name = convertView.findViewById(R.id.Bo3ban4Name);

            holder.bestOf1 = convertView.findViewById(R.id.bestOf1);
            holder.bestOf3 = convertView.findViewById(R.id.bestOf3);

            holder.Bo1teamAName = convertView.findViewById(R.id.Bo1teamAname);
            holder.Bo1teamBName = convertView.findViewById(R.id.Bo1teamBname);


            holder.Bo1pick1Title = convertView.findViewById(R.id.Bo1pick1Title);

            holder.Bo1ban1Title = convertView.findViewById(R.id.Bo1ban1Title);
            holder.Bo1ban2Title = convertView.findViewById(R.id.Bo1ban2Title);
            holder.Bo1ban3Title = convertView.findViewById(R.id.Bo1ban3Title);
            holder.Bo1ban4Title = convertView.findViewById(R.id.Bo1ban4Title);
            holder.Bo1ban5Title = convertView.findViewById(R.id.Bo1ban5Title);
            holder.Bo1ban6Title = convertView.findViewById(R.id.Bo1ban6Title);


            holder.Bo1pick1Image = convertView.findViewById(R.id.Bo1pick1Image);
            holder.Bo1pick1Name = convertView.findViewById(R.id.Bo1pick1Name);

            holder.Bo1pick1_teamA_rounds = convertView.findViewById(R.id.Bo1pick1TeamARounds);
            holder.Bo1pick1_teamB_rounds = convertView.findViewById(R.id.Bo1pick1TeamBRounds);


            holder.Bo1ban1Image = convertView.findViewById(R.id.Bo1ban1Image);
            holder.Bo1ban2Image = convertView.findViewById(R.id.Bo1ban2Image);
            holder.Bo1ban3Image = convertView.findViewById(R.id.Bo1ban3Image);
            holder.Bo1ban4Image = convertView.findViewById(R.id.Bo1ban4Image);
            holder.Bo1ban5Image = convertView.findViewById(R.id.Bo1ban5Image);
            holder.Bo1ban6Image = convertView.findViewById(R.id.Bo1ban6Image);


            holder.Bo1ban1Name = convertView.findViewById(R.id.Bo1ban1Name);
            holder.Bo1ban2Name = convertView.findViewById(R.id.Bo1ban2Name);
            holder.Bo1ban3Name = convertView.findViewById(R.id.Bo1ban3Name);
            holder.Bo1ban4Name = convertView.findViewById(R.id.Bo1ban4Name);
            holder.Bo1ban5Name = convertView.findViewById(R.id.Bo1ban5Name);
            holder.Bo1ban6Name = convertView.findViewById(R.id.Bo1ban6Name);
            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }


        final List<String> mapImages = Arrays.asList(mContext.getResources().getStringArray(R.array.mapImages));
        List<String> mapNames = Arrays.asList(mContext.getResources().getStringArray(R.array.mapNames));

        if (game.equals("CS:GO"))
        {
            csgoMaplist = new ArrayList<>();
            //Tehdään arraylist Map olioista
            //Luodaan karttaoliot haetuilla karttojen kuvilla ja nimillä
            for (int i = 0; i < mapImages.size(); i++) {
                String mapImage = mapImages.get(i);
                int mapImgID = mContext.getResources().getIdentifier(mapImage, "drawable", mContext.getPackageName());
                //lisätään olio csgoMaplist arraylistiin
                csgoMaplist.add(new Map(mapNames.get(i), mapImgID));
            }
        }

        holder.match = getItem(position);

        Log.d("Applikaatio","Haettu match = " +holder.match.toString());
        if(!holder.match.getGameFormat().isEmpty()){
            switch (holder.match.getGameFormat()){
                case("1"):
                    holder.bestOf1.setVisibility(View.VISIBLE);
                    holder.bestOf3.setVisibility(View.GONE);
                    break;
                case("3"):
                    holder.bestOf3.setVisibility(View.VISIBLE);
                    holder.bestOf1.setVisibility(View.GONE);
                    break;
                case("5"):
                    break;
            }
        }


        holder.Bo3teamAMapScore.setText(holder.match.getTeamAMapScore());
        holder.Bo3teamBMapScore.setText(holder.match.getTeamBMapScore());

        holder.Bo1pick1_teamA_rounds.setText(holder.match.getTeam_A_map1_score());
        holder.Bo1pick1_teamB_rounds.setText(holder.match.getTeam_B_map1_score());


        holder.Bo3pick1_teamA_rounds.setText(holder.match.getTeam_A_map1_score());
        holder.Bo3pick1_teamB_rounds.setText(holder.match.getTeam_B_map1_score());
        holder.Bo3pick2_teamA_rounds.setText(holder.match.getTeam_A_map2_score());
        holder.Bo3pick2_teamB_rounds.setText(holder.match.getTeam_B_map2_score());
        holder.Bo3pick3_teamA_rounds.setText(holder.match.getTeam_A_map3_score());
        holder.Bo3pick3_teamB_rounds.setText(holder.match.getTeam_B_map3_score());

        ArrayList<String> mapInfo;
        ArrayList<String> action = new ArrayList<>();
        ArrayList<String> mapName = new ArrayList<>();
        ArrayList<String> whoPickedMap = new ArrayList<>();
        ArrayList<String> side = new ArrayList<>();
        ArrayList<String> whoPickedSide = new ArrayList<>();

        mapInfo = holder.match.getMapInfo();
        Log.d("Applikaatio","map info = " + holder.match.getMapInfo().toString());

        for(int counter = 0;counter < mapInfo.size(); counter++){
            String[] splittedMapInfo = mapInfo.get(counter).split("/");

            if(splittedMapInfo.length == 3){
                action.add(splittedMapInfo[0]);
                mapName.add(splittedMapInfo[1]);
                whoPickedMap.add(splittedMapInfo[2]);
            }
            else if(splittedMapInfo.length == 5)
            {
                action.add(splittedMapInfo[0]);
                mapName.add(splittedMapInfo[1]);
                whoPickedMap.add(splittedMapInfo[2]);
                side.add(splittedMapInfo[3]);
                whoPickedSide.add(splittedMapInfo[4]);
            }
            else if(splittedMapInfo.length == 2)
            {
                action.add(splittedMapInfo[0]);
                mapName.add(splittedMapInfo[1]);
            }
            else
            {
                Log.d("Applikaatio#","Virheellinen määrä dataa mapInfossa");
            }
        }


        holder.Bo3teamAName.setText(holder.match.getTeamAname());
        holder.Bo3teamBName.setText(holder.match.getTeamBname());

        holder.Bo1teamAName.setText(holder.match.getTeamAname());
        holder.Bo1teamBName.setText(holder.match.getTeamBname());


        //EditTextien kuuntelijat
        holder.Bo3teamAMapScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeamAMapScore(holder.Bo3teamAMapScore.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo3teamBMapScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeamBMapScore(holder.Bo3teamBMapScore.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo1pick1_teamA_rounds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeam_A_map1_score(holder.Bo1pick1_teamA_rounds.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo1pick1_teamB_rounds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeam_B_map1_score(holder.Bo1pick1_teamB_rounds.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo3pick1_teamA_rounds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeam_A_map1_score(holder.Bo3pick1_teamA_rounds.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo3pick1_teamB_rounds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeam_B_map1_score(holder.Bo3pick1_teamB_rounds.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo3pick2_teamA_rounds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeam_A_map2_score(holder.Bo3pick2_teamA_rounds.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo3pick2_teamB_rounds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeam_B_map2_score(holder.Bo3pick2_teamB_rounds.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo3pick3_teamA_rounds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeam_A_map3_score(holder.Bo3pick3_teamA_rounds.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.Bo3pick3_teamB_rounds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                holder.match.setTeam_B_map3_score(holder.Bo3pick3_teamB_rounds.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        for(int k = 0; k< action.size();k++){
            Log.d("Applikaatio","Toiminto = " + action.get(k));
            switch (action.get(k)){
                case("Pick 1"):
                    holder.Bo3pick1Name.setText(mapName.get(k));
                    holder.Bo3pick1Title.setText(mapInfo.get(k));
                    holder.Bo1pick1Name.setText(mapName.get(k));
                    holder.Bo1pick1Title.setText(mapInfo.get(k));

                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo3pick1Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            holder.Bo1pick1Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                    break;
                case("Pick 2"):
                    holder.Bo3pick2Name.setText(mapName.get(k));
                    holder.Bo3pick2Title.setText(mapInfo.get(k));

                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo3pick2Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                    break;
                case("Tiebreaker"):
                    holder.Bo3pick3Name.setText(mapName.get(k));
                    holder.Bo3pick3Title.setText(mapInfo.get(k));

                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo3pick3Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                    break;
                case ("Ban 1"):
                    holder.Bo3ban1Name.setText(mapName.get(k));
                    holder.Bo3ban1Title.setText(mapInfo.get(k));
                    holder.Bo1ban1Name.setText(mapName.get(k));
                    holder.Bo1ban1Title.setText(mapInfo.get(k));

                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo3ban1Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            holder.Bo1ban1Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                    break;
                case ("Ban 2"):
                    holder.Bo3ban2Name.setText(mapName.get(k));
                    holder.Bo3ban2Title.setText(mapInfo.get(k));
                    holder.Bo1ban2Name.setText(mapName.get(k));
                    holder.Bo1ban2Title.setText(mapInfo.get(k));


                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo3ban2Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            holder.Bo1ban2Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                    break;
                case ("Ban 3"):
                    holder.Bo3ban3Name.setText(mapName.get(k));
                    holder.Bo3ban3Title.setText(mapInfo.get(k));
                    holder.Bo1ban3Name.setText(mapName.get(k));
                    holder.Bo1ban3Title.setText(mapInfo.get(k));

                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo3ban3Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            holder.Bo1ban3Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                    break;
                case ("Ban 4"):
                    holder.Bo3ban4Name.setText(mapName.get(k));
                    holder.Bo3ban4Title.setText(mapInfo.get(k));
                    holder.Bo1ban4Name.setText(mapName.get(k));
                    holder.Bo1ban4Title.setText(mapInfo.get(k));

                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo3ban4Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            holder.Bo1ban4Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                case ("Ban 5"):
                    holder.Bo1ban5Name.setText(mapName.get(k));
                    holder.Bo1ban5Title.setText(mapInfo.get(k));

                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo1ban5Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                    break;
                case ("Ban 6"):
                    holder.Bo1ban6Name.setText(mapName.get(k));
                    holder.Bo1ban6Title.setText(mapInfo.get(k));
                    for (int j = 0; j<csgoMaplist.size();j++){
                        if(mapName.get(k).equals(csgoMaplist.get(j).getMapName())){
                            holder.Bo1ban6Image.setImageResource(csgoMaplist.get(j).getMapImage());
                            break;
                        }
                    }
                break;
            }
        }

        return convertView;

    }

    class ViewHolder {

        Match match;

        LinearLayout bestOf1;
        LinearLayout bestOf3;

        TextView Bo3teamAName;
        TextView Bo3teamBName;

        TextView Bo1teamAName;
        TextView Bo1teamBName;

        EditText Bo3teamAMapScore;
        EditText Bo3teamBMapScore;


        TextView Bo3pick1Title;
        TextView Bo3pick2Title;
        TextView Bo3pick3Title;

        TextView Bo3ban1Title;
        TextView Bo3ban2Title;
        TextView Bo3ban3Title;
        TextView Bo3ban4Title;


        ImageView Bo3pick1Image;
        ImageView Bo3pick2Image;
        ImageView Bo3pick3Image;


        TextView Bo3pick1Name;
        TextView Bo3pick2Name;
        TextView Bo3pick3Name;

        EditText Bo3pick1_teamA_rounds;
        EditText Bo3pick1_teamB_rounds;

        EditText Bo3pick2_teamA_rounds;
        EditText Bo3pick2_teamB_rounds;

        EditText Bo3pick3_teamA_rounds;
        EditText Bo3pick3_teamB_rounds;

        ImageView Bo3ban1Image;
        ImageView Bo3ban2Image;
        ImageView Bo3ban3Image;
        ImageView Bo3ban4Image;


        TextView Bo3ban1Name;
        TextView Bo3ban2Name;
        TextView Bo3ban3Name;
        TextView Bo3ban4Name;

        TextView Bo1pick1Title;

        TextView Bo1ban1Title;
        TextView Bo1ban2Title;
        TextView Bo1ban3Title;
        TextView Bo1ban4Title;
        TextView Bo1ban5Title;
        TextView Bo1ban6Title;

        ImageView Bo1pick1Image;

        TextView Bo1pick1Name;

        ImageView Bo1ban1Image;
        ImageView Bo1ban2Image;
        ImageView Bo1ban3Image;
        ImageView Bo1ban4Image;
        ImageView Bo1ban5Image;
        ImageView Bo1ban6Image;

        TextView Bo1ban1Name;
        TextView Bo1ban2Name;
        TextView Bo1ban3Name;
        TextView Bo1ban4Name;
        TextView Bo1ban5Name;
        TextView Bo1ban6Name;

        EditText Bo1pick1_teamA_rounds;
        EditText Bo1pick1_teamB_rounds;

    }

}
