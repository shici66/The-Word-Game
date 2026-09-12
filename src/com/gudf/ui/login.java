package com.gudf.ui;

import com.gudf.domain.User;

import java.util.ArrayList;
import java.util.Scanner;

public class login {
    public void Start() {
        ArrayList<User> users = new ArrayList<>();

        while (true) {
            ui();
            Scanner sc = new Scanner(System.in);
            String num = sc.next();
            switch (num) {
                case "1" -> {
                    Login(users);
                }
                case "2" -> {
                    Register(users);
                }
                case "3" -> {
                    System.out.println("游戏正在退出!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("输入错误(请选择1-3)");
                }
            }
        }
    }

    public void Login(ArrayList<User> users) {
        if (users.size() == 0) {
            System.out.println("当前无用户，请先注册");
            return;
        }
        System.out.println("欢迎来到登录界面!!!!!!!");
        Scanner sc = new Scanner(System.in);

        int index = 0;
        String captchaCode;
        while (true) {
            boolean isUser = false;
            captchaCode = captcha.generateCaptcha();
            System.out.println("请输入用户名：(输入“0”返回上一级)");
            String username = sc.next();
            if (username.equals("0")) {
                return;
            }
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(username)) {
                    index = i;
                    isUser = true;
                    break;
                }
            }

            if (!isUser) {
                System.out.println("该用户不存在，请重新输入");
                continue;
            }
            if (!users.get(index).getState()) {
                System.out.println("账号" + users.get(index).getUsername() + "已冻结，请联系管理员");
                return;
            }
            while (true) {
                System.out.println("请输入验证码：" + captchaCode);
                String inputCaptcha = sc.next();
                if (inputCaptcha.equalsIgnoreCase(captchaCode)) {
                    System.out.println("验证码正确");
                    break;
                } else {
                    System.out.println("验证码错误，请重新输入");
                    captchaCode = captcha.generateCaptcha();
                    continue;
                }
            }
            System.out.println("请输入密码：");
            int num = 0;
            while (true) {
                String password = sc.next();
                if (users.get(index).getPassword().equals(password)) {
                    System.out.println("登录成功");
                    fightGame gh = new fightGame();
                    gh.gameStart(users.get(index).getUsername());
                    return;
                } else {
                    num++;
                    System.out.println("密码错误，请重新输入");
                    if (num == 3) {
                        System.out.println("密码错误次数过多，账号冻结");
                        users.get(index).setState(false);
                        return;
                    }
                    continue;
                }
            }
        }
    }


    public void Register(ArrayList<User> users) {
        System.out.println("欢迎来到注册界面!!!!!!!");
        User u = new User();
        String id = u.CreatId();
        System.out.println("您的id为" + id);
        Scanner sc = new Scanner(System.in);
        String username;
        while (true) {
            System.out.println("请输入用户名：");
            username = sc.next();
            if (username.length() < 3 || username.length() > 16) {
                System.out.println("用户名长度必须在3-16之间");
                continue;
            }
            int num = 0;
//            boolean allNum = false;
            boolean otherch = false;
            for (int i = 0; i < username.length(); i++) {
                if (username.charAt(i) - '0' >= 0 && username.charAt(i) - '0' <= 9) {
                    num++;
                }
            }
            if (num != username.length()) {
                for (int i = 0; i < username.length(); i++) {
                    char c = username.charAt(i);
                    boolean isNum = c - '0' >= 0 && c - '0' <= 9;
                    boolean isLetter = c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
                    if (!isNum && !isLetter) otherch = true;
                }
            } else if (num == username.length()) {
                System.out.println("用户名不能全为数字");
                continue;
            }
            if (otherch) {
                System.out.println("用户名不能包含特殊字符");
                continue;
            }
            boolean sameName = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(username)) {
                    System.out.println("用户名已存在");
                    sameName = true;
                    break;
                }
            }
            if (sameName) continue;
            u.setUsername(username);
            break;
        }
        String password1;
        String password2;
        while (true) {
            System.out.println("请输入密码：");
            password1 = sc.next();
            System.out.println("请再次输入密码：");
            password2 = sc.next();
            if (!password1.equals(password2)) {
                System.out.println("两次输入的密码不一致");
                continue;
            }
            if (password1.length() < 3 || password1.length() > 8) {
                System.out.println("密码长度必须在3-8之间");
                continue;
            }
            boolean isNum = false;
            boolean isLetter = false;
            boolean otherch = false;
            for (int i = 0; i < password1.length(); i++) {
                char c = password1.charAt(i);
                isNum = c - '0' >= 0 && c - '0' <= 9;
                isLetter = c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
                if (!isNum && !isLetter) {
                    otherch = true;
                }
            }
            if (otherch) {
                System.out.println("密码不能包含特殊字符");
                continue;
            }
            u.setPassword(password1);
            break;
        }
        System.out.println("用户名" + username + "注册成功");
        users.add(u);
    }

    public void ui() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║      🏰  文字冒险世界   🏰          ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║                                       ║");
        System.out.println("║         🔑  1. 登录                  ║");
        System.out.println("║         📝  2. 注册                  ║");
        System.out.println("║         🚪  3. 退出游戏              ║");
        System.out.println("║                                       ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.print("👉 请选择：");
    }

    public void showlist(ArrayList<User> users) {
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }
}

