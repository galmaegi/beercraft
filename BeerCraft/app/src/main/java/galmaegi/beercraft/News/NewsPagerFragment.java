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

    ListView newsListView = null;
    NewsAdapter newsAdapter = null;
    ArrayList<NewsItem> items = null;

    enum TYPE {
        LATEST,
        BOTTLED,
        DRAFT,
        ETC
    }

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

        items = new ArrayList<>();
        newsListView = (ListView) view.findViewById(R.id.lv_news);
        newsAdapter = new NewsAdapter(view.getContext(), items);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsFragment.newsFragment.showNewsContentView(items, position);
            }
        });
        newsListView.setAdapter(newsAdapter);

        TYPE type;
        switch (mPage) {
            case 0:
                type = TYPE.LATEST;
                break;
            case 1:
                type = TYPE.BOTTLED;
                break;
            case 2:
                type = TYPE.DRAFT;
                break;
            default:
                type = TYPE.ETC;
                break;
        }

        getNewsIndex(type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_news_listview1, container, false);

        return view;
    }

    private void getNewsIndex(TYPE type) {
        final String testURL;

        if(type == TYPE.LATEST) {
            testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php?type=latest";
        } else if(type == TYPE.BOTTLED) {
            testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php?type=bottled";
        } else if(type == TYPE.DRAFT) {
            testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php?type=draft";
        } else {
            testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-news.php?type=etc";
        }

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