

package galmaegi.beercraft.Detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.Detail.LineChart.CustomLineChart;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 14.
 */
public class Detail_2_PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_PAGE_TEXT = "ARG_PAGE_TEXT";

    private int mPage;
    private String text;
    private LineChart linechart;
    public static Detail_2_PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Detail_2_PageFragment fragment = new Detail_2_PageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        text = getArguments().getString(ARG_PAGE_TEXT);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_section_2_page, container, false);
        linechart = (LineChart) view.findViewById(R.id.BeerDetailLine);
        if(mPage==1) {
            new CustomLineChart(getContext(), linechart, GlobalVar.NanumGothic_Bold, 18);
        }
        else if(mPage==2){
            new CustomLineChart(getContext(),linechart, GlobalVar.NanumGothic_Bold,15);
        }
        else if(mPage==3){
            new CustomLineChart(getContext(),linechart, GlobalVar.NanumGothic_Bold, 30);
        }
        return view;
    }

    private void getGraphdata() throws JSONException{
        String testURL="";
        try {
            testURL = "http://http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-group-mgt.php?group_id="
                    +DetailGlobalVar.currentObject.getString("grp_id");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            for(int i=0;i<response.length();i++) {
                                JSONObject response_item = (JSONObject) response.get(i);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Detail_2_graph_loading", "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
