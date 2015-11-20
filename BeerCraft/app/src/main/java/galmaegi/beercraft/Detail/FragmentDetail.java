package galmaegi.beercraft.Detail;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.astuetz.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

/**
 * Created by root on 15. 11. 13.
 */
public class FragmentDetail extends Fragment {

    Context parent_context;
    int price;
    private JSONObject currentObject;

    private static String TAG = "DETAILACTIVITY";
    //set font
    private Typeface tf;

    //to set section2
    ViewPager detail_2_viewpager;
    PagerSlidingTabStrip detail_2_tabstrip;

    //<views where included in section1>
    Detail_1 detail_1;

    //<views where included in section 3>
    Detail_3 detail_3;

    //<views where included in section 4>
    Detail_4 detail_4;

    //<views where included in section 5>
    ViewPager detail_5_viewpager;
    PagerSlidingTabStrip detail_5_tabstrip;

    int currentItemID ;


    public FragmentDetail(Context parent_context, BeerIndexItem item){
        this.parent_context = parent_context;
        this.currentItemID = item.getProductID();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Line Chart View
//        linechart = (LineChart)view.findViewById(R.id.BeerDetailLine);
//        new CustomLineChart(getContext(),linechart, GlobalVar.NanumGothic_Bold);

        //initialization views which located in activity_detail section1
        detail_1 = new Detail_1(view);

        //initialization views which located in activity_detail section3
        detail_3 = new Detail_3(view);

        //initialization views which located in activity_detail section4
        detail_4 = new Detail_4(view);
        //get section 2
        detail_2_viewpager = (ViewPager) view.findViewById(R.id.detail_2_viewpager);
        detail_2_tabstrip = (PagerSlidingTabStrip) view.findViewById(R.id.detail_2_tabs);


        //get section 5
        detail_5_viewpager = (ViewPager) view.findViewById(R.id.detail_5_viewpager);
        detail_5_tabstrip = (PagerSlidingTabStrip) view.findViewById(R.id.detail_5_tabs);

        //get json data
        getDetailjson();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_core, container, false);
    }

    private void getDetailjson() {
        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-get-selected-order-item.php?productID="+currentItemID;
//        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            //set global object
                            DetailGlobalVar.currentObject = (JSONObject) response.get(0);
                            DetailGlobalVar.price = DetailGlobalVar.currentObject.getInt("sellingPrice");
                            //set detail section 1
                            detail_1.setSection();

                            //set detail section 2
                            //set section 5
                            detail_2_viewpager.setAdapter(new Detail_2_Page_Adapter(getChildFragmentManager(), "groupid"));
                            detail_2_tabstrip.setViewPager(detail_2_viewpager);

                            //set detail section 3
                            detail_3.setSection();

                            //set detail section 4
                            detail_4.setSection();
                            //to set detail_1_section

                            //set section 5
                            detail_5_viewpager.setAdapter(new Detail_5_Page_Adapter(getChildFragmentManager(), DetailGlobalVar.currentObject.getString("tastingNote"), DetailGlobalVar.currentObject.getString("beerStory")));
                            detail_5_tabstrip.setViewPager(detail_5_viewpager);


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
