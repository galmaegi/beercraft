package galmaegi.beercraft.News;

import android.content.Context;
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

import galmaegi.beercraft.R;
import galmaegi.beercraft.common.BeerIndexItem;

public class NewsFragment extends Fragment {

    private ListView recommendListView;
    private RecommendAdapter recommendAdapter;
    private ArrayList<BeerIndexItem> items;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recommendListView = (ListView) view.findViewById(R.id.lv_recommend);

        items = new ArrayList<>();
        recommendAdapter = new RecommendAdapter(view.getContext(), items);

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View header = inflater.inflate(R.layout.layout_news_listview2_header, null, false);

        recommendListView.addHeaderView(header);
        recommendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        recommendListView.setAdapter(recommendAdapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_news, container, false);

        ViewPager viewpager = (ViewPager)view.findViewById(R.id.inc_news_index).findViewById(R.id.vp_news_index);
        viewpager.setAdapter(new NewsPagerAdapter(getChildFragmentManager()));

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.inc_news_index).findViewById(R.id.tab_news_index);
        tabStrip.setViewPager(viewpager);

        return view;
    }
}

