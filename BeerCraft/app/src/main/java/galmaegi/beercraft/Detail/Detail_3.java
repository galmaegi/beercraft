package galmaegi.beercraft.Detail;

import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;

import org.json.JSONException;

import galmaegi.beercraft.RadarChart.CustomRadarView;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 17.
 */
public class Detail_3 {
    View parent_View;
    //to set RadarChart view
    RadarChart detailRadar;
    CustomRadarView customradar;


    TextView detail_3_strength;
    TextView detail_3_bitter;
    TextView detail_3_volume;
    TextView detail_3_ratebeer;


    public Detail_3(View view){
        parent_View = view;
        //initialization RadarChart
        detailRadar = (RadarChart)view.findViewById(R.id.BeerDetailRadar);
        detail_3_strength = (TextView)view.findViewById(R.id.detail_3_strength);
        detail_3_bitter = (TextView)view.findViewById(R.id.detail_3_bitter);
        detail_3_volume = (TextView)view.findViewById(R.id.detail_3_volume);
        detail_3_ratebeer = (TextView)view.findViewById(R.id.detail_3_ratebeer);


    }
    public void setSection(){
        //set Radar's value
        customradar = new CustomRadarView(parent_View.getContext(),detailRadar, GlobalVar.NanumGothic_Bold);
        try {
            detail_3_strength.setText(DetailGlobalVar.currentObject.getString("strength"));
            detail_3_bitter.setText(DetailGlobalVar.currentObject.getString("bitterTaste"));
            detail_3_volume.setText(DetailGlobalVar.currentObject.getString("volume"));
            detail_3_ratebeer.setText(DetailGlobalVar.currentObject.getString("rateBeerScore"));
        }
        catch (JSONException e){

        }
    }

}
