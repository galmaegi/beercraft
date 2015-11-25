package galmaegi.beercraft.SideMenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

public class SidemenuFragment extends Fragment {

    public static TastingNote tastingNote;

    ImageView img_updown;
    NetworkImageView img_product;
    TextView txt_productname;
    TextView txt_currentprice;
    TextView txt_count;
    TextView txt_totalprice;

    ImageButton btn_countminus;
    ImageButton btn_countplus;
    ImageButton btn_buy;

    private ListView sidemenuListView;

    private SidemenuAdapter sidemenuAdapter;
    private ArrayList<BeerIndexItem> items;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tastingNote = new TastingNote(view);

        img_updown = (ImageView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.img_updown);
        img_product = (NetworkImageView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.img_product);
        txt_productname = (TextView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.txt_productname);
        txt_currentprice = (TextView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.txt_currentprice);
        txt_count = (TextView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.txt_count);
        txt_totalprice = (TextView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.txt_totalprice);

        btn_countminus = (ImageButton)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.btn_countminus);
        btn_countplus = (ImageButton)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.btn_countplus);
        btn_buy = (ImageButton)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.btn_buy);


//        sidemenuListView = (ListView) view.findViewById(R.id.lv_sidemenu_index);
//
//        items = new ArrayList<>();
//        sidemenuAdapter = new SidemenuAdapter(view.getContext(), items);
//
//        sidemenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                simpleView.setView(items.get(position));
//            }
//        });
//
//        sidemenuListView.setAdapter(sidemenuAdapter);
//        getSidemenu();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_sidemenu, container, false);

        ViewPager viewpager = (ViewPager)view.findViewById(R.id.inc_sidemenu_index).findViewById(R.id.vp_sidemenu_index);
        viewpager.setAdapter(new SidemenuIndexPagerAdapter(getChildFragmentManager()));

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.inc_sidemenu_index).findViewById(R.id.tab_sidemenu_index);
        tabStrip.setTextSize(13);
        tabStrip.setTextColor(Color.WHITE);
        tabStrip.setViewPager(viewpager);

        return view;
    }

    public class TastingNote {
        private TextView tastingNote;

        public TastingNote(View view) {
            tastingNote = (TextView) view.findViewById(R.id.tv_tastingnote);
        }

        public void setView(SidemenuIndexItem item) {
            tastingNote.setText(item.getTastingNote());
        }
    }

    private void getSidemenu() {
        final String testURL = "http://www.kbx.kr/wp-content/plugins/beer-rest-api/lib/class-wp-json-side_menu.php?type=kbx";

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
                        sidemenuAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
