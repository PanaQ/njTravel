package travel.nanjing.com.travel.util;

import java.text.DecimalFormat;

/**
 * Created by xubo on 2018/2/2.
 */

public class InniNumberFormat {

    public static String getFormatNumber(Double number) {
        String purchaseNum = "";
        if (number == null || number == 0) {
            return "0";
        }
        if (number > 999.0) {
            double amount = Double.parseDouble(number.toString());
            DecimalFormat formatter = new DecimalFormat("####,000");
            purchaseNum = formatter.format(amount);
        } else {
            purchaseNum = number.toString();
        }
        return purchaseNum;
    }

    public static String rankFormat(Double number) {
        String purchaseNum = "";
        if (number == null || number == 0) {
            return "0";
        }
        if (number > 10000000) {
            return (int) (number / 10000000) + "k";
        } else {
            purchaseNum = getFormatNumber(number);
        }
        return purchaseNum;
    }

}
