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

    @Test
    public void testCheckString() {
        String id1 = "";
        String id2 = null;
        String id3 = "123456789987654321";
        String id4 = "da11";
        Assert.assertEquals("", ControllerUtils.checkStringLength(id1, ControllerUtils.PARAM_LENGTH_MAX_16));
        Assert.assertEquals("", ControllerUtils.checkStringLength(id2, ControllerUtils.PARAM_LENGTH_MAX_16));
        Assert.assertEquals("1234567899876543", ControllerUtils.checkStringLength(id3, ControllerUtils.PARAM_LENGTH_MAX_16));
        Assert.assertEquals("da11", ControllerUtils.checkStringLength(id4, ControllerUtils.PARAM_LENGTH_MAX_16));
    }

    @Test
    public void testIsEmailLegal() {
        //1、正确数据
        //2、错误数据-只输入空白、空或者回车
        //3、错误数据-只输入字母
        //4、错误数据-只输入数字
        //5、错误数据-只输入特殊字符
        //6、错误数据-输入以上数据的组合
        //7、错误数据-缺少@符号，例如email163.com
        //8、错误数据-缺少黑点“.”，例如email@163com
        //9、错误数据-缺少com、cn、net、hotmail等，例如：email@163.
        //10、错误数据-@符号位置不正确，例如：email163@.com、email163.@com、email163.com@等
        //11、错误数据-黑点“.”位置不正确，例如：email.@163.com、email@.163com、email@163com.
        //12、错误数据-邮箱名长度小于最小值，或者大于最大值
        Assert.assertTrue(ControllerUtils.isEmailLegal("ljan03040@163.com"));
        Assert.assertFalse(ControllerUtils.isEmailLegal(""));
        Assert.assertFalse(ControllerUtils.isEmailLegal("ljancom"));
        Assert.assertFalse(ControllerUtils.isEmailLegal("03040163"));
        Assert.assertFalse(ControllerUtils.isEmailLegal("#)%,.@><"));
        Assert.assertFalse(ControllerUtils.isEmailLegal("ljancomm#)%,.@><03040163"));
        Assert.assertFalse(ControllerUtils.isEmailLegal("email163.com"));
        Assert.assertFalse(ControllerUtils.isEmailLegal("email@163com"));
        Assert.assertFalse(ControllerUtils.isEmailLegal("email@163."));
        Assert.assertFalse(ControllerUtils.isEmailLegal("email163@.com"));
        Assert.assertFalse(ControllerUtils.isEmailLegal("email@.163com"));
        Assert.assertFalse(ControllerUtils.isEmailLegal("ljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.comljan03040@163.com"));
    }
}
