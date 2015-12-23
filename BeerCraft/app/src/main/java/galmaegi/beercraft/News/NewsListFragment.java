package galmaegi.beercraft.News;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

public class NewsListFragment extends Fragment{

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_news_pager, container, false);

        ViewPager viewpager = (ViewPager)view.findViewById(R.id.vp_news_index);
        viewpager.setAdapter(new NewsPagerAdapter(getChildFragmentManager()));

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tab_news_index);
        tabStrip.setTextSize(GlobalVar.tabTextSize);
        tabStrip.setTextColor(Color.parseColor("#ffd588"));
        tabStrip.setViewPager(viewpager);

        return view;
    }
}
