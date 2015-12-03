package galmaegi.beercraft.Check;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

public class CheckIndexAdapter extends BaseAdapter implements View.OnClickListener{

    private Context mContext;
    private ArrayList<CheckIndexItem> items;
    boolean isOnCheckbox;
    private int position;

    public CheckIndexAdapter(Context context) {
        super();
        mContext = context;
        items = new ArrayList<>();
    }

    public CheckIndexAdapter(Context context, ArrayList items, boolean isOnCheckbox) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CheckIndexViewHolder holder;


        if(convertView == null) {
            this.position = position;
            holder = new CheckIndexViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_check_listview_item, null);

            holder.mAlert = convertView.findViewById(R.id.v_alert);
            holder.mName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mCode = (TextView) convertView.findViewById(R.id.tv_code);
            holder.mCostPrice = (TextView) convertView.findViewById(R.id.tv_cost_price);
            holder.mOrderAmount = (TextView) convertView.findViewById(R.id.tv_order_amount);
            holder.mRate = (TextView) convertView.findViewById(R.id.tv_rate);
            holder.mIncrease = (TextView) convertView.findViewById(R.id.tv_increase);
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.get(position).setIsclicked(!items.get(position).getisclicked());
                }
            });

            if(!isOnCheckbox) {
                holder.mCheckBox.setVisibility(View.INVISIBLE);
            }

            convertView.setTag(holder);
        } else {
            holder = (CheckIndexViewHolder) convertView.getTag();
        }

        CheckIndexItem item = items.get(position);

        holder.mName.setText(item.getProductName());
        holder.mCostPrice.setText(GlobalVar.setComma(item.getCostPrice()));
        holder.mOrderAmount.setText(GlobalVar.setComma(item.getDiscountPrice()));

        int increase;
        try {
            increase = item.getCostPrice() - item.getDiscountPrice();
        }
        catch (ArithmeticException e){
            increase = 0;
        }
        double rate = GlobalVar.Division(increase,item.getCostPrice())*100;

        holder.mRate.setText(String.format("%.2f %%", rate));
        holder.mIncrease.setText(GlobalVar.setComma(increase * item.getQty()));


        int color;
        if(increase < 0) {
            color = Color.parseColor("#9D1819");
        } else if(increase == 0) {
            color = Color.parseColor("#6F6F6F");
        } else {
            color = Color.parseColor("#05B005");
        }

        holder.mAlert.setBackgroundColor(color);
        holder.mRate.setTextColor(color);
        holder.mIncrease.setTextColor(color);

        if(item.getisclicked()){
            holder.mCheckBox.setChecked(true);
        }
        else{
            holder.mCheckBox.setChecked(false);
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {
    }
}
