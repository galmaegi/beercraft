package galmaegi.beercraft;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import galmaegi.beercraft.Detail.FragmentDetail;


public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private LinearLayout custom_dynamic_activity;
    View include;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BaseActivity에서 Activity를 띄워주기 위한 작업
//        super.currentContext = this;

        //공통뷰들을 찾아주기 위한 작업을 수행해야함
        findViewById(R.id.main_left_side).findViewById(R.id.btn_home).setOnClickListener(this);
        findViewById(R.id.main_left_side).findViewById(R.id.btn_beer).setOnClickListener(this);
        findViewById(R.id.main_left_side).findViewById(R.id.btn_sidemenu).setOnClickListener(this);
        findViewById(R.id.main_left_side).findViewById(R.id.btn_news).setOnClickListener(this);
        findViewById(R.id.main_left_side).findViewById(R.id.btn_check).setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        Fragment fr=null;
        switch(v.getId()){
            case R.id.btn_back:
                break;
            case R.id.btn_home:
//                Log.d("is clicked", "hello");
//                Intent intent = new Intent(currentContext,DetailActivity.class);
//                currentContext.startActivity(intent);
//                this.finish();

                break;
            case R.id.btn_beer:
                fr = new FragmentTwo();
                break;
            case R.id.btn_sidemenu:
                fr = new BeerFragment();
                break;
            case R.id.btn_news:
                break;
            case R.id.btn_check:
                fr = new FragmentDetail(this);
                break;


        }
        if(fr!=null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment, fr);
            fragmentTransaction.commit();
        }

    }

}
