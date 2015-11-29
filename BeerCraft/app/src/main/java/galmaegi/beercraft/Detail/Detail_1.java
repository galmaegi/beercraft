package galmaegi.beercraft.Detail;

import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 17.
 */
public class Detail_1 implements OnClickListener{
    private View parent_view;
    //<views  where included in section 1>
    TextView sum_beer_name;
    TextView sum_beer_style;
    TextView sum_beer_abv;
    TextView sum_price;
    TextView sum_range;
    ImageButton sum_wishlist;
    TextView sum_today_high;
    TextView sum_today_low;
    TextView sum_today_open;
    TextView sum_today_prev;
    TextView sum_summary_high;
    TextView sum_summary_low;
//    TextView sum_summary_avg;
//    TextView sum_summary_per;
    public Detail_1(View view){
        parent_view = view;

        sum_beer_name = (TextView)view.findViewById(R.id.sum_beer_name);
        sum_beer_style = (TextView)view.findViewById(R.id.sum_beer_style);
        sum_beer_abv = (TextView)view.findViewById(R.id.sum_beer_abv);
        sum_price = (TextView)view.findViewById(R.id.sum_price);
        sum_range = (TextView)view.findViewById(R.id.sum_range);
        sum_wishlist = (ImageButton)view.findViewById(R.id.sum_wishlist);
        sum_today_high = (TextView)view.findViewById(R.id.sum_today_high);
        sum_today_low = (TextView)view.findViewById(R.id.sum_today_low);
        sum_today_open = (TextView)view.findViewById(R.id.sum_today_open);
        sum_today_prev = (TextView)view.findViewById(R.id.sum_today_prev);
        sum_summary_high = (TextView)view.findViewById(R.id.sum_summary_high);
        sum_summary_low = (TextView)view.findViewById(R.id.sum_summary_low);

        sum_wishlist.setOnClickListener(this);
//        sum_summary_avg = (TextView)view.findViewById(R.id.sum_summary_avg);
//        sum_summary_per = (TextView)view.findViewById(R.id.sum_summary_per);
    }

    public void setSection(){
        try {
            //to set detail_4_section
            sum_beer_name.setText(DetailGlobalVar.currentObject.getString("productName"));
            sum_beer_style.setText(DetailGlobalVar.currentObject.getString("style"));
            sum_beer_abv.setText(DetailGlobalVar.currentObject.getString("strength") + "%, " + DetailGlobalVar.currentObject.getString("volume") + "ml");
            sum_price.setText(GlobalVar.setComma(DetailGlobalVar.price));
            setChanged(DetailGlobalVar.currentObject.getString("sellingPrice"), DetailGlobalVar.currentObject.getString("last"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
    public void setChanged(String sCurrentPrice,String sLastPrice){
        int CurrentPrice = isNullPrice(sCurrentPrice);
        int LastPrice = isNullPrice(sLastPrice);
        double percent = round(getChangePercent(CurrentPrice, LastPrice),2);
        int ChangedPrice = CurrentPrice - LastPrice;

        if(ChangedPrice>=0) {
            sum_range.setText(Html.fromHtml("<font color=#801f21> +" + ChangedPrice + " (<font color=#801f21> +" + percent + "%)"));
        }
        else {
            sum_range.setText(Html.fromHtml("<font color=#15a615> -" + ChangedPrice + " (<font color=#15a615> -"+percent+"%)"));
        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public double getChangePercent(int currentPrice, int LastPrice) {
        if(LastPrice==0 || currentPrice==0)
            return 0;
        return (double)currentPrice/(double)LastPrice;
    }
    public String isNullString(String input){
        String returnvalue = "NoData";

        if(input.length()!=0 && !input.equals("null"))
            returnvalue = input;

        return returnvalue;
    }
    public int isNullPrice(String input){
        int returnvalue = 0;

        if(input.length()!=0 && !input.equals("null"))
            returnvalue=Integer.parseInt(input);

        return returnvalue;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(parent_view.getContext(),"성공적으로 추가되었습니다.",Toast.LENGTH_SHORT).show();
    }
}
