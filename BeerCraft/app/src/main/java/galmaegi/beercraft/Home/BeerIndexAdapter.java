package galmaegi.beercraft.Home;

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
import galmaegi.beercraft.common.BeerIndexItem;

/**
 * Created by jongsu on 2015. 11. 16..
 */
public class BeerIndexAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<BeerIndexItem> items;

    public BeerIndexAdapter(Context context) {
        super();
        mContext = context;
        items = new ArrayList<>();
    }

    public BeerIndexAdapter(Context context, ArrayList items) {
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
        BeerIndexViewHolder holder;

        if(convertView == null) {
            holder = new BeerIndexViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_home_listview1_item, null);

            holder.mAlert = (View) convertView.findViewById(R.id.v_alert);
            holder.mName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mCountry = (TextView) convertView.findViewById(R.id.tv_country);
            holder.mSellingPrice = (TextView) convertView.findViewById(R.id.tv_sellingPrice);
            holder.mDate = (TextView) convertView.findViewById(R.id.tv_date);
            holder.mRate = (TextView) convertView.findViewById(R.id.tv_rate);
            holder.mIncrease = (TextView) convertView.findViewById(R.id.tv_increase);

            convertView.setTag(holder);
        } else {
            holder = (BeerIndexViewHolder) convertView.getTag();
        }

        BeerIndexItem item = items.get(position);
        int curPrice = item.getSellingPrice();
        int prvPrice = item.getLast();
        int increase = item.getIncrease();
        double rate = item.getRate();

        holder.mName.setText(item.getBeerName());
        holder.mCountry.setText(item.getCountry());
        holder.mSellingPrice.setText(GlobalVar.setComma(curPrice));
        holder.mDate.setText(GlobalVar.DateToString(item.getModifyDate()));
        holder.mRate.setText(String.format("%.1f %%", rate));
        holder.mIncrease.setText(GlobalVar.setComma(increase));

        int color;
        if(increase < 0) {
            color = Color.parseColor("#05B005");
        } else if(increase == 0) {
            color = Color.parseColor("#6F6F6F");
        } else {
            color = Color.parseColor("#9D1819");
        }

        holder.mAlert.setBackgroundColor(color);
        holder.mRate.setTextColor(color);
        holder.mIncrease.setTextColor(color);

        return convertView;
    }
}
