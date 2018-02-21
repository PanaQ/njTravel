package travel.nanjing.com.travel.util;

/**
 * Created by zx on 2018/2/2 0002.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 文件名：DateUtils.java 日期处理相关工具类
 * 版本信息：V1.0
 */
public class DateUtils {

    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyyMM";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public
    static final String DATE_KEY_STR = "yyMMddHHmmss";

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个时间比较
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(Date date1) {
        Date date2 = new Date();
        int rnum = date1.compareTo(date2);
        return rnum;
    }

    /**
     * 两个时间比较(时间戳比较)
     *
     * @return 0: activityTime<=serverTime  今天
     * 1: activityTime>serverTime   明天
     * 2: activityTime>serverTime+1 后天以及以后
     */
    public static int compareDateWithNow(long activityTime, long serverTime) {

        int atTMonth = getMonth(new Date(activityTime));
        int atTDay = getDay(new Date(activityTime));

        int stTMonth = getMonth(new Date(serverTime));
        int stTDay = getDay(new Date(serverTime));

        if (atTMonth > stTMonth) {
            return 2;
        }

        if (atTDay > stTDay) {
            if (atTDay - stTDay > 1) {
                return 2;
            }
            return 1;
        } else {
            return 0;
        }

    }


    /**
     * 将指定的日期转换成Unix时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取日期
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getDateFormat(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        String timeText = String.format("%02d-%02d", calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1);
        return timeText;
    }

    public static String getTimeFormat(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        String timeText = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE));
        return timeText;
    }

    public static String getCountTime(long time) {
        long minute = time / 60;
        long second = time - minute * 60;
        String timeText = String.format("%02d:%02d", minute, second);
        return timeText;

    }

}
