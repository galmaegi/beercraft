package galmaegi.beercraft.RadarChart;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 11.
 */
public class CustomRadarView {
    private RadarChart radarchart;

    public CustomRadarView(Context context, RadarChart radarchart){
        this.radarchart = radarchart;
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
    private String[] mParties = new String[] {
            "CREAMY","FLAVORY","PURE","BITERNESS","SWEETNESS","CLUMSY"
    };
    public void setData() {

        float mult = 150;
        int cnt = 6;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
//        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < cnt; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
        }

//        for (int i = 0; i < cnt; i++) {
//            yVals2.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
//        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < cnt; i++)
            xVals.add(mParties[i % mParties.length]);

        XAxis xaxis=radarchart.getXAxis();
        xaxis.setTextColor(Color.WHITE);
        xaxis.setTextSize(20f);

        YAxis yAxis=radarchart.getYAxis();
        yAxis.setEnabled(false);


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
//        set1.setVisible(false);



//        RadarDataSet set2 = new RadarDataSet(yVals2, "Set 2");
//        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
//        set2.setDrawFilled(true);
//        set2.setLineWidth(2f);

        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
        sets.add(set1);
//        sets.add(set2);

        RadarData data = new RadarData(xVals, sets);
//        data.setValueTypeface(tf);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);

        data.setDrawValues(false);

        radarchart.setData(data);

        radarchart.invalidate();
        radarchart.setDescriptionColor(Color.WHITE);
    }

}
