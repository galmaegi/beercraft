package galmaegi.beercraft.Detail;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.astuetz.PagerSlidingTabStrip;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.LineChart.CustomLineChart;
import galmaegi.beercraft.R;
import galmaegi.beercraft.RadarChart.CustomRadarView;

/**
 * Created by root on 15. 11. 13.
 */
public class FragmentDetail extends Fragment{

    Context parent_context;
    int price;

    private static String TAG = "DETAILACTIVITY";
    //set font
    private Typeface tf;

    //to set LineChartView
    LineChart linechart;

    //to set RadarChart view
    RadarChart detailRadar;
    CustomRadarView customradar;


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
    TextView sum_summary_avg;
    TextView sum_summary_per;






    //<views where included in section 4>
    ImageView detail_4_img_updown;
    NetworkImageView detail_4_productimage;
    TextView detail_4_productname;
    TextView detail_4_productstyle;
    TextView detail_4_abv;
    TextView detail_4_changepercent;
    TextView detail_4_changeprice;
    TextView detail_4_currentprice;
    ImageButton detail_4_countminus;
    TextView detail_4_counttext;
    ImageButton detail_4_countplus;
    TextView detail_4_totalprice;
    ImageButton detail_4_btn_buy;
    //</sector4>

    //<views where included in section 5>
    ViewPager viewpager;
    PagerSlidingTabStrip tabStrip;
    //</section5>

    public FragmentDetail(Context parent_context){
        this.parent_context = parent_context;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Line Chart View
        linechart = (LineChart)view.findViewById(R.id.BeerDetailLine);
        new CustomLineChart(getContext(),linechart,Typeface.createFromAsset(getActivity().getAssets(),"NanumGothic_Coding_Bold.ttf"));

        //Radar Chart View
        detailRadar = (RadarChart)view.findViewById(R.id.BeerDetailRadar);
        customradar = new CustomRadarView(getContext(),detailRadar);

        //initialization views which located in activity_detail section1
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
        sum_summary_avg = (TextView)view.findViewById(R.id.sum_summary_avg);
        sum_summary_per = (TextView)view.findViewById(R.id.sum_summary_per);

        //initialization views which located in activity_detail section4
        detail_4_img_updown = (ImageView)view.findViewById(R.id.detail_4_img_updown);
        detail_4_productimage = (NetworkImageView)view.findViewById(R.id.detail_4_productimage);
        detail_4_productname = (TextView)view.findViewById(R.id.detail_4_productname);
        detail_4_productstyle = (TextView)view.findViewById(R.id.detail_4_productstyle);
        detail_4_abv = (TextView)view.findViewById(R.id.detail_4_abv);
        detail_4_changepercent = (TextView)view.findViewById(R.id.detail_4_changepercent);
        detail_4_changeprice = (TextView)view.findViewById(R.id.detail_4_changeprice);
        detail_4_currentprice = (TextView)view.findViewById(R.id.detail_4_currentprice);
        detail_4_countminus = (ImageButton)view.findViewById(R.id.detail_4_countminus);
        detail_4_counttext = (TextView)view.findViewById(R.id.detail_4_counttext);
        detail_4_countplus = (ImageButton)view.findViewById(R.id.detail_4_countplus);
        detail_4_totalprice = (TextView)view.findViewById(R.id.detail_4_totalprice);
        detail_4_btn_buy = (ImageButton)view.findViewById(R.id.detail_4_btn_buy);






        //get section 5
        viewpager = (ViewPager)view.findViewById(R.id.detail_5_viewpager);
        tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.detail_5_tabs);

        //get json data
        getDetailjson();




    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.detail_core, container, false);
    }

    private void getDetailjson() {

        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-get-selected-order-item.php?productID=23";
//        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            JSONObject currentobject = (JSONObject)response.get(0);

                            //to set detail_1_section

                            //to set detail_4_section
                            detail_4_productname.setText(currentobject.getString("productName"));
                            detail_4_productstyle.setText(currentobject.getString("style"));
                            detail_4_abv.setText(currentobject.getString("strength")+"%, "+currentobject.getString("volume")+"ml");
                            price = currentobject.getInt("sellingPrice");
                            detail_4_currentprice.setText(Detail_4_clicklistener.textFormating(price)+"");

                            Detail_4_clicklistener detail_4_clicklistener = new Detail_4_clicklistener(detail_4_totalprice,detail_4_counttext,price);
                            detail_4_countplus.setOnClickListener(detail_4_clicklistener);
                            detail_4_countminus.setOnClickListener(detail_4_clicklistener);

//                            GetImage getimage = new GetImage(detail_4_productimage);
//                            getimage.getImagebyurl(currentobject.getString("proudctImage"));
                            ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
                            detail_4_productimage.setImageUrl(currentobject.getString("proudctImage"),mImageLoader);

                            viewpager.setAdapter(new Detail_5_Page_Adapter(getFragmentManager(),currentobject.getString("tastingNote"),currentobject.getString("beerStory")));
                            tabStrip.setViewPager(viewpager);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(parent_context,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(parent_context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

}
