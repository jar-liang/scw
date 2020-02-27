package me.jar.scw.connection;

import me.jar.project.commons.EncryptUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Description
 * @Date 2020/2/23-9:47
 */
public class Test2DB {

    @Test
    public void testConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scw?useSSL=false&serverTimezone=Asia/Shanghai", "root", "Mysql@123456");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scw?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai", "root", "Mysql@123456");
            System.out.println(conn);
        } catch (Exception e) {
            System.out.println("Test2DB.testConnetion : exception");
        }
    }

    @Test
    public void testEncrypt() {
        // 788b298d8a1a54c6ac53d724f94714320a91283322b993d76e1620f1b7c4ff93
        // 788b298d8a1a54c6ac53d724f94714320a91283322b993d76e1620f1b7c4ff93
        String shaCode = EncryptUtils.getSHACode("123456", "scw", "SHA-256");
        System.out.println(shaCode);
    }
}
