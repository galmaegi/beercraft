package galmaegi.beercraft.Check;

import android.app.Dialog;
import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.Detail.DetailGlobalVar;
import galmaegi.beercraft.GlobalVar;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 18.
 */
public class DialogWishlist extends Dialog implements View.OnClickListener{

    ImageButton dialog_buy_btn_buy;
    ImageButton dialog_buy_btn_no;
    ImageView dialog_buy_check_wishlist_alert;
    public static boolean buy = false;
    public DialogWishlist(Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_buy_wishlist);
        //initialization views which located in activity_detail section4

        dialog_buy_btn_no = (ImageButton)findViewById(R.id.dialog_buy_btn_no);
        dialog_buy_btn_buy = (ImageButton)findViewById(R.id.dialog_buy_btn_buy);
        dialog_buy_check_wishlist_alert = (ImageView)findViewById(R.id.dialog_buy_check_wishlist_alert);

        if(GlobalVar.language == Locale.ENGLISH){

            dialog_buy_btn_buy.setBackground(this.getContext().getResources().getDrawable(R.drawable.btn_buy_eng));
            dialog_buy_btn_no.setBackground(this.getContext().getResources().getDrawable(R.drawable.btn_dialog_cancel_eng));

            final float scale = getContext().getResources().getDisplayMetrics().density;
            int pixels = (int) (90 * scale + 0.5f);
            dialog_buy_check_wishlist_alert.getLayoutParams().height=pixels;
            pixels = (int) (10 * scale + 0.5f);
            dialog_buy_check_wishlist_alert.setPadding(0,0,0,pixels);
            dialog_buy_check_wishlist_alert.setBackground(this.getContext().getResources().getDrawable(R.drawable.dialog_buy_back_eng));
        }
        setSection();

    }

    @Override
    public void onClick(View v) {
        Message message = new Message();
        switch (v.getId()){
            case R.id.dialog_buy_btn_buy:
                buy = true;

                dismiss();
                break;
            case R.id.dialog_buy_btn_no:
                buy = false;

                dismiss();
                break;
        }
    }
    public void setSection(){
        dialog_buy_btn_buy.setOnClickListener(this);
        dialog_buy_btn_no.setOnClickListener(this);
    }
}

