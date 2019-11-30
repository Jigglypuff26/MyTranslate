package hfad.com.mytranslate.weather.Common;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    public static final String APP_ID = "254fddfba925af4f86bd537088cb8437";
    public static Location current_location;

    public static String convertUnixDate(int dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd EEE MM yyy");
        String formatted = sdf.format(date);
        return formatted;
    }

    public static String convertUnixHour(int dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }
}
