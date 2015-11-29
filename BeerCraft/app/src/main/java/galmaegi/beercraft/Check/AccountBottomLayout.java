package galmaegi.beercraft.Check;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import galmaegi.beercraft.R;

public class AccountBottomLayout {
    LinearLayout layout;
    TextView mCost;
    TextView mBuy;
    TextView mPER;
    TextView mProfit;
    TextView mTotal;

    public AccountBottomLayout(View view) {
        layout = (LinearLayout) view.findViewById(R.id.ll_account);
        mCost = (TextView) view.findViewById(R.id.tv_account_cost);
        mBuy = (TextView) view.findViewById(R.id.tv_account_buy);
        mPER = (TextView) view.findViewById(R.id.tv_account_per);
        mProfit = (TextView) view.findViewById(R.id.tv_account_profit);
        mTotal = (TextView) view.findViewById(R.id.tv_account_total);
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
