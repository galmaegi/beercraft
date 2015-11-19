package galmaegi.beercraft.Detail.LineChart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.widget.SeekBar;

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

import java.util.ArrayList;

import galmaegi.beercraft.R;
import galmaegi.beercraft.Detail.RadarChart.RadarMarkerView;

/**
 * Created by root on 15. 11. 15.
 */
public class CustomLineChart implements SeekBar.OnSeekBarChangeListener,
        OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart mChart;

    public CustomLineChart(Context context,LineChart mChart,Typeface tf,int cnt){
        this.mChart = mChart;
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

        // set data
        mChart.setData(generateDataLine(cnt));

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

        for (int i = 0; i < cnt; i++) {
            e1.add(new Entry(i*1000, i));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");

        d1.setLineWidth(2.5f);
        d1.setCircleSize(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(getXLabelperDays(cnt), sets);
        return cd;
    }
    private ArrayList<String> getXLabelperDays(int cnt) {
        ArrayList<String> m = new ArrayList<String>();
        for(int i=1;i<=cnt;i++) {
            m.add(i+"");
        }
        return m;
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