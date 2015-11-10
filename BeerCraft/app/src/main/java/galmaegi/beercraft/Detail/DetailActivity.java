package galmaegi.beercraft.Detail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.BaseActivity;
import galmaegi.beercraft.Custom.RadarMarkerView;
import galmaegi.beercraft.R;

/**
 * Created by kyoungmin on 2015-10-24.
 */
public class DetailActivity extends BaseActivity {
    private static String TAG = "DETAILACTIVITY";
    //set font
    private Typeface tf;

    //to set radarview
    RadarChart detailRadar;

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



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //BaseActivity에서 Activity를 띄워주기 위한 작업
        super.currentContext = this;

        //공통뷰들을 찾아주기 위한 작업을 수행해야함
        findViewById(R.id.detail_left_side).findViewById(R.id.btn_home).setOnClickListener(this);
        getDetailjson();
        detailRadar = (RadarChart)findViewById(R.id.BeerDetailRadar);


        //initialization views which located in activity_detail sector4
        detail_4_img_updown = (ImageView)findViewById(R.id.detail_4_img_updown);
        detail_4_productimage = (ImageView)findViewById(R.id.detail_4_productimage);
        detail_4_productname = (TextView)findViewById(R.id.detail_4_productname);
        detail_4_productstyle = (TextView)findViewById(R.id.detail_4_productstyle);
        detail_4_abv = (TextView)findViewById(R.id.detail_4_abv);
        detail_4_changepercent = (TextView)findViewById(R.id.detail_4_changepercent);
        detail_4_changeprice = (TextView)findViewById(R.id.detail_4_changeprice);
        detail_4_currentprice = (TextView)findViewById(R.id.detail_4_currentprice);
        detail_4_countminus = (ImageButton)findViewById(R.id.detail_4_countminus);
        detail_4_counttext = (TextView)findViewById(R.id.detail_4_counttext);
        detail_4_countplus = (ImageButton)findViewById(R.id.detail_4_countplus);
        detail_4_totalprice = (TextView)findViewById(R.id.detail_4_totalprice);
        detail_4_btn_buy = (ImageButton)findViewById(R.id.detail_4_btn_buy);


        //set font
//        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        //set radar values
        detailRadar.setDescription("");
        detailRadar.setWebLineWidth(1.5f);
        detailRadar.setWebLineWidthInner(0.75f);
        detailRadar.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        RadarMarkerView mv = new RadarMarkerView(this, R.layout.radar_marker);

        // set the marker to the chart
        detailRadar.setMarkerView(mv);
        setData();

    }
    private String[] mParties = new String[] {
            "CREAMY","FLAVORY","PURE","BITERNESS","SWEETNESS","CLUMSY"
    };
    public void setData() {

        float mult = 150;
        int cnt = 6;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < cnt; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
        }

        for (int i = 0; i < cnt; i++) {
            yVals2.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < cnt; i++)
            xVals.add(mParties[i % mParties.length]);

        RadarDataSet set1 = new RadarDataSet(yVals1, "Set 1");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(yVals2, "Set 2");
        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setDrawFilled(true);
        set2.setLineWidth(2f);

        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(xVals, sets);
//        data.setValueTypeface(tf);
        data.setValueTextSize(8f);
        data.setDrawValues(false);

        detailRadar.setData(data);

        detailRadar.invalidate();
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
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

}
