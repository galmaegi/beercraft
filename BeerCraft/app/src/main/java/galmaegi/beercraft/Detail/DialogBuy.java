package galmaegi.beercraft.Detail;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 18.
 */
public class DialogBuy extends Dialog implements View.OnClickListener{

    NetworkImageView dialog_buy_productimage;
    TextView dialog_buy_productname;
    TextView dialog_buy_productstyle;
    TextView dialog_buy_abv;
    TextView dialog_buy_country;
    TextView dialog_buy_currentprice;
    TextView dialog_buy_counttext;
    TextView dialog_buy_totalprice;
    ImageButton dialog_buy_btn_buy;
    ImageButton dialog_buy_btn_no;
    public DialogBuy(Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_buy_product);
        //initialization views which located in activity_detail section4
        dialog_buy_productimage = (NetworkImageView)findViewById(R.id.dialog_buy_productimage);
        dialog_buy_productname = (TextView)findViewById(R.id.dialog_buy_productname);
        dialog_buy_productstyle = (TextView)findViewById(R.id.dialog_buy_productstyle);
        dialog_buy_abv = (TextView)findViewById(R.id.dialog_buy_abv);
        dialog_buy_country = (TextView)findViewById(R.id.dialog_buy_country);
        dialog_buy_currentprice = (TextView)findViewById(R.id.dialog_buy_currentprice);
        dialog_buy_counttext = (TextView)findViewById(R.id.dialog_buy_counttext);
        dialog_buy_totalprice = (TextView)findViewById(R.id.dialog_buy_totalprice);
        dialog_buy_btn_no = (ImageButton)findViewById(R.id.dialog_buy_btn_no);
        dialog_buy_btn_buy = (ImageButton)findViewById(R.id.dialog_buy_btn_buy);
        setSection();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_buy_btn_buy:
                addorderRequest();

                dismiss();
                break;
            case R.id.dialog_buy_btn_no:
                dismiss();
                break;
        }
    }
    public void setSection(){
        try {
            //to set dialog_buy_section
            ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
            dialog_buy_productimage.setImageUrl(DetailGlobalVar.currentObject.getString("proudctImage"), mImageLoader);
            dialog_buy_productname.setText(DetailGlobalVar.currentObject.getString("productName"));
            dialog_buy_productstyle.setText(DetailGlobalVar.currentObject.getString("style"));
            dialog_buy_abv.setText(DetailGlobalVar.currentObject.getString("strength") + "%, " + DetailGlobalVar.currentObject.getString("volume") + "ml");
            dialog_buy_country.setText(DetailGlobalVar.currentObject.getString("country"));
            dialog_buy_currentprice.setText(Detail_4_clicklistener.textFormating(DetailGlobalVar.price));
            dialog_buy_counttext.setText(DetailGlobalVar.count+"EA");
            dialog_buy_totalprice.setText(Detail_4_clicklistener.textFormating(DetailGlobalVar.count*DetailGlobalVar.price));
            dialog_buy_btn_buy.setOnClickListener(this);
            dialog_buy_btn_no.setOnClickListener(this);

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
    private void addorderRequest() {
        String urlJsonObj="";
        try {

            urlJsonObj = "http://beerexchange.dnktechnologies.com/wp-content/plugins/beer-rest-api/lib/class-wp-json-addorder.php?" +
                    "tableNo="+GlobalVar.currentTable+"&" +
                    "productID="+DetailGlobalVar.currentObject.getString("productID")+"&" +
                    "orderAmount="+DetailGlobalVar.count;
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    // Parsing json object response
                    // response will be a json object
                    String status = response.getString("status");
                    if(status.equals("1")){
                        Log.d("addorderRequest","Success");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("addorderRequest","Failed");
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
