package galmaegi.beercraft;

import android.graphics.Typeface;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 15. 11. 17.
 */
public class GlobalVar {
    public static Typeface NanumGothic_Bold;
    public static String currentTable;
    public static int realLoadingTime = 5*1000;

    public static Date StringToDate(String string) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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

    public static double SafetyJSONStringToDouble(JSONObject object, String key) throws JSONException {
        try {
            return Double.parseDouble(object.getString(key));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static String setComma(int value) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(value);
    }

    public static double Division(int num1, int num2) {
        double ret;
        try {
            ret = (double) num1 / num2 * 100;
        } catch (ArithmeticException e) {
            return 0;
        }

        return ret;
    }

    public static String DateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        try {
            return format.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
