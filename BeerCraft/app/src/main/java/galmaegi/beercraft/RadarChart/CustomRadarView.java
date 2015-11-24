package galmaegi.beercraft.RadarChart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;

import java.util.ArrayList;

import galmaegi.beercraft.Detail.DetailGlobalVar;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 11.
 */
public class CustomRadarView {
    private RadarChart radarchart;
    Typeface tf;

    private ArrayList<String> mParties = new ArrayList<String>();
    public CustomRadarView(Context context, RadarChart radarchart, Typeface tf){

        mParties.add(context.getResources().getString(R.string.alcohol));
        mParties.add(context.getResources().getString(R.string.malty));
        mParties.add(context.getResources().getString(R.string.bitter));
        mParties.add(context.getResources().getString(R.string.sweet));
        mParties.add(context.getResources().getString(R.string.body));
        mParties.add(context.getResources().getString(R.string.florar));


        this.radarchart = radarchart;
        this.tf = tf;
        //set radar values
        radarchart.setDescription("");
        radarchart.setWebLineWidth(1.5f);
        radarchart.setWebLineWidthInner(0.75f);
        radarchart.setWebAlpha(100);
        radarchart.setDrawWeb(true);
        radarchart.setWebColor(Color.WHITE);
        radarchart.setWebColorInner(Color.WHITE);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        RadarMarkerView mv = new RadarMarkerView(context, R.layout.radar_marker);

        // set the marker to the chart
        radarchart.setMarkerView(mv);
        setData();

    }

//    {
//            "CREAMY","FLAVORY","PURE","BITTERNESS","SWEETNESS","CLUMSY"
//    };
    public void setData() {

        float mult = 150;
        int cnt = 6;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < cnt; i++) {
//            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
            try {
                yVals1.add(new Entry((float) DetailGlobalVar.currentObject.getInt(mParties.get(i).toLowerCase()), i));

            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < cnt; i++)
            xVals.add(mParties.get(i % mParties.size()));

        XAxis xaxis=radarchart.getXAxis();
        xaxis.setTextColor(Color.WHITE);
        xaxis.setTextSize(16f);

        YAxis yAxis=radarchart.getYAxis();
        yAxis.setEnabled(false);
        yAxis.setTypeface(tf);



        Legend legend = radarchart.getLegend();
        legend.setEnabled(false);
        legend.setPosition(LegendPosition.ABOVE_CHART_RIGHT);

        radarchart.setRotationEnabled(false);
        radarchart.setRotationAngle(0.1f);
        radarchart.setExtraBottomOffset(50f);


        RadarDataSet set1 = new RadarDataSet(yVals1,null);
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);
        set1.setDrawFilled(false);
        set1.setLineWidth(4f);

        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
        sets.add(set1);

        RadarData data = new RadarData(xVals, sets);
        data.setValueTypeface(tf);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);

        data.setDrawValues(false);

        radarchart.setData(data);

        radarchart.invalidate();
        radarchart.setDescriptionColor(Color.WHITE);
    }

}
