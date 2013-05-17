package github.com.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static Calendar getCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
    }
    public static Date getDate() {
        return getCalendar().getTime();
    }
}
