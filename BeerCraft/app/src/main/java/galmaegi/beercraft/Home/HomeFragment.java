package galmaegi.beercraft.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import galmaegi.beercraft.CustomTimer.CustomTimer;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.MainActivity;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.NewsItem;

public class HomeFragment extends Fragment {

    ListView newsListView = null;
    NewsAdapter newsAdapter = null;
    ArrayList<NewsItem> items = null;

    private CustomTimer customTimer;

    BeerIndexPagerAdapter beerIndexPagerAdapter;

    HomeKOBI homeKOBI;
    ViewPager viewpager;
    PagerSlidingTabStrip tabStrip;

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.mainActivity.buttonSelector(MainActivity.mainActivity.btn_home);
        getListView();
//        customTimer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        customTimer.cancel();
    }

    Handler handleHome = new Handler(new android.os.Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            getNews();
//            getBeers();
            return false;
        }
    });

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

        homeKOBI = new HomeKOBI(view);

        //set timer to loading realtime data
        customTimer = new CustomTimer(GlobalVar.realLoadingTime,GlobalVar.realLoadingTime,handleHome);
        customTimer.start();
    }

    void getListView(){
        viewpager = (ViewPager) this.getView().findViewById(R.id.inc_beer_index).findViewById(R.id.vp_beer_index);
        beerIndexPagerAdapter = new BeerIndexPagerAdapter((getChildFragmentManager()));
        viewpager.setAdapter(beerIndexPagerAdapter);

        tabStrip = (PagerSlidingTabStrip) this.getView().findViewById(R.id.inc_beer_index).findViewById(R.id.tab_beer_index);
        tabStrip.setTextSize(13);
        tabStrip.setTextColor(Color.WHITE);
        tabStrip.setViewPager(viewpager);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View holder = inflater.inflate(R.layout.layout_home, container, false);

        return holder;
    }

    private void getBeers(){
        BeerIndexPagerFragment BBP = (BeerIndexPagerFragment) beerIndexPagerAdapter.getItem(0);
        BBP.getBottledBeerIndex();
        BeerIndexPagerFragment DBP = (BeerIndexPagerFragment) beerIndexPagerAdapter.getItem(1);
        DBP.getDraftBeerIndex();
    }

    private void getNews() {
        final String testURL = "http://kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php";

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        items.clear();
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
