package com.gudf.ui;

import java.util.Random;

public class captcha {
//    验证码规则:
//    长度为5
//
//            由4位大写或者小写字母和1位数字组成,同一个字母可重复
//    数字可以出现在任意位置
//    比如:aQa1K
    public static String generateCaptcha() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int random = r.nextInt(2);
            if (random==0) {
                sb.append((char) (r.nextInt(26) + 'A'));
            }else if(random == 1){
                sb.append((char) (r.nextInt(26) + 'a'));
            }
        }
        sb.insert(r.nextInt(5), r.nextInt(10));
        return sb.toString();
    }
}
