package galmaegi.beercraft.Check;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import galmaegi.beercraft.Beer.BeerIndexPagerAdapter;
import galmaegi.beercraft.Beer.RecommendAdapter;
import galmaegi.beercraft.MainActivity;
import galmaegi.beercraft.R;

public class CheckFragment extends Fragment{
    public static CheckFragment checkFragment;

    public CheckFragment() {
        checkFragment = this;
    }
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.mainActivity.buttonSelector(MainActivity.mainActivity.btn_check);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View holder = inflater.inflate(R.layout.layout_check, container, false);

        ViewPager viewpager = (ViewPager) holder.findViewById(R.id.inc_check_list).findViewById(R.id.vp_check);
        CheckIndexPagerAdapter adapter = new CheckIndexPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(adapter);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) holder.findViewById(R.id.inc_check_list).findViewById(R.id.tab_check);
        tabStrip.setTextSize(13);
        tabStrip.setTextColor(Color.WHITE);
        tabStrip.setViewPager(viewpager);

        return holder;
    }

}
