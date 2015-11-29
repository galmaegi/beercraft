package galmaegi.beercraft;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by root on 15. 11. 17.
 */
public class DialogActionBar extends Dialog implements View.OnClickListener{

    Button dialog_action_bar_btn_0;
    Button dialog_action_bar_btn_1;
    Button dialog_action_bar_btn_2;
    Button dialog_action_bar_btn_3;
    Button dialog_action_bar_btn_4;
    Button dialog_action_bar_btn_5;
    Button dialog_action_bar_btn_6;
    Button dialog_action_bar_btn_7;
    Button dialog_action_bar_btn_8;
    Button dialog_action_bar_btn_9;
    Button dialog_action_bar_btn_back;
    Button dialog_action_bar_btn_accept;
    Button dialog_action_bar_btn_exit;

    TextView dialog_action_bar_text;

    String currentext;

    public DialogActionBar(Context context){
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_action_bar);

        dialog_action_bar_btn_0 = (Button)findViewById(R.id.dialog_action_bar_btn_0);
        dialog_action_bar_btn_1 = (Button)findViewById(R.id.dialog_action_bar_btn_1);
        dialog_action_bar_btn_2 = (Button)findViewById(R.id.dialog_action_bar_btn_2);
        dialog_action_bar_btn_3 = (Button)findViewById(R.id.dialog_action_bar_btn_3);
        dialog_action_bar_btn_4 = (Button)findViewById(R.id.dialog_action_bar_btn_4);
        dialog_action_bar_btn_5 = (Button)findViewById(R.id.dialog_action_bar_btn_5);
        dialog_action_bar_btn_6 = (Button)findViewById(R.id.dialog_action_bar_btn_6);
        dialog_action_bar_btn_7 = (Button)findViewById(R.id.dialog_action_bar_btn_7);
        dialog_action_bar_btn_8 = (Button)findViewById(R.id.dialog_action_bar_btn_8);
        dialog_action_bar_btn_9 = (Button)findViewById(R.id.dialog_action_bar_btn_9);
        dialog_action_bar_btn_back = (Button)findViewById(R.id.dialog_action_bar_btn_back);
        dialog_action_bar_btn_accept = (Button)findViewById(R.id.dialog_action_bar_btn_accept);
        dialog_action_bar_btn_exit = (Button)findViewById(R.id.dialog_action_bar_btn_exit);
        dialog_action_bar_text = (TextView)findViewById(R.id.dialog_action_bar_text);

        dialog_action_bar_btn_0.setOnClickListener(this);
        dialog_action_bar_btn_1.setOnClickListener(this);
        dialog_action_bar_btn_2.setOnClickListener(this);
        dialog_action_bar_btn_3.setOnClickListener(this);
        dialog_action_bar_btn_4.setOnClickListener(this);
        dialog_action_bar_btn_5.setOnClickListener(this);
        dialog_action_bar_btn_6.setOnClickListener(this);
        dialog_action_bar_btn_7.setOnClickListener(this);
        dialog_action_bar_btn_8.setOnClickListener(this);
        dialog_action_bar_btn_9.setOnClickListener(this);
        dialog_action_bar_btn_back.setOnClickListener(this);
        dialog_action_bar_btn_accept.setOnClickListener(this);
        dialog_action_bar_btn_exit.setOnClickListener(this);

        currentext="";


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_action_bar_btn_0:
                currentext+="0";
                break;
            case R.id.dialog_action_bar_btn_1:
                currentext+="1";
                break;
            case R.id.dialog_action_bar_btn_2:
                currentext+="2";
                break;
            case R.id.dialog_action_bar_btn_3:
                currentext+="3";
                break;
            case R.id.dialog_action_bar_btn_4:
                currentext+="4";
                break;
            case R.id.dialog_action_bar_btn_5:
                currentext+="5";
                break;
            case R.id.dialog_action_bar_btn_6:
                currentext+="6";
                break;
            case R.id.dialog_action_bar_btn_7:
                currentext+="7";
                break;
            case R.id.dialog_action_bar_btn_8:
                currentext+="8";
                break;
            case R.id.dialog_action_bar_btn_9:
                currentext+="9";
                break;
            case R.id.dialog_action_bar_btn_back:
                if(currentext.length()>0) {
                    currentext = currentext.substring(0, currentext.length() - 1);
                    dialog_action_bar_text.setText(currentext);
                }
                break;
            case R.id.dialog_action_bar_btn_accept:
                preftablenumWrite(currentext);
                GlobalVar.currentTable = currentext;
                dismiss();
                break;
            case R.id.dialog_action_bar_btn_exit:
                dismiss();
                break;
        }
        dialog_action_bar_text.setText(currentext);

    }
    public String sha1(String password) {
        int salt = 930817;
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i])+salt);
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void prefpasswordWrite(String password){
        SharedPreferences prefs = this.getContext().getSharedPreferences("BEERCRAFT", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("password", password);
        ed.commit();
    }
    public void preftablenumWrite(String tablenum){
        SharedPreferences prefs = this.getContext().getSharedPreferences("BEERCRAFT", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("tablenum", tablenum);
        ed.commit();
    }
    public String prefpasswordRead(String password){
        SharedPreferences prefs = this.getContext().getSharedPreferences("BEERCRAFT", Context.MODE_PRIVATE);
        String returnvalue = prefs.getString("password", "0000");
        return returnvalue;
    }
    public String preftablenumRead(String password){
        SharedPreferences prefs = this.getContext().getSharedPreferences("BEERCRAFT", Context.MODE_PRIVATE);
        String returnvalue = prefs.getString("tablenum", "001");
        return returnvalue;
    }

}
