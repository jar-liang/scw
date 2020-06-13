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
}
