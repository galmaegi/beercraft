package galmaegi.beercraft.Detail;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;

import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 17.
 */
public class Detail_1 {
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
//        sum_summary_avg = (TextView)view.findViewById(R.id.sum_summary_avg);
//        sum_summary_per = (TextView)view.findViewById(R.id.sum_summary_per);
    }

    public void setSection(){
        try {
            //to set detail_4_section
            sum_beer_name.setText(DetailGlobalVar.currentObject.getString("productName"));
            sum_beer_style.setText(DetailGlobalVar.currentObject.getString("style"));
            sum_beer_abv.setText(DetailGlobalVar.currentObject.getString("strength") + "%, " + DetailGlobalVar.currentObject.getString("volume") + "ml");
            sum_price.setText(Detail_4_clicklistener.textFormating(DetailGlobalVar.price));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }


}
