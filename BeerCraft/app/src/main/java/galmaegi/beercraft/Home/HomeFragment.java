package galmaegi.beercraft.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.Date;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.MainActivity;
import galmaegi.beercraft.News.NewsFragment;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.NewsItem;

public class HomeFragment extends Fragment {

    ListView newsListView = null;
    NewsAdapter newsAdapter = null;
    ArrayList<NewsItem> items = null;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsListView = (ListView) view.findViewById(R.id.lv_news);

        items = new ArrayList<>();
        newsAdapter = new NewsAdapter(view.getContext(), items);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.mainActivity.showNewsContent();
            }
        });
        newsListView.setAdapter(newsAdapter);

        getNews();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View holder = inflater.inflate(R.layout.layout_home, container, false);

        ViewPager viewpager = (ViewPager) holder.findViewById(R.id.inc_beer_index).findViewById(R.id.vp_beer_index);
        viewpager.setAdapter(new BeerIndexPagerAdapter(getChildFragmentManager()));

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) holder.findViewById(R.id.inc_beer_index).findViewById(R.id.tab_beer_index);
        tabStrip.setTextSize(13);
        tabStrip.setTextColor(Color.WHITE);
        tabStrip.setViewPager(viewpager);

        return holder;
    }

    private void getNews() {
        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php";

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0 ; i < response.length() ; i++) {
                            try {
                                NewsItem item = new NewsItem(response.getJSONObject(i));
                                items.add(item);
                            } catch (JSONException e) {
                                NewsItem item = new NewsItem();
                                item.setNewsTitle("JSONException");
                                item.setEntryDate(new Date());
                                item.setContent_newsImage("http://beerexchange.dnktechnologies.com/wp-content/uploads/News/1445997263.png");
                                items.add(item);
                                e.printStackTrace();
                            } catch (Exception e) {
                                NewsItem item = new NewsItem();
                                item.setNewsTitle("JSONException");
                                item.setEntryDate(new Date());
                                item.setContent_newsImage("http://beerexchange.dnktechnologies.com/wp-content/uploads/News/1445997263.png");
                                items.add(item);
                            }
                        }
                        newsAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
