package galmaegi.beercraft.Beer;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
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
import galmaegi.beercraft.common.BeerIndexItem;

public class BeerFragment extends Fragment {
    public static BeerFragment beerFragment;
    SimpleView simpleView;

    ListView recommendListView;
    RecommendAdapter recommendAdapter;
    ArrayList<BeerIndexItem> items;

    CustomTimer timer;

    public BeerFragment() {
        beerFragment = this;
    }
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
        MainActivity.mainActivity.buttonSelector(MainActivity.mainActivity.btn_beer);
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

        simpleView = new SimpleView(view);

        items = new ArrayList<>();
        recommendListView = (ListView) view.findViewById(R.id.lv_recommend);
        recommendAdapter = new RecommendAdapter(view.getContext(), items);

        recommendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                simpleView.setView(items.get(position));
            }
        });
        recommendListView.setAdapter(recommendAdapter);

        getRecommend();

        timer = new CustomTimer(GlobalVar.realLoadingTime,GlobalVar.realLoadingTime, handler);
        timer.start();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View holder = inflater.inflate(R.layout.layout_beer, container, false);

        ViewPager viewpager = (ViewPager) holder.findViewById(R.id.inc_beer_index).findViewById(R.id.vp_beer_index);
        BeerIndexPagerAdapter adapter = new BeerIndexPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(adapter);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) holder.findViewById(R.id.inc_beer_index).findViewById(R.id.tab_beer_index);
        tabStrip.setTextSize(GlobalVar.tabTextSize);
        tabStrip.setTextColor(Color.WHITE);
        tabStrip.setViewPager(viewpager);

        return holder;
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

    public void setSimpleView(BeerIndexItem item) {
        simpleView.setView(item);
    }

    public class SimpleView implements View.OnClickListener{
        private View mAlert;
        private NetworkImageView mThumbnail;
        private TextView mName;
        private TextView mStyle;
        private TextView mAbvMl;
        private TextView mRate;
        private TextView mIncrease;
        private TextView mSellingPrice;
        private Button mShowDetail;

        private BeerIndexItem item;

        public SimpleView(View view) {
            mAlert = view.findViewById(R.id.v_alert);
            mThumbnail = (NetworkImageView) view.findViewById(R.id.iv_thumbnail);
            mName = (TextView) view.findViewById(R.id.tv_name);
            mStyle = (TextView) view.findViewById(R.id.tv_style);
            mAbvMl = (TextView) view.findViewById(R.id.tv_abv_ml);
            mRate = (TextView) view.findViewById(R.id.tv_rate);
            mIncrease = (TextView) view.findViewById(R.id.tv_increase);
            mSellingPrice = (TextView) view.findViewById(R.id.tv_sellingPrice);
            mShowDetail = (Button) view.findViewById(R.id.bt_showdetail);

            mShowDetail.setOnClickListener(this);
        }

        public void setView(BeerIndexItem item) {
            mThumbnail.setImageUrl(item.getProductImage(), AppController.getInstance().getImageLoader());
            mThumbnail.setImageURI(Uri.parse(item.getProductImage()));
            mName.setText(item.getEnglishName());
            mStyle.setText(item.getStyle());
            mAbvMl.setText(item.getStrength()+ "%, " + item.getVolume() + "ml");
            mRate.setText(String.format("%.2f",item.getRate())+"%");
            mIncrease.setText(String.valueOf(item.getIncrease()));
            mSellingPrice.setText(GlobalVar.setComma(item.getSellingPrice()));

            int color;
            if(item.getIncrease() < 0) {
                color = Color.parseColor("#05B005");
            } else if(item.getIncrease() == 0) {
                color = Color.parseColor("#6F6F6F");
            } else {
                color = Color.parseColor("#9D1819");
            }

            mAlert.setBackgroundColor(color);
            mRate.setTextColor(color);
            mIncrease.setTextColor(color);

            this.item = item;
        }

        @Override
        public void onClick(View v) {
            if(item != null) {
                MainActivity.mainActivity.showDetailView(item);
            }
        }
    }
}
