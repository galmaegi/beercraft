package galmaegi.beercraft;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.app.Fragment;



public class MainActivity extends BaseActivity{
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



    }


}
