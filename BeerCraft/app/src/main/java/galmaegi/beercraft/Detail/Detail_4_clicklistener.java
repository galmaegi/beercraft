package galmaegi.beercraft.Detail;

import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 16.
 */
public class Detail_4_clicklistener implements View.OnClickListener{


    TextView text_totalprice;
    TextView text_count;
    int price;
    private int totalcount;
    public Detail_4_clicklistener(TextView text_totalprice,TextView text_count,int price){
        this.text_totalprice = text_totalprice;
        this.text_count = text_count;
        this.price = price;
        totalcount = 1;

        text_totalprice.setText(textFormating(totalcount * price)+"");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_4_countminus:
                if(totalcount>1)
                    totalcount--;
                break;
            case R.id.detail_4_countplus:
                if(totalcount<10)
                    totalcount++;
                break;

        }
        text_count.setText(totalcount+"");
        text_totalprice.setText(textFormating(totalcount*price)+"");

    }

    public static String textFormating(int money){
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(money);

    }

}
