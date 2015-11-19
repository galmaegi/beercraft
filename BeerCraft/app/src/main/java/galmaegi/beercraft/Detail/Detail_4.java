package galmaegi.beercraft.Detail;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;

import galmaegi.beercraft.AppController;
import galmaegi.beercraft.R;

/**
 * Created by root on 15. 11. 17.
 */
public class Detail_4 implements View.OnClickListener{
    View parent_view;

    //<views where included in section 4>
    ImageView detail_4_img_updown;
    NetworkImageView detail_4_productimage;
    TextView detail_4_productname;
    TextView detail_4_productstyle;
    TextView detail_4_abv;
    TextView detail_4_country;
    TextView detail_4_changepercent;
    TextView detail_4_changeprice;
    TextView detail_4_currentprice;
    ImageButton detail_4_countminus;
    TextView detail_4_counttext;
    ImageButton detail_4_countplus;
    TextView detail_4_totalprice;
    ImageButton detail_4_btn_buy;

    public Detail_4(View view){
        parent_view = view;
        //initialization views which located in activity_detail section4
        detail_4_img_updown = (ImageView)view.findViewById(R.id.detail_4_img_updown);
        detail_4_productimage = (NetworkImageView)view.findViewById(R.id.detail_4_productimage);
        detail_4_productname = (TextView)view.findViewById(R.id.detail_4_productname);
        detail_4_productstyle = (TextView)view.findViewById(R.id.detail_4_productstyle);
        detail_4_abv = (TextView)view.findViewById(R.id.detail_4_abv);
        detail_4_country = (TextView)view.findViewById(R.id.detail_4_country);
        detail_4_changepercent = (TextView)view.findViewById(R.id.detail_4_changepercent);
        detail_4_changeprice = (TextView)view.findViewById(R.id.detail_4_changeprice);
        detail_4_currentprice = (TextView)view.findViewById(R.id.detail_4_currentprice);
        detail_4_countminus = (ImageButton)view.findViewById(R.id.detail_4_countminus);
        detail_4_counttext = (TextView)view.findViewById(R.id.detail_4_counttext);
        detail_4_countplus = (ImageButton)view.findViewById(R.id.detail_4_countplus);
        detail_4_totalprice = (TextView)view.findViewById(R.id.detail_4_totalprice);
        detail_4_btn_buy = (ImageButton)view.findViewById(R.id.detail_4_btn_buy);

    }


    public void setSection(){
        try {
            //to set detail_4_section
            ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
            detail_4_productimage.setImageUrl(DetailGlobalVar.currentObject.getString("proudctImage"), mImageLoader);
            detail_4_productname.setText(DetailGlobalVar.currentObject.getString("productName"));
            detail_4_productstyle.setText(DetailGlobalVar.currentObject.getString("style"));
            detail_4_abv.setText(DetailGlobalVar.currentObject.getString("strength") + "%, " + DetailGlobalVar.currentObject.getString("volume") + "ml");
            detail_4_country.setText(DetailGlobalVar.currentObject.getString("country"));
            detail_4_currentprice.setText(Detail_4_clicklistener.textFormating(DetailGlobalVar.price) + "");
            Detail_4_clicklistener detail_4_clicklistener = new Detail_4_clicklistener(detail_4_totalprice, detail_4_counttext, DetailGlobalVar.price);
            detail_4_countplus.setOnClickListener(detail_4_clicklistener);
            detail_4_countminus.setOnClickListener(detail_4_clicklistener);
            detail_4_btn_buy.setOnClickListener(this);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_4_btn_buy:
                DialogBuy dialogBuy = new DialogBuy(parent_view.getContext());
                dialogBuy.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                dialogBuy.show();
                break;
        }
    }
}
