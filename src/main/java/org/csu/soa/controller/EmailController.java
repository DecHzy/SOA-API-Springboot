package org.csu.soa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮箱的小工具
 *
 * @author huangzhangyan
 * @date 2022/7/4 14:13
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @PostMapping("/")
    public void sendEmail(@RequestParam String subject,
                          @RequestParam String text,
                          @RequestParam String to) {
        try {
            sentSimpleMail(subject, text, to);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    private static final String MAIL_HOST = "smtp.qq.com"; // 发送邮件的主机
    private static final String FROM = "925861023@qq.com"; // 发件人邮箱地址
    private static final boolean isDebug = false;  // debug选项 true会显示信息

    public static void main(String[] args) throws MessagingException {
        EmailController.sentSimpleMail("标题", "邮件内容", "1503409765@qq.com");
    }

    /**
     * 用qq邮箱发送一个简单邮件
     *
     * @param subject      邮件标题
     * @param text         邮件内容
     * @param toRecipients 接收邮件，逗号分隔
     * @author huangzhangyan
     * @date 2022/7/4 14:13
     */
    public static void sentSimpleMail(String subject, String text, String toRecipients)
            throws MessagingException {
        /*
         * 初始化JavaMail会话
         */
        Properties props = System.getProperties(); // 获得系统属性配置，用于连接邮件服务器的参数配置
        props.setProperty("mail.smtp.host", MAIL_HOST); // 发送邮件的主机
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, null);// 获得Session对象
        session.setDebug(isDebug); // 设置是否显示debug信息,true 会在控制台显示相关信息

        /*
         * 创建邮件消息，发送邮件
         */
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM));

        // To: 收件人
        // message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toRecipient));
        message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(toRecipients, false));
        // To: 增加收件人（可选）
        // message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toRecipient);
        // Cc: 抄送（可选）
        // message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(ccRecipient));
        // Bcc: 密送（可选）
        // message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(bccRecipient));

        message.setSubject(subject); // 邮件标题
        message.setText(text); // 邮件内容

        // 简单发送邮件的方式
        Transport.send(message, FROM, "tbnfoenynskgbdcj"); // 授权码
        System.out.println("邮件:" + text);
    }
}


