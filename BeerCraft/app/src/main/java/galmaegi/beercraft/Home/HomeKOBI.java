package galmaegi.beercraft.Home;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 29.
 */
public class HomeKOBI {
    View parent_view;

    View KOBI;
    LinearLayout KOBI_back;
    TextView KOBI_value;

    View DBI;
    LinearLayout DBI_back;
    TextView DBI_value;

    View BBI;
    LinearLayout BBI_back;
    TextView BBI_value;

    double DBIvalue;
    double BBIvalue;

    Handler handleHomeKOBI = new Handler(new android.os.Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            getBBIFromWeb();
            getDBIFromWeb();
            return false;
        }
    });

    public HomeKOBI(View parent_view){
        this.parent_view = parent_view;

        DBIvalue = 0;
        BBIvalue = 0;

        KOBI = (View) parent_view.findViewById(R.id.KOBI);
        KOBI_back = (LinearLayout)KOBI.findViewById(R.id.KOBI_back);
        KOBI_value = (TextView)KOBI.findViewById(R.id.KOBI_value);

        DBI = (View) parent_view.findViewById(R.id.DBI);
        DBI_back = (LinearLayout)DBI.findViewById(R.id.KOBI_back);
        DBI_value = (TextView)DBI.findViewById(R.id.KOBI_value);

        BBI = (View) parent_view.findViewById(R.id.BBI);
        BBI_back = (LinearLayout)BBI.findViewById(R.id.KOBI_back);
        BBI_value = (TextView)BBI.findViewById(R.id.KOBI_value);

        getBBIFromWeb();
        getDBIFromWeb();
    }

    public void setDBIData(){
        DBI_value.setText(String.format("%.2f",DBIvalue));
        KOBI_value.setText(String.format("%.2f", DBIvalue + BBIvalue));
    }
    public void setBBIData(){
        BBI_value.setText(String.format("%.2f",BBIvalue));
        KOBI_value.setText(String.format("%.2f", DBIvalue + BBIvalue));
    }



    private void getBBIFromWeb() {
        final String testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-bottled_beer_day_price.php";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            //set global object
                            JSONObject jsonObject = (JSONObject)response.get(0);
                            BBIvalue = validKOBI(jsonObject.getString("Todays_Order_Total"));
                            setBBIData();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("BottomBar", "Error: " + error.getMessage());
                // hide the progress dialog
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
    private void getDBIFromWeb() {
        final String testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-draft_beer_day_price.php";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            //set global object
                            JSONObject jsonObject = (JSONObject)response.get(0);
                            DBIvalue = validKOBI(jsonObject.getString("Todays_Order_Total"));
                            setDBIData();

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("BottomBar", "Error: " + error.getMessage());
                // hide the progress dialog
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private double validKOBI(String input) {
        double returnvalue = 0;
        if(!input.toLowerCase().equals("null")&&input.length()!=0)
            returnvalue = Double.parseDouble(input);

        return returnvalue;
    }
}
