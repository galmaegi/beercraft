package galmaegi.beercraft;

import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import galmaegi.beercraft.CustomTimer.CustomTimer;


/**
 * Created by root on 15. 11. 24.
 */
public class BottomBar {

    private TextView bottom_bar_text;
    ArrayList<BottomBarItem> bottomBarItems = new ArrayList<>();
    ArrayList<String> bottomData = new ArrayList<>();

    public Handler handleBottomBar;
    CustomTimer customTimer;

    private String currentText="";
    public BottomBar(View v){
        getDataFromWeb();
        bottom_bar_text = (TextView)v.findViewById(R.id.bottom_bar_text);
        bottom_bar_text.setSelected(true);

        handleBottomBar = new Handler(new android.os.Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                getDataFromWeb();
                return false;
            }
        });
        customTimer = new CustomTimer(60*3*1000,60*3*1000,handleBottomBar);
        customTimer.start();
    }

    private void getDataFromWeb() {
        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-get-bottom.php";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            //set global object
                            bottomBarItems.clear();
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = (JSONObject)response.get(i);
                                BottomBarItem insertData = new BottomBarItem();

                                insertData.setKorName(jsonObject.getString("productName"));
                                insertData.setEngName(jsonObject.getString("englishName"));
                                insertData.setCurrentPrice(jsonObject.getString("last"));
                                insertData.setLastPrice(jsonObject.getString("sellingPrice"));

                                bottomBarItems.add(insertData);

                            }

                            makeData();
                            printData();
                            createTimer();

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
    public void createTimer(){
        if (customTimer != null)
            customTimer.cancel();
        long time = currentText.length()/3*1000;
        customTimer = new CustomTimer(time,time,handleBottomBar);
    }
    public void makeData(){
        bottomData.clear();
        for(int i = 0; i < bottomBarItems.size(); i++) {
            BottomBarItem tempItem = bottomBarItems.get(i);
            String result = "";
            if(tempItem.getCurrentPrice()-tempItem.getLastPrice()>0)
                result = String.format("%10s <font color=#801f21>▲ %.1f (%s)</font>",tempItem.getKorName(),tempItem.getChangePercent(),GlobalVar.setComma(tempItem.getCurrentPrice()));
            else if(tempItem.getCurrentPrice()-tempItem.getLastPrice()==0)
                result = String.format("%10s <font color=#6f6f6f>〓 %.1f (%s)</font>",tempItem.getKorName(),tempItem.getChangePercent(),GlobalVar.setComma(tempItem.getCurrentPrice()));
            else
                result = String.format("%10s <font color=#15a615>▼ %.1f (%s)</font>",tempItem.getKorName(),tempItem.getChangePercent(),GlobalVar.setComma(tempItem.getCurrentPrice()));
            bottomData.add(result);
        }
    }
    public void printData(){

        for(int i = 0; i < bottomData.size(); i++){
            currentText+=bottomData.get(i)+"&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        bottom_bar_text.setText(Html.fromHtml(currentText));
    }

    public class BottomBarItem{
        private String korName;
        private String engName;
        private int currentPrice;
        private int lastPrice;
        private double changePercent;


        public void setKorName(String korName) {
            this.korName = isNullString(korName);
        }

        public void setEngName(String engName) {
            this.engName = isNullString(engName);
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = isNullPrice(currentPrice);
        }

        public void setLastPrice(String lastPrice) {
            this.lastPrice = isNullPrice(lastPrice);
        }

        public void setChangePercent(double changePercent) {
            this.changePercent = changePercent;
        }

        public String getKorName() {
            return korName;
        }

        public String getEngName() {
            return engName;
        }

        public int getCurrentPrice() {
            return currentPrice;
        }

        public int getLastPrice() {
            if(lastPrice==0)
                return currentPrice;
            return lastPrice;
        }

        public double getChangePercent() {
            return ((double)getCurrentPrice()-getLastPrice())/(double)getLastPrice()*100;
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
    }

}
