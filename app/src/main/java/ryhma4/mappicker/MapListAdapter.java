package ryhma4.mappicker;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;



//custom adapteri list view olio varten
public class MapListAdapter extends ArrayAdapter<Map>  {

    private static final String TAG ="MapListAdapter";
    private Context mContext;
    int mResource;

    public MapListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Map> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final Holder holder;


        if (convertView == null) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.add_match_listview, parent, false);
            holder = new Holder();
            holder.mapName = view.findViewById(R.id.add_match_listview_textEdit);
            holder.mapImg =  view.findViewById(R.id.add_match_listview_image);
            holder.CT = view.findViewById(R.id.CT);
            holder.Tero = view.findViewById(R.id.Terrorist);
            holder.startingSideFrame = view.findViewById(R.id.startingSideFrame);
            view.setTag(holder);

            Log.d("applikaatio","converView == null");

        } else {
            holder = (Holder) view.getTag();
            Log.d("applikaatio","converView =! null");
        }


        holder.mapName.setText(getItem(position).getMapName());
        holder.mapImg.setImageResource(getItem(position).getMapImage());

        //Haetaan tarvittavat napit/värit näkyviin jos List Adapteri on korvannut vanhan positionin uudella
        return view;
    }



    private class Holder {
        ImageView mapImg;
        TextView mapName;
        FrameLayout startingSideFrame;
        Button CT;
        Button Tero;
        int buttonLocation;
    }
}



