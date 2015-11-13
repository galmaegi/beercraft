package galmaegi.beercraft.Detail;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.R;
import galmaegi.beercraft.RadarVIew.CustomRadarView;

/**
 * Created by root on 15. 11. 13.
 */
public class FragmentDetail extends Fragment {

    Context parent_context;

    private static String TAG = "DETAILACTIVITY";
    //set font
    private Typeface tf;

    //to set LineChartView
    LineChart linechart;

    //to set RadarChart view
    RadarChart detailRadar;
    CustomRadarView customradar;

    //<views where included in sector 4>
    ImageView detail_4_img_updown;
    ImageView detail_4_productimage;
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

    public FragmentDetail(Context parent_context){
        this.parent_context = parent_context;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Line Chart View

        //Radar Chart View
        detailRadar = (RadarChart)view.findViewById(R.id.BeerDetailRadar);
        customradar = new CustomRadarView(parent_context,detailRadar);


        //initialization views which located in activity_detail sector4
        detail_4_img_updown = (ImageView)view.findViewById(R.id.detail_4_img_updown);
        detail_4_productimage = (ImageView)view.findViewById(R.id.detail_4_productimage);
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

        getDetailjson();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.detail_core, container, false);
    }

    private void getDetailjson() {

        final String testURL = "http://beerexchange.dnktechnologies.com/wp-content/plugins/beer-rest-api/lib/class-wp-json-draft.php";

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            JSONObject currentobject = (JSONObject)response.get(0);
                            detail_4_productname.setText(currentobject.getString("productName"));
                            detail_4_productstyle.setText(currentobject.getString("style"));
                            detail_4_abv.setText(currentobject.getString("strength")+"%, "+currentobject.getString("volume")+"ml");
                            detail_4_currentprice.setText(currentobject.getString("sellingPrice"));

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
