package galmaegi.beercraft.Beer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.CustomTimer.CustomTimer;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

public class BeerIndexPagerFragment extends Fragment implements View.OnClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    ListView beerListView = null;
    BeerIndexAdapter beerIndexAdapter = null;
    ArrayList<BeerIndexItem> items = null;

    CustomTimer timer;

    public static BeerIndexPagerFragment beerIndexDraft;
    public static BeerIndexPagerFragment beerIndexBottled;

    //forSorting
    Button beer_list_btn_index;
    Button beer_list_btn_style;
    Button beer_list_btn_abv;
    Button beer_list_btn_ml;
    Button beer_list_btn_current;

    ArrayList<Boolean> isSort;

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.beer_list_btn_index:
                this.sortList(0);
                break;
            case R.id.beer_list_btn_style:
                this.sortList(1);
                break;
            case R.id.beer_list_btn_abv:
                this.sortList(2);
                break;
            case R.id.beer_list_btn_ml:
                this.sortList(3);
                break;
            case R.id.beer_list_btn_current:
                this.sortList(4);
                break;
        }

    }

    static class sortBeer implements Comparator<BeerIndexItem> {

        /**
         * 오름차순(ASC)
         */
        public int sortArg;
        public BeerIndexItem lhs;
        public BeerIndexItem rhs;
        public boolean isSort;

        public sortBeer(int sortArg, boolean isSort){
            this.sortArg = sortArg;
            this.isSort = isSort;
        }

        @Override
        public int compare(BeerIndexItem lhs, BeerIndexItem rhs) {
            int returnValue = -1;
            if(isSort == true) {
                this.lhs = lhs;
                this.rhs = rhs;
            } else {
                this.rhs = lhs;
                this.lhs = rhs;
            }

            if(sortArg == 0){
                returnValue = sortByName();
            } else if(sortArg == 1){
                returnValue = sortByStyle();
            } else if(sortArg == 2){
                returnValue = sortByPercent();
            } else if(sortArg == 3){
                returnValue = sortByMl();
            } else if(sortArg == 4){
                returnValue = sortByCurrent();
            }
            return returnValue;
        }
        public int sortByName(){

            if(GlobalVar.language == Locale.KOREAN) {
                return lhs.getBeerName().compareTo(rhs.getBeerName());
            }
            else {
                return lhs.getEnglishName().compareTo(rhs.getEnglishName());
            }
        }
        public int sortByStyle(){
            return lhs.getStyle().compareTo(rhs.getStyle());
        }
        public int sortByPercent(){
            if(lhs.getStrength() - rhs.getStrength() <0)
                return -1;
            else if(lhs.getStrength() - rhs.getStrength() > 0)
                return 1;
            else
                return 0;
        }
        public int sortByMl(){
            return (lhs.getVolume() - rhs.getVolume());
        }
        public int sortByCurrent(){
            return lhs.getSellingPrice() - rhs.getSellingPrice();
        }

    }

    public void sortList(int sortArg){
        Collections.sort(this.items, new sortBeer(sortArg,isSort.get(sortArg)));
        isSort.set(sortArg,!isSort.get(sortArg));
        this.beerIndexAdapter.notifyDataSetChanged();
    }



    public static BeerIndexPagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BeerIndexPagerFragment fragment = new BeerIndexPagerFragment();

        if(page == 0){
            beerIndexDraft = fragment;
        } else{
            beerIndexBottled = fragment;
        }

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
                BeerFragment.beer_simple_default.setVisibility(View.INVISIBLE);
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

        beer_list_btn_index = (Button)view.findViewById(R.id.beer_list_btn_index);
        beer_list_btn_style = (Button)view.findViewById(R.id.beer_list_btn_style);
        beer_list_btn_abv = (Button)view.findViewById(R.id.beer_list_btn_abv);
        beer_list_btn_ml = (Button)view.findViewById(R.id.beer_list_btn_ml);
        beer_list_btn_current = (Button)view.findViewById(R.id.beer_list_btn_current);

        beer_list_btn_index.setOnClickListener(this);
        beer_list_btn_style.setOnClickListener(this);
        beer_list_btn_abv.setOnClickListener(this);
        beer_list_btn_ml.setOnClickListener(this);
        beer_list_btn_current.setOnClickListener(this);

        isSort = new ArrayList<Boolean>();
        for(int i=0; i < 5; i++)
            isSort.add(i,true);
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
