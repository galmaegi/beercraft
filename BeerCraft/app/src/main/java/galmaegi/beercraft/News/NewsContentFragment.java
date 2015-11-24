package galmaegi.beercraft.News;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import galmaegi.beercraft.R;
import galmaegi.beercraft.common.NewsItem;

public class NewsContentFragment extends Fragment implements View.OnTouchListener{

    private ArrayList<NewsItem> items;
    private int index;

    private ViewFlipper viewFlipper;

    // need restore
    public NewsContentFragment(ArrayList items, int index) {
        this.items = items;
        this.index = index;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_news_content, container, false);

        viewFlipper = (ViewFlipper) view.findViewById(R.id.vf_content);
        viewFlipper.setOnTouchListener(this);

        View content = inflater.inflate(R.layout.layout_news_content_core, viewFlipper, false);
        viewFlipper.addView(content);

        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
