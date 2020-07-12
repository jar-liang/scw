package me.jar.scw.manager.controller.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @Description controller的工具类
 * @Date 2020/6/13-12:35
 */
public final class ControllerUtils {
    private ControllerUtils() {
        // 私有构造，防止工具类实例化
    }

    public static final int PARAM_LENGTH_MAX_16 = 16;
    public static final int PARAM_LENGTH_MAX_256 = 256;

    /**
     *  对传入的id进行校验，长度不大于16，大于16的截断，再使用正则校验，全部都要是数字
     * @param id
     * @return
     */
    public static String checkId(String id) {
        if (StringUtils.isEmpty(id)) {
            return StringUtils.EMPTY;
        }
        String idParam = id;
        if (id.length() > PARAM_LENGTH_MAX_16) {
            idParam = id.substring(0, PARAM_LENGTH_MAX_16);
        }
        if (Pattern.matches("\\d+", idParam)) {
            return idParam;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 对字符串进行长度校验，如果长度不超过给出的最大的长度，返回该字符串，如果
     * 超过，则按限制长度进行截取
     * @param inputString
     * @param maxStringLength
     * @return
     */
    public static String checkStringLength(String inputString, int maxStringLength) {
        if (StringUtils.isEmpty(inputString)) {
            return StringUtils.EMPTY;
        }
        return inputString.length() > maxStringLength ? inputString.substring(0, maxStringLength) : inputString;

    }

    /**
     *  检验邮箱合法性
     * @param email
     * @return
     */
    public static boolean isEmailLegal(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        if (Pattern.matches("^[a-z0-9A-Z][- | a-z0-9A-Z_.]{1,64}@[a-z0-9A-Z][a-zA-Z0-9.-]{1,64}\\.[a-zA-Z]{1,5}$", email)) {
            return true;
        }
        return false;
    }
}
