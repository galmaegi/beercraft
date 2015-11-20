package galmaegi.beercraft.SideMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

public class SidemenuAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<BeerIndexItem> items;

    public SidemenuAdapter(Context context) {
        super();
        mContext = context;
        items = new ArrayList<>();
    }

    public SidemenuAdapter(Context context, ArrayList items) {
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
        SidemenuViewHolder holder;

        if(convertView == null) {
            holder = new SidemenuViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_beer_listview2_item, null);
            holder.mSellingPrice = (TextView) convertView.findViewById(R.id.tv_sellingPrice);
            holder.mDate = (TextView) convertView.findViewById(R.id.tv_date);

            convertView.setTag(holder);
        } else {
            holder = (SidemenuViewHolder) convertView.getTag();
        }

        BeerIndexItem item = items.get(position);

        holder.mName.setText(item.getEnglishName());
        holder.mSellingPrice.setText(String.valueOf(item.getSellingPrice()));
        holder.mDate.setText(item.getModifyDate().toString());
//
//        int color;
//        if(item.getRateBeerScore() < 0) {
//            color = Color.parseColor("#9D1819");
//        } else if(item.getRateBeerScore() == 0) {
//            color = Color.parseColor("#6F6F6F");
//        } else {
//            color = Color.parseColor("#05B005");
//        }
//
//        holder.mAlert.setBackgroundColor(color);

        return convertView;
    }
}
