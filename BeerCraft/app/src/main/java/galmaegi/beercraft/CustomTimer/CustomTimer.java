package galmaegi.beercraft.CustomTimer;


import android.os.CountDownTimer;
import android.os.Handler;


/**
 * Created by root on 15. 11. 24.
 */
public class CustomTimer extends CountDownTimer {
    Handler customHandler;
    public CustomTimer(long millisInFuture, long countDownInterval,Handler customHandler){
        super(millisInFuture,countDownInterval);
        this.customHandler = customHandler;
    }
    @Override
    public void onTick(long millisUntilFinished) {
    }

    @Override
    public void onFinish() {
//        this.start();
        customHandler.sendEmptyMessage(0);
    }
}
