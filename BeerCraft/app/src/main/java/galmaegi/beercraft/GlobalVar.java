package galmaegi.beercraft;

import android.graphics.Typeface;

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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }
}