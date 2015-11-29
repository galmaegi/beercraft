package galmaegi.beercraft.Beer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import galmaegi.beercraft.CustomTimer.CustomTimer;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

public class BeerIndexPagerFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    ListView beerListView = null;
    BeerIndexAdapter beerIndexAdapter = null;
    ArrayList<BeerIndexItem> items = null;

    CustomTimer timer;

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }
    @Override
    public void onResume(){
        super.onPause();
        timer.start();
    }
    Handler handler = new Handler(new android.os.Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(mPage == 0) {
                getDraftBeerIndex();
            } else {
                getBottledBeerIndex();
            }
            return false;
        }
    });

    public static BeerIndexPagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BeerIndexPagerFragment fragment = new BeerIndexPagerFragment();
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
        beerListView = (ListView) view.findViewById(R.id.lv_beer_index);
        beerIndexAdapter = new BeerIndexAdapter(view.getContext(), items);

        beerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeerFragment.beerFragment.setSimpleView(items.get(position));
            }
        });
        beerListView.setAdapter(beerIndexAdapter);

        if (mPage == 0) {
            getDraftBeerIndex();
        } else {
            getBottledBeerIndex();
        }

        timer = new CustomTimer(GlobalVar.realLoadingTime,GlobalVar.realLoadingTime, handler);
        timer.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View holder = inflater.inflate(R.layout.layout_beer_listview1, container, false);

        return holder;
    }

    private void getBottledBeerIndex() {
        final String testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-bottled_beer.php";

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(testURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        items.clear();
                        for(int i = 0 ; i < response.length() ; i++) {
                            try {
                                BeerIndexItem item = new BeerIndexItem(response.getJSONObject(i));
                                items.add(item);
                            } catch (Exception e) {
                                BeerIndexItem item = new BeerIndexItem();
                                item.setEnglishName("Excep");
                                item.setEntryDate(new Date());
                                item.setModifyDate(new Date());
                                items.add(item);
                            }
                        }
                        beerIndexAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void getDraftBeerIndex() {
        final String testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-draft_beer.php";

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
                            } catch (Exception e) {
                                BeerIndexItem item = new BeerIndexItem();
                                item.setEnglishName("IOE");
                                item.setEntryDate(new Date());
                                item.setModifyDate(new Date());
                                items.add(item);
                            }
                        }
                        beerIndexAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
