package travel.nanjing.com.travel.util;

/**
 * Created by xubo on 2018/2/2.
 */

public class Constant {
    public final static String UNIT = "Rp.";

    public final static String RTMP_ADDRESS = "rtmp://push.hishendeng.com/app-cn-test1/transcode_msd";
//        public static String SERVER_ADDRESS = "http://192.168.30.193:8080";
    public static String SERVER_ADDRESS = "http://47.88.191.81:13030";
    public static String DANMU_WS_ADDRESS = "ws://47.88.191.81:6790?token=";
    public static String PEOPLE_WS_ADDRESS = "ws://47.88.191.81:6789?token=";

    public final static String shareLinks = SERVER_ADDRESS + "/share.html?inviteCode=";

    public final static String ruleLinks = SERVER_ADDRESS + "/rule.html?inviteCode=";

    public final static long DISPLAY_TIME = 15 * 1000;

    public final static String PRE_FIX_A = "A. ";
    public final static String PRE_FIX_B = "B. ";
    public final static String PRE_FIX_C = "C. ";

    public static Long timeDis = 0L;

}
