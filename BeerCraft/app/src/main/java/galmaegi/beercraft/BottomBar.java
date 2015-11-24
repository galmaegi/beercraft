package galmaegi.beercraft;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import galmaegi.beercraft.CustomTimer.CustomTimer;


/**
 * Created by root on 15. 11. 24.
 */
public class BottomBar {

    private TextView bottom_bar_text;
    ArrayList<String> bottomData = new ArrayList<>();

    public Handler handleBottomBar;
    CustomTimer customTimer;
    public BottomBar(View v){
        bottom_bar_text = (TextView)v.findViewById(R.id.bottom_bar_text);
        bottom_bar_text.setSelected(true);



        handleBottomBar = new Handler(new android.os.Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                bottom_bar_text.setText("HELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO END");

                return false;
            }
        });
        customTimer = new CustomTimer(5000,5000,handleBottomBar);
        customTimer.start();

    }

    public void getDataFromWeb(){

    }
    public void makeData(){

    }
    public void printData(){

    }

}
