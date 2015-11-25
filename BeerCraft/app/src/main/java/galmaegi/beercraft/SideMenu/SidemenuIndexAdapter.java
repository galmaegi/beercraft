package galmaegi.beercraft.SideMenu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

/**
 * Created by jongsu on 2015. 11. 16..
 */
public class SidemenuIndexAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<SidemenuIndexItem> items;

    public SidemenuIndexAdapter(Context context) {
        super();
        mContext = context;
        items = new ArrayList<>();
    }

    public SidemenuIndexAdapter(Context context, ArrayList items) {
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
            holder = new SidemenuIndexViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_sidemenu_listview_item, null);

            holder.mAlert = convertView.findViewById(R.id.v_alert);
            holder.mName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mPrice = (TextView) convertView.findViewById(R.id.tv_sellingPrice);

            convertView.setTag(holder);
        } else {
            holder = (SidemenuIndexViewHolder) convertView.getTag();
        }

        SidemenuIndexItem item = items.get(position);

        holder.mName.setText(item.getProductName());
        holder.mPrice.setText(GlobalVar.setComma(item.getPrice()));

        holder.mAlert.setBackgroundColor(Color.parseColor("#05B005"));

        return convertView;
    }
}