package galmaegi.beercraft.News;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.CustomTimer.CustomTimer;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.LineChart.CustomLineChart;
import galmaegi.beercraft.MainActivity;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

public class NewsFragment extends Fragment {

    public static NewsFragment newsFragment = null;

    private ListView recommendListView = null;
    private RecommendAdapter recommendAdapter = null;
    private ArrayList<BeerIndexItem> items = null;

    FragmentManager fm;
    boolean isBackActive = false;

    public NewsFragment() {
        newsFragment = this;
    }

    LineChart NewsLineChart;

    CustomTimer timer;

    Handler handler = new Handler(new android.os.Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            getRecommend();
            return false;
        }
    });
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.mainActivity.buttonSelector(MainActivity.mainActivity.btn_news);
        timer.start();
    }
    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<>();
        recommendListView = (ListView) view.findViewById(R.id.lv_recommend);
        recommendAdapter = new RecommendAdapter(view.getContext(), items);

        recommendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        recommendListView.setAdapter(recommendAdapter);

        getRecommend();

        timer = new CustomTimer(GlobalVar.realLoadingTime,GlobalVar.realLoadingTime, handler);
        timer.start();

        fm = getChildFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (1 < fm.getBackStackEntryCount()) {
                    isBackActive = true;
                }

                if (isBackActive && fm.getBackStackEntryCount() == 1) {
                    isBackActive = false;
                    MainActivity.mainActivity.setBackButtonHandler(null);
                }
            }
        });
        replaceFragment(newsListFragment);

        NewsLineChart = (LineChart)view.findViewById(R.id.NewsLineChart);
        new CustomLineChart(getContext(), NewsLineChart, GlobalVar.NanumGothic_Bold, 20);
    }

    Fragment newsListFragment = new NewsListFragment();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View holder = inflater.inflate(R.layout.layout_news, container, false);

        //TODO: make own name



        return holder;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.news_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void showNewsContentView(ArrayList items, int index) {
        MainActivity.mainActivity.setBackButtonHandler(newsBackButtonHandler);
        Fragment fragment = new NewsContentFragment(items, index);
        replaceFragment(fragment);
    }

    private void getRecommend() {
        final String testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-recommended_beer.php";

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        items.clear();
                        for(int i = 0 ; i < response.length() ; i++) {
                            try {
                                BeerIndexItem item = new BeerIndexItem(response.getJSONObject(i));
                                items.add(item);
                            } catch (JSONException e) {
                                BeerIndexItem item = new BeerIndexItem();
                                item.setEnglishName("JSON Excep");
                                item.setEntryDate(new Date());
                                item.setModifyDate(new Date());
                                items.add(item);
                                e.printStackTrace();
                            }
                        }
                        recommendAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }



    View.OnClickListener newsBackButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(fm.getBackStackEntryCount() == 1) {
                return ;
            }
            fm.popBackStack();
        }
    };
}

