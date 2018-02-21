package travel.nanjing.com.travel.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.ColorRes;

/**
 * Created by gang on 2018/1/21.
 */

public class CommonUtil {

    /**
     * 获取颜色值
     *
     * @param context
     * @param colorResId
     * @return
     */
    public static int getColor(Context context, @ColorRes int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(colorResId, null);
        } else {
            return context.getResources().getColor(colorResId);
        }
    }

    /**
     * 是否通过wifi连接
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

}
