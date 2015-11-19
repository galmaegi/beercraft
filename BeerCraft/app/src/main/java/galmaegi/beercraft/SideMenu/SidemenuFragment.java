package galmaegi.beercraft.SideMenu;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
=======
import android.widget.ImageButton;
import android.widget.ImageView;
>>>>>>> origin/master
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.MainActivity;
import galmaegi.beercraft.News.RecommendAdapter;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;
import galmaegi.beercraft.common.BeerIndexItemSendListener;

public class SidemenuFragment extends Fragment {
<<<<<<< HEAD
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
=======

    ImageView img_updown;
    NetworkImageView img_product;
    TextView txt_productname;
    TextView txt_currentprice;
    TextView txt_count;
    TextView txt_totalprice;

    ImageButton btn_countminus;
    ImageButton btn_countplus;
    ImageButton btn_buy;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        img_updown = (ImageView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.img_updown);
        img_product = (NetworkImageView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.img_product);
        txt_productname = (TextView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.txt_productname);
        txt_currentprice = (TextView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.txt_currentprice);
        txt_count = (TextView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.txt_count);
        txt_totalprice = (TextView)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.txt_totalprice);

        btn_countminus = (ImageButton)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.btn_countminus);
        btn_countplus = (ImageButton)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.btn_countplus);
        btn_buy = (ImageButton)view.findViewById(R.id.inc_sidemenu_detail).findViewById(R.id.btn_buy);

>>>>>>> origin/master
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_sidemenu, container, false);

        ViewPager viewpager = (ViewPager)view.findViewById(R.id.inc_sidemenu_index).findViewById(R.id.vp_sidemenu_index);
        viewpager.setAdapter(new SidemenuIndexPagerAdapter(getChildFragmentManager()));

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.inc_sidemenu_index).findViewById(R.id.tab_sidemenu_index);
        tabStrip.setViewPager(viewpager);

        return view;
    }

    private class TastingNote {
        private TextView tastingNote;

        public TastingNote(View view) {
            tastingNote = (TextView) view.findViewById(R.id.tv_tastingnote);
        }

        public void setView(SidemenuIndexItem item) {
            tastingNote.setText(item.getTastingNote());
        }
    }
}
