package galmaegi.beercraft.LineChart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.widget.SeekBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.Detail.DetailGlobalVar;
import galmaegi.beercraft.R;
import galmaegi.beercraft.RadarChart.RadarMarkerView;

/**
 * Created by root on 15. 11. 15.
 */
public class CustomLineChart implements SeekBar.OnSeekBarChangeListener,
        OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart mChart;
    int cnt;

    String times[] = new String[]{
            "17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30","00:00","00:30","01:00","01:30"
    };
    ArrayList<Integer> dataArray = new ArrayList<>();
    public CustomLineChart(Context context,LineChart mChart,Typeface tf,int cnt){
        this.mChart = mChart;
        if(cnt==20) {
            this.cnt = 18;
            getMgt("ForRandom");
        }
        else {
            this.cnt = cnt;
            getMgt();
        }




        RadarMarkerView mv = new RadarMarkerView(context, R.layout.radar_marker);
        mChart.setMarkerView(mv);

        mChart.setDescription("");
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(tf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setTextColor(Color.WHITE);

        mChart.getAxisLeft().setEnabled(false);

        YAxis leftAxis = mChart.getAxisRight();
        leftAxis.setTypeface(tf);
        leftAxis.setLabelCount(5, false);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMinValue(4000f);
        leftAxis.setStartAtZero(false);
        leftAxis.setLabelCount(4, true);
    }
    public void setData(){
        mChart.setData(generateDataLine(cnt));
        mChart.getAxisRight().resetAxisMinValue();
        mChart.getAxisRight().setAxisMinValue(4000f);


        // do not forget to refresh the chart
        // mChart.invalidate();
        mChart.animateX(750);
        mChart.getLegend().setEnabled(false);
        mChart.animateXY(2000, 2000);

        // dont forget to refresh the drawing
        ArrayList<LineDataSet> sets = (ArrayList<LineDataSet>) mChart.getData()
                .getDataSets();

        for (LineDataSet set : sets) {
            set.setDrawFilled(true);
        }

        mChart.invalidate();

    }

    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();
        if (DetailGlobalVar.price != 0) {
            for (int i = 0; i < cnt; i++) {
                e1.add(new Entry((int) ((double) dataArray.get(i) / 100 * DetailGlobalVar.price), i));
            }
        }
        else{
            for (int i = 0; i < cnt; i++) {
                e1.add(new Entry((int) ((double) dataArray.get(i) / 100 * 12000), i));
            }
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");

        d1.setLineWidth(2.5f);
        d1.setCircleSize(4.5f);
        d1.setHighLightColor(Color.rgb(39, 174, 96));
        d1.setDrawValues(false);

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(getXLabelperTime(cnt), sets);

        return cd;
    }

    private LineData generateRandomDataLine() {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int i = 0; i < 18; i++) {
            e1.add(new Entry(new Random().nextInt(10)*1000, i));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + 18 + ", (1)");

        d1.setLineWidth(2.5f);
        d1.setCircleSize(4.5f);
        d1.setHighLightColor(Color.rgb(39, 174, 96));
        d1.setDrawValues(false);

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(getXLabelperTime(18), sets);
        return cd;
    }
    private ArrayList<String> getXLabelperTime(int cnt) {
        ArrayList<String> m = new ArrayList<String>();
        for(int i=0;i<cnt;i++) {
            m.add(times[i]);
        }
        return m;
    }
    private void getMgt(String forOverriding) {

        String testURL = testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-random-graph-data.php";
//        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            //set global object
                            dataArray.clear();
                            for(int i = 0; i < cnt; i++) {
                                JSONObject tmpObject = (JSONObject) response.get(i);
                                dataArray.add(tmpObject.getInt("time_value"));
                            }
                            setData();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("LineChart", "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
    private void getMgt() {

        String testURL="";
        String grp_id="";
        try {
            grp_id = DetailGlobalVar.currentObject.getString("grp_id");
//            grp_id = "7";
            if(grp_id.length()==0 || grp_id.equals("null"))
                return;

        } catch (JSONException e) {
            e.printStackTrace();
            grp_id="7";
            return;
        } catch (NullPointerException e){
            grp_id="7";
            return;
        }
        testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-graph-data.php?groupID="+ grp_id;
//        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            //set global object
                            dataArray.clear();
                            for(int i = 0; i < cnt; i++) {
                                JSONObject tmpObject = (JSONObject) response.get(i);
                                dataArray.add(tmpObject.getInt("time_value"));
                            }
                            setData();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("LineChart", "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
    @Override
    public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent motionEvent) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent motionEvent) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent motionEvent) {

    }

    @Override
    public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

    }

    @Override
    public void onChartScale(MotionEvent motionEvent, float v, float v1) {

    }

    @Override
    public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {

    }

    @Override
    public void onValueSelected(Entry entry, int i, Highlight highlight) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
