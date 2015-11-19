package galmaegi.beercraft.Beer;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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
import galmaegi.beercraft.MainActivity;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;
import galmaegi.beercraft.common.BeerIndexItemSendListener;

public class BeerFragment extends Fragment {
    SimpleView simpleView;

    ListView recommendListView;
    RecommendAdapter recommendAdapter;
    ArrayList<BeerIndexItem> items;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        simpleView = new SimpleView(view);

        recommendListView = (ListView) view.findViewById(R.id.lv_recommend);

        items = new ArrayList<>();
        recommendAdapter = new RecommendAdapter(view.getContext(), items);

        recommendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                simpleView.setView(items.get(position));
            }
        });
        recommendListView.setAdapter(recommendAdapter);

        getRecommend();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_beer, container, false);

        ViewPager viewpager = (ViewPager)view.findViewById(R.id.inc_beer_index).findViewById(R.id.vp_beer_index);
        final BeerIndexPagerAdapter adapter = new BeerIndexPagerAdapter(getChildFragmentManager(), new BeerIndexPagerFragment.ItemClickListener() {
            @Override
            public void itemclick(BeerIndexItem item) {
                simpleView.setView(item);
            }
        });
        viewpager.setAdapter(adapter);

        final PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.inc_beer_index).findViewById(R.id.tab_beer_index);
        tabStrip.setViewPager(viewpager);

        return view;
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

    private class SimpleView implements View.OnClickListener, BeerIndexItemSendListener{
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
            mRate.setText(String.valueOf(item.getRateBeerScore()));
            mIncrease.setText(String.valueOf(item.getPrice()));
            mSellingPrice.setText(String.valueOf(item.getSellingPrice()));

            int color;
            if(item.getRateBeerScore() < 0) {
                color = Color.parseColor("#9D1819");
            } else if(item.getRateBeerScore() == 0) {
                color = Color.parseColor("#6F6F6F");
            } else {
                color = Color.parseColor("#05B005");
            }

            mAlert.setBackgroundColor(color);
            mRate.setTextColor(color);
            mIncrease.setTextColor(color);

            this.item = item;
        }

        @Override
        public void onClick(View v) {
            MainActivity.mainActivity.showDetailView(item);
        }

        @Override
        public void sendItem(BeerIndexItem item) {

        }
    }
}
