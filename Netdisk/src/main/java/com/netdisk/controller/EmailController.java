package com.netdisk.controller;

import com.netdisk.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Random;


@RestController
public class EmailController {
    @Autowired
    EmailService emailService;

    private static final String SYMBOLS = "0123456789";
    private static final Random RANDOM = new SecureRandom();

    @PostMapping("/email")
    @ResponseBody
    public Object sendEmail(@RequestParam("emailAddress") String emailAddress) {
        try {
            String s = generateVerCode();
            emailService.sendEmailVerCode(emailAddress,s);
            return s;
        } catch (Exception e) {
            return "邮件发送失败";
        }
    }

    /**
     * 生成6位随机数字
     * @return 返回6位数字验证码
     */
    private String generateVerCode() {
        char[] nonceChars = new char[6];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
}

