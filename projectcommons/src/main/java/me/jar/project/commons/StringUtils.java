package me.jar.project.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 常用String工具类
 * @Date 2020/2/15-16:48
 */
public class StringUtils {

    /**
     *  判断字符串是否为null或""
     * @param string
     * @return
     */
    public static boolean isEmptyString(String string) {
        if (string == null) {
            return true;
        }
        if ("".equals(string)) {
            return true;
        }
        return false;
    }

    /**
     *  将date转换成字符串
     * @param date
     * @return
     */
    public static String timeToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
