package galmaegi.beercraft.Check;

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

public class CheckAccountAdapter extends BaseAdapter implements View.OnClickListener{

    private Context mContext;
    private ArrayList<CheckIndexItem> items;
    boolean isOnCheckbox;

    public CheckAccountAdapter(Context context) {
        super();
        mContext = context;
        items = new ArrayList<>();
    }

    public CheckAccountAdapter(Context context, ArrayList items, boolean isOnCheckbox) {
        super();
        mContext = context;
        this.items = items;
        this.isOnCheckbox = isOnCheckbox;
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
        CheckAccountViewHolder holder;

        if(convertView == null) {
            holder = new CheckAccountViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_check_listview_account_item, null);

            holder.mAlert = convertView.findViewById(R.id.v_alert);
            holder.mName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mCode = (TextView) convertView.findViewById(R.id.tv_code);
            holder.mLast = (TextView) convertView.findViewById(R.id.tv_cost_last);
            holder.mSale = (TextView) convertView.findViewById(R.id.tv_cost_sale);
            holder.mQty = (TextView)convertView.findViewById(R.id.tv_cost_qty);
            holder.mBuy = (TextView) convertView.findViewById(R.id.tv_order_buy);

            holder.mRate = (TextView) convertView.findViewById(R.id.tv_rate);
            holder.mIncrease = (TextView) convertView.findViewById(R.id.tv_increase);



            convertView.setTag(holder);
        } else {
            holder = (CheckAccountViewHolder) convertView.getTag();
        }

        CheckIndexItem item = items.get(position);

        holder.mName.setText(item.getProductName());
        holder.mLast.setText(GlobalVar.setComma(item.getCostPrice()));
        holder.mSale.setText(GlobalVar.setComma(item.getDiscountPrice()));
        holder.mQty.setText(item.getQty()+"");
        holder.mBuy.setText(GlobalVar.setComma(item.getOrderAmount()));
        int increase;
        try {
            increase = item.getOrderAmount() / item.getQty() - item.getCostPrice();
        }
        catch (ArithmeticException e){
            increase = 0;
        }
        double rate = GlobalVar.Division(increase, item.getCostPrice());
        holder.mIncrease.setText(GlobalVar.setComma(increase*item.getQty()));
        holder.mRate.setText(String.format("%.2f %%", rate));


        int color = Color.parseColor("#6F6F6F");
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

    @Override
    public void onClick(View v) {


    }
}
