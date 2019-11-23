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
    private  Context mContext;
    private  int mResource;
    private boolean pickSide;

    MapListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Map> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        pickSide = false;
    }

    public void setPickSide(boolean pickSide) {
        this.pickSide = pickSide;
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
            view.setTag(holder);

        } else {
            holder = (Holder) view.getTag();
        }


        if(getItem(position).getMapName()!= null){
            holder.mapName.setText(getItem(position).getMapName());
        }
        else
        {
            Log.d("Applikaatio","map name was null");
        }

        if(getItem(position).getMapImage()!= null){
            holder.mapImg.setImageResource(getItem(position).getMapImage());
        }
        else
        {
            Log.d("Applikaatio","map image was null");
        }

        return view;
    }



    private class Holder {
        ImageView mapImg;
        TextView mapName;
    }

    @Override
    public boolean isEnabled(int position) {
        if(pickSide) {
            return false;
        } else {
            return true;
        }
    }
}



