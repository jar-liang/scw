package me.jar.scw.controller.util;

import me.jar.scw.manager.controller.util.ControllerUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Description controller工具类测试类
 * @Date 2020/6/13-12:49
 */
public class UtilTest {
    @Test
    public void testCheckId() {
        String id1 = "";
        String id2 = null;
        String id3 = "123456789987654321";
        String id4 = "da11";
        Assert.assertEquals("", ControllerUtils.checkId(id1));
        Assert.assertEquals("", ControllerUtils.checkId(id2));
        Assert.assertEquals("1234567899876543", ControllerUtils.checkId(id3));
        Assert.assertEquals("", ControllerUtils.checkId(id4));
    }

}
