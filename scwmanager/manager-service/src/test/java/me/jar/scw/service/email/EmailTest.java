package me.jar.scw.service.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

/**
 * @Description 测试email
 * @Date 2020/6/17-23:22
 */
public class EmailTest {
    @Test
    public void testSendEmail() throws EmailException {
        SimpleEmail email = new SimpleEmail();
        // 设置主机名，163可在网页客户端查
        email.setHostName("smtp.163.com");
        // 设置登录用户名、密码
        email.setAuthentication("liang1993hello@163.com", "BGRCIQIBBXVQXQNA");
        // 发送给谁（收件人）
        email.addTo("ljyan0304@163.com");
        //设置邮件来源
        email.setFrom("liang1993hello@163.com");
        // 设置邮件主题
        email.setSubject("你要的网页地址");
        // 设置邮件内容
        email.setMsg("想要吗，加我微信");

        // 右键发送
        email.send();
    }

    @Test
    public void testSendEmailViaJames() throws EmailException {
        SimpleEmail email = new SimpleEmail();
        // 设置主机名，163可在网页客户端查
        email.setHostName("jar.me");
        email.setSmtpPort(25);
        // 设置登录用户名、密码
        email.setAuthentication("zhangsan@jar.me", "123456");
        // 发送给谁（收件人）
        email.addTo("lisi@jar.me");
        //设置邮件来源
        email.setFrom("zhangsan@jar.me");
        // 设置邮件主题
        email.setSubject("你要的网页地址22");
        // 设置邮件内容
        email.setMsg("想要吗，加我微信22");
        // 右键发送
        email.send();
    }

    @Test
    public void testSendEmailViaJamesTo163() throws EmailException {
        SimpleEmail email = new SimpleEmail();
        // 设置主机名，163可在网页客户端查
        email.setHostName("127.0.0.1");
        email.setSmtpPort(25);
        // 设置登录用户名、密码
        email.setAuthentication("zhangsan@jar.me", "123456");
        email.setCharset("UTF-8");
        // 发送给谁（收件人）
        email.addTo("liang1993hello@163.com");
        //设置邮件来源
        email.setFrom("zhangsan@jar.me");
        // 设置邮件主题
        email.setSubject("通过James发给163");
        // 设置邮件内容
        email.setMsg("能看到发送的内容吗？");
        // 右键发送
        email.send();
    }
}
