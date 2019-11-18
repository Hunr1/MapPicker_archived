package ryhma4.mappicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;



//custom adapteri list view olio varten
public class MapListAdapter extends ArrayAdapter<Map> {

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
        Integer mapImage = getItem(position).getMapImage();
        String mapName = getItem(position).getMapName();

        Map map = new Map(mapName,mapImage);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView mapNameTextView= convertView.findViewById(R.id.add_match_listview_textEdit);
        ImageView mapImgImgView= convertView.findViewById(R.id.add_match_listview_image);

        mapNameTextView.setText(mapName);
        mapImgImgView.setImageResource(mapImage);
        return convertView;
    }
}
