package me.jar.scw.manager.service.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @Description service层工具类
 * @Date 2020/7/15-22:27
 */
public final class ServiceUtils {
    private ServiceUtils() {
        // 防止工具类被实例化
    }

    /**
     *  给用户邮箱发送找回密码的链接
     * @param userEmail
     * @param url
     */
    public static void sendEmailForFindPwd(String userEmail, String url) {
        // 后续可考虑通过配置文件，灵活添加邮件信息
        try {
            SimpleEmail email = new SimpleEmail();
            // 设置主机名
            email.setHostName("jar.me");
            email.setSmtpPort(25);
            // 设置登录用户名、密码
            email.setAuthentication("zhangsan@jar.me", "123456");
            // 发送给谁（收件人）
            email.addTo(userEmail);
            //设置邮件来源
            email.setFrom("zhangsan@jar.me");
            // 设置邮件主题
            email.setSubject("尚筹网找回密码");
            // 设置邮件内容
            email.setMsg("请点击链接重置密码 " + url);
            // 发送
            email.send();
        } catch (EmailException e) {
            System.out.println("send email fail" + e.getMessage());
        }
    }
}
