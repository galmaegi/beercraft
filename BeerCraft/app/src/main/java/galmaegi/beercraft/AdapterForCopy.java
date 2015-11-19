package galmaegi.beercraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import galmaegi.beercraft.SideMenu.SidemenuIndexItem;
import galmaegi.beercraft.SideMenu.SidemenuIndexViewHolder;

/**
 * Created by jongsu on 2015. 11. 16..
 */
public class AdapterForCopy extends BaseAdapter {

    private Context mContext;
    private ArrayList<SidemenuIndexItem> items;

    public AdapterForCopy(Context context) {
        super();
        mContext = context;
        items = new ArrayList<>();
    }

    public AdapterForCopy(Context context, ArrayList items) {
        super();
        mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SidemenuIndexViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_home_listview1_item, null);

        } else {

        }

        return convertView;
    }
}