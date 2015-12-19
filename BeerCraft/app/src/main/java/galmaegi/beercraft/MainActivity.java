package galmaegi.beercraft;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

import galmaegi.beercraft.Beer.BeerFragment;
import galmaegi.beercraft.Check.CheckFragment;
import galmaegi.beercraft.Detail.FragmentDetail;
import galmaegi.beercraft.Home.HomeFragment;
import galmaegi.beercraft.News.NewsFragment;
import galmaegi.beercraft.SideMenu.SidemenuFragment;
import galmaegi.beercraft.common.BeerIndexItem;


public class MainActivity extends FragmentActivity implements View.OnClickListener, View.OnLongClickListener{

    public static MainActivity mainActivity = null;
    private View mDecorView;

    View include;
    Button action_bar_tablenum;
    Button action_bar_title;

    //left side Button
    public ImageButton btn_back;
    public ImageButton btn_home;
    public ImageButton btn_beer;
    public ImageButton btn_sidemenu;
    public ImageButton btn_news;
    public ImageButton btn_check;

    //bottom bar
    BottomBar bottomBar;

    FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDecorView = getWindow().getDecorView();
        hideSystemUI(mDecorView);
        //BaseActivity에서 Activity를 띄워주기 위한 작업
//        super.currentContext = this;

        GlobalVar.NanumGothic_Bold = Typeface.createFromAsset(getAssets(), "NanumGothic_Coding_Bold.ttf");
        //공통뷰들을 찾아주기 위한 작업을 수행해야함
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_home = (ImageButton)findViewById(R.id.btn_home);
        btn_beer = (ImageButton)findViewById(R.id.btn_beer);
        btn_sidemenu = (ImageButton)findViewById(R.id.btn_sidemenu);
        btn_news = (ImageButton)findViewById(R.id.btn_news);
        btn_check = (ImageButton)findViewById(R.id.btn_check);

        action_bar_tablenum = (Button) findViewById(R.id.action_bar_tablenum);
        action_bar_title = (Button) findViewById(R.id.action_bar_title);

        bottomBar = new BottomBar(this.getWindow().getDecorView().getRootView());


        btn_back.setOnClickListener(mainBackHandler);
        btn_home.setOnClickListener(this);
        btn_home.setSelected(true);
        btn_beer.setOnClickListener(this);
        btn_sidemenu.setOnClickListener(this);
        btn_news.setOnClickListener(this);
        btn_check.setOnClickListener(this);

        action_bar_tablenum.setOnLongClickListener(this);
        action_bar_title.setOnLongClickListener(this);


        setCurrenttable();

        fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(fm.getBackStackEntryCount() == 1)
                    btn_back.setSelected(false);
                Log.d("BACKSTACK", String.valueOf(fm.getBackStackEntryCount()));
            }
        });
        replaceFragment(frHome);
        mainActivity = this;

        setLocaleResources();
    }

    public void setLocaleResources(){
        if(GlobalVar.language == Locale.ENGLISH) {
            btn_beer.setBackground(ContextCompat.getDrawable(this, R.drawable.btn_beer_selector_eng));
            btn_home.setBackground(ContextCompat.getDrawable(this,R.drawable.btn_home_selector_eng));
            btn_sidemenu.setBackground(ContextCompat.getDrawable(this,R.drawable.btn_sidemenu_selector_eng));
            btn_news.setBackground(ContextCompat.getDrawable(this,R.drawable.btn_news_selector_eng));
            btn_check.setBackground(ContextCompat.getDrawable(this,R.drawable.btn_check_selector_eng));
        }
    }

    Fragment frHome = new HomeFragment();
    Fragment frBeer = new BeerFragment();
    Fragment frSide = new SidemenuFragment();
    Fragment frNews = new NewsFragment();
    Fragment frCheck = new CheckFragment();

    @Override
    public void onClick(View v) {
        Fragment fr = null;
        ImageButton curBtn = null;
        switch(v.getId()){
            case R.id.btn_home:
                curBtn = btn_home;
                fr = frHome;
                break;
            case R.id.btn_beer:
                curBtn = btn_beer;
                fr = frBeer;
                break;
            case R.id.btn_sidemenu:
                curBtn = btn_sidemenu;
                fr = frSide;
                break;
            case R.id.btn_news:
                frNews = new NewsFragment();
                curBtn = btn_news;
                fr = frNews;
                break;
            case R.id.btn_check:
                curBtn = btn_check;
                fr = frCheck;
                break;
        }
        if(fr!=null) {
            btn_back.setSelected(true);
            replaceFragment(fr);
            setBackButtonHandler(null);
        }

        if(curBtn!=null)
            buttonSelector(curBtn);
    }
    public void buttonSelector(ImageButton curBtn){
        btn_check.setSelected(false);
        btn_news.setSelected(false);
        btn_sidemenu.setSelected(false);
        btn_beer.setSelected(false);
        btn_home.setSelected(false);
        if(fm.getBackStackEntryCount() != 1)
            btn_back.setSelected(true);
        else
            btn_back.setSelected(false);

        curBtn.setSelected(true);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void Back() {
        if(fm.getBackStackEntryCount() == 1) {
            btn_back.setSelected(false);
            return ;
        }
        fm.popBackStack();
        if(fm.getBackStackEntryCount() == 1){
            btn_back.setSelected(false);
        }
    }

    public void setCurrenttable(){
        GlobalVar.currentTable = preftablenumRead();
        action_bar_tablenum.setText("TABLE NUMBER #" + GlobalVar.currentTable);
    }

    public String preftablenumRead(){
        SharedPreferences prefs = getSharedPreferences("BEERCRAFT", Context.MODE_PRIVATE);
        String returnvalue = prefs.getString("tablenum", "001");
        return returnvalue;
    }

    public void showDetailView(BeerIndexItem item) {
        Fragment fragment = new FragmentDetail(this, item);
        replaceFragment(fragment);
    }

    public void showNewsContent() {
        replaceFragment(frNews);
    }

    private void hideSystemUI(View mDecorView) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
            hideSystemUI(mDecorView);

    }
    @Override
    public boolean onLongClick(View v) {
        boolean returnvalue=false;
        switch (v.getId()){
            case R.id.action_bar_tablenum:
                DialogPassword dialogPassword = new DialogPassword(this);
                dialogPassword.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        setCurrenttable();
                    }
                });
                dialogPassword.show();
                returnvalue = true;
                break;
            case R.id.action_bar_title:
                DialogLanguage dialogLanguage = new DialogLanguage(this);
                dialogLanguage.show();
                dialogLanguage.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(GlobalVar.localeChanged == true){
                            Resources res = getResources();
                            DisplayMetrics dm = res.getDisplayMetrics();
                            Configuration conf = res.getConfiguration();
                            conf.locale = GlobalVar.language;
                            res.updateConfiguration(conf, dm);
                            Intent refresh = new Intent(getApplicationContext(), SplashActivity.class);
                            startActivity(refresh);
                            MainActivity.mainActivity.finish();
                        }
                    }
                });
                break;
        }
        return returnvalue;
    }

    @Override
    public void onBackPressed() {
    }
    View.OnClickListener mainBackHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Back();
        }
    };

    public void setBackButtonHandler(View.OnClickListener listener) {
        if(listener == null) {
            btn_back.setOnClickListener(mainBackHandler);
        } else {
            btn_back.setOnClickListener(listener);
        }
    }
}
