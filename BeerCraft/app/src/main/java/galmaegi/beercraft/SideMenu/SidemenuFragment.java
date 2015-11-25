package galmaegi.beercraft.SideMenu;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.MainActivity;
import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

public class SidemenuFragment extends Fragment {
    public static SidemenuFragment sidemenuFragment;

    public TastingNote tastingNote;
    public SimpleView simpleView;

    public SidemenuFragment() {
        sidemenuFragment = this;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tastingNote = new TastingNote(view);
        simpleView = new SimpleView(view);
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

    public void setTastingNote(SidemenuIndexItem item) {
        tastingNote.setView(item);
    }

    public void setSimpleView(SidemenuIndexItem item) {
        simpleView.setView(item);
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

    public class SimpleView implements View.OnClickListener{
        ImageView img_updown;
        NetworkImageView img_product;
        TextView txt_productname;
        TextView txt_currentprice;
        TextView txt_count;
        TextView txt_totalprice;

        ImageButton btn_countminus;
        ImageButton btn_countplus;
        ImageButton btn_buy;

        private SidemenuIndexItem item;
        int count;

        public SimpleView(View view) {
            img_updown = (ImageView)view.findViewById(R.id.img_updown);
            img_product = (NetworkImageView)view.findViewById(R.id.img_product);
            txt_productname = (TextView)view.findViewById(R.id.txt_productname);
            txt_currentprice = (TextView)view.findViewById(R.id.txt_currentprice);
            txt_count = (TextView)view.findViewById(R.id.txt_count);
            txt_totalprice = (TextView)view.findViewById(R.id.txt_totalprice);

            btn_countminus = (ImageButton)view.findViewById(R.id.btn_countminus);
            btn_countplus = (ImageButton)view.findViewById(R.id.btn_countplus);
            btn_buy = (ImageButton)view.findViewById(R.id.btn_buy);

            btn_countminus.setOnClickListener(this);
            btn_countplus.setOnClickListener(this);
            btn_buy.setOnClickListener(this);

            count = 1;
        }

        public void setView(SidemenuIndexItem item) {
            int price = item.getPrice();
            img_product.setImageUrl(item.getProudctImage(), AppController.getInstance().getImageLoader());

            txt_productname.setText(item.getProductName());
            txt_currentprice.setText(GlobalVar.setComma(price));
            txt_count.setText(String.valueOf(count));
            txt_totalprice.setText(GlobalVar.setComma(price * count));

            this.item = item;
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_countminus) {
                count--;

                if(count <= 0) {
                    btn_countminus.setEnabled(false);
                }

                setView(item);
            } else if(v.getId() == R.id.btn_countplus) {
                count++;

                if(!btn_countminus.isEnabled()) {
                    btn_countminus.setEnabled(true);
                }

                setView(item);
            } else if(v.getId() == R.id.btn_buy) {

            }
        }
    }
}
