package galmaegi.beercraft;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by root on 15. 11. 17.
 */
public class DialogLanguage extends Dialog implements View.OnClickListener{

    Button btn_kor;
    Button btn_eng;

    public DialogLanguage(Context context){
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_change_language);

        btn_kor = (Button)findViewById(R.id.btn_kor);
        btn_eng = (Button)findViewById(R.id.btn_eng);

        btn_kor.setOnClickListener(this);
        btn_eng.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_kor:
                Toast.makeText(this.getContext(),"한국어로 설정되었습니다.",Toast.LENGTH_SHORT).show();
                GlobalVar.language = Locale.KOREAN;
                dismiss();
                break;
            case R.id.btn_eng:
                Toast.makeText(this.getContext(),"Language is set to English",Toast.LENGTH_SHORT).show();
                GlobalVar.language = Locale.ENGLISH;
                dismiss();
                break;
        }

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
