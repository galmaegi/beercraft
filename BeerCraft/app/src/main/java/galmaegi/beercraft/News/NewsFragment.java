package galmaegi.beercraft.News;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.astuetz.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.Detail.Detail_2_Page_Adapter;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

public class NewsFragment extends Fragment {

    public static NewsFragment newsFragment = null;

    private ListView recommendListView = null;
    private RecommendAdapter recommendAdapter = null;
    private ArrayList<BeerIndexItem> items = null;

    public NewsFragment() {
        newsFragment = this;
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
        replaceFragment(newsListFragment);
    }

    Fragment newsListFragment = new NewsListFragment();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View holder = inflater.inflate(R.layout.layout_news, container, false);

        //TODO: make own name
        ViewPager viewpagerTemp = (ViewPager) holder.findViewById(R.id.inc_news_graph).findViewById(R.id.vp_news_graph_index);
        viewpagerTemp.setAdapter(new Detail_2_Page_Adapter(getChildFragmentManager(), "test"));

        PagerSlidingTabStrip tabStripTemp = (PagerSlidingTabStrip) holder.findViewById(R.id.inc_news_graph).findViewById(R.id.tab_news_graph_index);
        tabStripTemp.setViewPager(viewpagerTemp);


        return holder;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.news_fragment, fragment);
        fragmentTransaction.commit();
    }

    public void showNewsContentView(ArrayList items, int index) {
        Fragment fragment = new NewsContentFragment(items, index);
        replaceFragment(fragment);
    }

    private void getRecommend() {
        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-draft.php";

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

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
}

