//package galmaegi.beercraft.Detail;
//
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.charts.RadarChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.RadarData;
//import com.github.mikephil.charting.data.RadarDataSet;
//import com.github.mikephil.charting.utils.ColorTemplate;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import galmaegi.beercraft.AppController;
//
//import galmaegi.beercraft.RadarVIew.CustomRadarView;
//import galmaegi.beercraft.RadarVIew.RadarMarkerView;
//import galmaegi.beercraft.R;
//
///**
// * Created by kyoungmin on 2015-10-24.
// */
//public class DetailActivity  {
//    private static String TAG = "DETAILACTIVITY";
//    //set font
//    private Typeface tf;
//
//    //to set LineChartView
//    LineChart linechart;
//
//    //to set RadarChart view
//    RadarChart detailRadar;
//    CustomRadarView customradar;
//
//    //<views where included in sector 4>
//    ImageView detail_4_img_updown;
//    ImageView detail_4_productimage;
//    TextView detail_4_productname;
//    TextView detail_4_productstyle;
//    TextView detail_4_abv;
//    TextView detail_4_changepercent;
//    TextView detail_4_changeprice;
//    TextView detail_4_currentprice;
//    ImageButton detail_4_countminus;
//    TextView detail_4_counttext;
//    ImageButton detail_4_countplus;
//    TextView detail_4_totalprice;
//    ImageButton detail_4_btn_buy;
//    //</sector4>
//
//
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
//        //BaseActivity에서 Activity를 띄워주기 위한 작업
//        super.currentContext = this;
//
//        //공통뷰들을 찾아주기 위한 작업을 수행해야함
////        findViewById(R.id.detail_left_side).findViewById(R.id.btn_home).setOnClickListener(this);
//        getDetailjson();
//
//        //Line Chart View
//
//        //Radar Chart View
//        detailRadar = (RadarChart)findViewById(R.id.BeerDetailRadar);
//        customradar = new CustomRadarView(this,detailRadar);
//
//
//        //initialization views which located in activity_detail sector4
//        detail_4_img_updown = (ImageView)findViewById(R.id.detail_4_img_updown);
//        detail_4_productimage = (ImageView)findViewById(R.id.detail_4_productimage);
//        detail_4_productname = (TextView)findViewById(R.id.detail_4_productname);
//        detail_4_productstyle = (TextView)findViewById(R.id.detail_4_productstyle);
//        detail_4_abv = (TextView)findViewById(R.id.detail_4_abv);
//        detail_4_changepercent = (TextView)findViewById(R.id.detail_4_changepercent);
//        detail_4_changeprice = (TextView)findViewById(R.id.detail_4_changeprice);
//        detail_4_currentprice = (TextView)findViewById(R.id.detail_4_currentprice);
//        detail_4_countminus = (ImageButton)findViewById(R.id.detail_4_countminus);
//        detail_4_counttext = (TextView)findViewById(R.id.detail_4_counttext);
//        detail_4_countplus = (ImageButton)findViewById(R.id.detail_4_countplus);
//        detail_4_totalprice = (TextView)findViewById(R.id.detail_4_totalprice);
//        detail_4_btn_buy = (ImageButton)findViewById(R.id.detail_4_btn_buy);
//
//
//        //set font
////        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//
//
//
//    }
//
//    private void getDetailjson() {
//
//        final String testURL = "http://beerexchange.dnktechnologies.com/wp-content/plugins/beer-rest-api/lib/class-wp-json-draft.php";
//
//        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
//                new Response.Listener<JSONArray>() {
//
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    // Parsing json object response
//                    // response will be a json object
//                    JSONObject currentobject = (JSONObject)response.get(0);
//                    detail_4_productname.setText(currentobject.getString("productName"));
//                    detail_4_productstyle.setText(currentobject.getString("style"));
//                    detail_4_abv.setText(currentobject.getString("strength")+"%, "+currentobject.getString("volume")+"ml");
//                    detail_4_currentprice.setText(currentobject.getString("sellingPrice"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),
//                            "Error: " + e.getMessage(),
//                            Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_SHORT).show();
//                // hide the progress dialog
//
//            }
//        });
//
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(jsonObjReq);
//    }
//
//}
