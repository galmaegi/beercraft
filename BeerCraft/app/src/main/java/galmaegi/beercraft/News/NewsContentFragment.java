package galmaegi.beercraft.News;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import galmaegi.beercraft.R;
import galmaegi.beercraft.common.NewsItem;

public class NewsContentFragment extends Fragment implements View.OnTouchListener,Html.ImageGetter{

    private ArrayList<NewsItem> items;
    private int index;

    ViewFlipper viewFlipper;
    TextView tvNewsContent;
    String TAG = "NewsContentFragment";

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
            Log.d("mCurx",mCurX+"");
            Log.d("mPrevX",mPrevX+"");
            if(mCurX - mPrevX < 100) {
                showNextNews();
            } else if(100 > mPrevX - mCurX){
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
        URLImageParser p = new URLImageParser(tvNewsContent, getContext());
//        Spanned html = Html.fromHtml(items.get(index).getContent(), p, null);
        Spanned html = Html.fromHtml(items.get(index).getContent(),this,null);

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


    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(R.drawable.sidemenu_loading_back);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(source, d);

        return d;
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d(TAG, "doInBackground " + source);
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "onPostExecute drawable " + mDrawable);
            Log.d(TAG, "onPostExecute bitmap " + bitmap);
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = tvNewsContent.getText();
                tvNewsContent.setText(t);
            }
        }
    }
}
