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
    public Detail_4_clicklistener(TextView text_totalprice,TextView text_count,int price){
        this.text_totalprice = text_totalprice;
        this.text_count = text_count;
        this.price = price;
        DetailGlobalVar.count = 1;

        text_totalprice.setText(textFormating(DetailGlobalVar.count * price)+"");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_4_countminus:
                if(DetailGlobalVar.count>1)
                    DetailGlobalVar.count--;
                break;
            case R.id.detail_4_countplus:
                if(DetailGlobalVar.count<10)
                    DetailGlobalVar.count++;
                break;

        }
        text_count.setText(DetailGlobalVar.count+"");
        text_totalprice.setText(textFormating(DetailGlobalVar.count*price)+"");

    }

    public static String textFormating(int money){
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(money);

    }

}
