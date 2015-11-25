package galmaegi.beercraft;

import android.graphics.Typeface;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 15. 11. 17.
 */
public class GlobalVar {
    public static Typeface NanumGothic_Bold;
    public static String currentTable;

    public static Date StringToDate(String string) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            return format.parse(string);
        } catch (Exception e) {
            return new Date();
        }
    }

    public static int SafetyJSONStringToInt(JSONObject object, String key) throws JSONException {
        try {
            return Integer.parseInt(object.getString(key));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
