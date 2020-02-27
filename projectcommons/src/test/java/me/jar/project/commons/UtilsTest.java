package me.jar.project.commons;

import org.junit.Test;

/**
 * @Description
 * @Date 2020/2/24-21:13
 */
public class UtilsTest {

    @Test
    public void testSHAEncrypt() {
        String shaCode = EncryptUtils.getSHACode("123456", "scw", "SHA-256");
        System.out.println(shaCode);
    }


}
