package galmaegi.beercraft;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import galmaegi.beercraft.Beer.BeerFragment;
import galmaegi.beercraft.Detail.FragmentDetail;
import galmaegi.beercraft.Home.HomeFragment;
import galmaegi.beercraft.News.NewsFragment;
import galmaegi.beercraft.SideMenu.SidemenuFragment;


public class MainActivity extends FragmentActivity implements View.OnClickListener{
    public static MainActivity mainActivity = null;

    View include;
    Button action_bar_tablenum;

    ImageButton btn_home;
    ImageButton btn_beer;
    ImageButton btn_sidemenu;
    ImageButton btn_news;
    ImageButton btn_check;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BaseActivity에서 Activity를 띄워주기 위한 작업
//        super.currentContext = this;

        GlobalVar.NanumGothic_Bold = Typeface.createFromAsset(getAssets(), "NanumGothic_Coding_Bold.ttf");
        //공통뷰들을 찾아주기 위한 작업을 수행해야함
        btn_home = (ImageButton)findViewById(R.id.btn_home);
        btn_beer = (ImageButton)findViewById(R.id.btn_beer);
        btn_sidemenu = (ImageButton)findViewById(R.id.btn_sidemenu);
        btn_news = (ImageButton)findViewById(R.id.btn_news);
        btn_check = (ImageButton)findViewById(R.id.btn_check);
        action_bar_tablenum = (Button) findViewById(R.id.action_bar_tablenum);

        btn_home.setOnClickListener(this);
        btn_home.setSelected(true);
        btn_beer.setOnClickListener(this);
        btn_sidemenu.setOnClickListener(this);
        btn_news.setOnClickListener(this);
        btn_check.setOnClickListener(this);
        action_bar_tablenum.setOnClickListener(this);

        setCurrenttable();

        replaceFragment(frHome);
        mainActivity = this;

    }
    Fragment frHome = new HomeFragment();
    Fragment frBeer = new BeerFragment();
    Fragment frSide = new SidemenuFragment();
    Fragment frNews = new NewsFragment();
    Fragment frDetail = new FragmentDetail(this);
    @Override
    public void onClick(View v) {
        Fragment fr = null;
        ImageButton curBtn = null;
        switch(v.getId()){
            case R.id.btn_back:

                break;
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
                curBtn = btn_news;
                fr = frNews;
                break;
            case R.id.btn_check:
                curBtn = btn_check;
                fr = frDetail;
                break;
            case R.id.action_bar_tablenum:
                DialogActionBar dialogActionBar = new DialogActionBar(this);
                dialogActionBar.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        setCurrenttable();
                    }
                });
                dialogActionBar.show();
                break;
        }
        if(fr!=null)
            replaceFragment(fr);

        if(curBtn!=null)
            buttonSelector(curBtn);

    }
    public void buttonSelector(ImageButton curBtn){
        btn_check.setSelected(false);
        btn_news.setSelected(false);
        btn_sidemenu.setSelected(false);
        btn_beer.setSelected(false);
        btn_home.setSelected(false);

        curBtn.setSelected(true);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();
    }

    public void setCurrenttable(){
        GlobalVar.currentTable = preftablenumRead();
        action_bar_tablenum.setText("TABLE NUMBER #"+GlobalVar.currentTable);

    }

    public String preftablenumRead(){
        SharedPreferences prefs = getSharedPreferences("BEERCRAFT", Context.MODE_PRIVATE);
        String returnvalue = prefs.getString("tablenum", "001");
        return returnvalue;
    }

}
