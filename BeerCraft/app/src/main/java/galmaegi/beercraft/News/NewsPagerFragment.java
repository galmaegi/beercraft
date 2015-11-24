package galmaegi.beercraft.News;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.NewsItem;

public class NewsPagerFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    ListView newsListView;
    NewsAdapter newsAdapter;
    ArrayList<NewsItem> items;

    public static NewsPagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NewsPagerFragment fragment = new NewsPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsListView = (ListView) view.findViewById(R.id.lv_news);

        items = new ArrayList<>();
        newsAdapter = new NewsAdapter(view.getContext(), items);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsFragment.newsFragment.showNewsContentView(items, position);
            }
        });
        newsListView.setAdapter(newsAdapter);

        getNewsIndex();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_news_listview1, container, false);

        return view;
    }

    private void getNewsIndex() {
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