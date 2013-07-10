package github.com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class DateUtil {

    private static final ThreadLocal<Map<String, DateFormat>> DATE_FORMATS = new ThreadLocal<Map<String, DateFormat>>() {
        @Override
        protected Map<String, DateFormat> initialValue() {
            return new HashMap<String, DateFormat>();
        }
    };
    
    /**
     * デフォルトタイムゾーン
     */
    public static final String DEFAULT_TIMEZONE = "UTC";

    /**
     * デフォルトタイムゾーンカレンダー格納ThreadLocal
     */
    private static final ThreadLocal<Calendar> DEFAULT_TZ_CAL = new ThreadLocal<Calendar>() {
        @Override
        protected Calendar initialValue() {
            return Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        }
    };

    public static Calendar getCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
    }
    public static Date getDate() {
        return getCalendar().getTime();
    }
    /**
     * 日付を文字列に変換する。
     * @param date 日時
     * @param format フォーマット
     * @param timezone タイムゾーン
     * @return 日付文字列
     */
    public static String toString(Date date, String format) {
        if (date == null)
            throw new IllegalArgumentException("date must not be null.");
        if (format == null)
            throw new IllegalArgumentException("format must not be null.");
        DateFormat dateFormat = findDateFormat(format, TimeZone.getTimeZone("Asia/Tokyo"));
        return dateFormat.format(date);
    }
    /**
     * 文字列をパースして日付にする。
     * @param dateString 日付文字列
     * @param format フォーマット
     * @param timezone タイムゾーン
     * @return 日付
     */
    public static Date toDate(String dateString, String format) {
        if (dateString == null)
            throw new IllegalArgumentException("dateString must not be null.");
        if (format == null)
            throw new IllegalArgumentException("format must not be null.");
        DateFormat dateFormat = findDateFormat(format, TimeZone.getTimeZone("Asia/Tokyo"));
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Parse failed.", e);
        }
    }
    /**
     * 日付フォーマットクラスを検索する。なければ作成する。
     * @param format 日付フォーマット
     * @param timezone タイムゾーン
     * @return 日付フォーマット
     */
    private static DateFormat findDateFormat(String format, TimeZone timezone) {
        DateFormat dateFormat = DATE_FORMATS.get().get(format);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(format);
            DATE_FORMATS.get().put(format, dateFormat);
        }
        dateFormat.setTimeZone(timezone);
        return dateFormat;
    }
    
    /**
     * Date型に対して「日」を加算する(マイナス指定で減算する)
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = DEFAULT_TZ_CAL.get();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 与えられた時間（時）の加減を行う
     * 
     * @param date
     * @param hour
     * @return 加減後のDate
     */
    public static Date addHour(Date date, int hour) {
        Calendar cal = DEFAULT_TZ_CAL.get();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * 与えられた時間（分）の加減を行う
     * 
     * @param date
     * @param minute
     * @return 加減後のDate
     */
    public static Date addMinute(Date date, int minute) {
        Calendar cal = DEFAULT_TZ_CAL.get();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }
}
