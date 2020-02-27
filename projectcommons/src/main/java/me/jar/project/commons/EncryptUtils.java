package me.jar.project.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description
 * @Date 2020/2/24-21:09
 */
public class EncryptUtils {

    /**
     *  使用SHA算法加密
     * @param password 原始密钥
     * @param salt 盐值
     * @param algorithm 可选SHA-1/SHA-256/SHA-384/SHA-512
     * @return
     */
    public static String getSHACode(String password, String salt, String algorithm) {
        // algorithm加密算法可选SHA-1/SHA-256/SHA-384/SHA-512
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            // Get password mix salt，可以做多种组合，增加原始密码长度、复杂度
            password = salt + password + salt;
            // digest(byte[] input)使用指定的byte数组对摘要进行最后更新，然后完成摘要计算，此方法首先调用
            // update(input)，向update方法传递input数组，然后调用digest()。
            byte[] bytes = md.digest(password.getBytes()); // 如有中文，须添加字符集
            return byte2Hex(bytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("algorithm wrong");
            return null;
        }
    }

    /**
     *  将byte[]数组转换成16进制字符。一个byte生成两个字符，长度对应1:2
     * @param bytes，输入byte[]数组
     * @return 16进制字符
     */
    public static String byte2Hex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        // 遍历byte[]数组，将每个byte数字转换成16进制字符，再拼接起来成字符串
        for (int i = 0; i < bytes.length; i++) {
            // 每个byte转换成16进制字符时，bytes[i] & 0xff如果高位是0，输出将会去掉，所以+0x100(在更高位加1)，再截取后两位字符
            builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString();
    }

    /**
     *  将16进制字符转换成byte[]数组。与byte2Hex功能相反。
     * @param string 16进制字符串
     * @return byte[]数组
     */
    public static byte[] hex2Byte(String string) {
        if (string == null || string.length() < 1) {
            return null;
        }
        // 因为一个byte生成两个字符，长度对应1:2，所以byte[]数组长度是字符串长度一半
        byte[] bytes = new byte[string.length() / 2];
        // 遍历byte[]数组，遍历次数是字符串长度一半
        for (int i = 0; i < string.length() / 2; i++) {
            // 截取没两个字符的前一个，将其转为int数值
            int high = Integer.parseInt(string.substring(i * 2, i * 2 + 1), 16);
            // 截取没两个字符的后一个，将其转为int数值
            int low = Integer.parseInt(string.substring(i * 2 + 1, i * 2 + 2), 16);
            // 高位字符对应的int值*16+低位的int值，强转成byte数值即可
            // 如dd，高位13*16+低位13=221(强转成byte二进制11011101，对应十进制-35)
            bytes[i] = (byte) (high * 16 + low);
        }
        return bytes;
    }
}
