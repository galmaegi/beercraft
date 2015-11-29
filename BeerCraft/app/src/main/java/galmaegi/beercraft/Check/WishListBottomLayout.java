package galmaegi.beercraft.Check;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import galmaegi.beercraft.R;

public class WishListBottomLayout {
    LinearLayout layout;
    TextView mPER;
    TextView mProfit;
    TextView mTotal;
    Button mDelete;
    Button mBuy;

    public WishListBottomLayout(View view) {
        layout = (LinearLayout) view.findViewById(R.id.ll_wish);
        mPER = (TextView) view.findViewById(R.id.tv_wish_per);
        mProfit = (TextView) view.findViewById(R.id.tv_wish_profit);
        mTotal = (TextView) view.findViewById(R.id.tv_wish_total);
        mDelete = (Button) view.findViewById(R.id.bt_delete);
        mBuy = (Button) view.findViewById(R.id.bt_buy);
    }

    public void setVisible(boolean visible) {
        if(visible) {
            layout.setVisibility(View.VISIBLE);
            layout.setEnabled(true);
        } else {
            layout.setVisibility(View.INVISIBLE);
            layout.setEnabled(false);
        }
    }
}
