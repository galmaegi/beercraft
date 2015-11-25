package galmaegi.beercraft.News;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import galmaegi.beercraft.R;
import galmaegi.beercraft.common.NewsItem;

public class NewsContentFragment extends Fragment implements View.OnTouchListener{

    private ArrayList<NewsItem> items;
    private int index;

    ViewFlipper viewFlipper;
    TextView tvNewsContent;

    // need restore
    public NewsContentFragment(ArrayList items, int index) {
        this.items = items;
        this.index = index;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(tvNewsContent == null) {
            tvNewsContent = (TextView)view.findViewById(R.id.tv_news_content);
            tvNewsContent.setOnTouchListener(this);
        }

        showNews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_news_content, container, false);

        viewFlipper = (ViewFlipper) view.findViewById(R.id.vf_content);
        viewFlipper.setOnTouchListener(this);

        return view;
    }

    private int mPrevX = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mPrevX = (int)event.getX();
        }

        if(event.getAction() == MotionEvent.ACTION_UP) {
            int mCurX = (int)event.getX();
            if(mCurX < mPrevX) {
                showNextNews();
            } else if(mCurX > mPrevX){
                showPrevNews();
            }

            mPrevX = mCurX;
        }

        return true;
    }

    private void setIndex(int value) {
        int result = index + value;

        if(result < 0 || items.size() <= result) {
            return ;
        }

        index = index + value;
    }

    private void showNews() {
        Spanned html = Html.fromHtml(items.get(index).getContent());
        tvNewsContent.setText(html);
    }

    private void showNextNews() {
        setIndex(1);
        viewFlipper.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.appear_from_right));
        showNews();
    }

    private void showPrevNews() {
        setIndex(-1);
        viewFlipper.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.appear_from_left));
        showNews();
    }
}
