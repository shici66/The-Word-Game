package com.gudf.ui;

import com.gudf.domain.badCharacter;
import com.gudf.domain.heroCharacter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class fightGame {
    public void gameStart(String name) {
        heroCharacter hero = createHero(name);
        System.out.println("创建角色成功：" + hero.name);
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("🎮  "+hero.name+"欢迎来到文字格斗游戏 🎮");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\uD83C\uDF1F 初始属性:" + "[Hp:" + hero.HP + "/" + hero.maxHP + ",ATK:" + hero.attack + ",DEF:" + hero.defense + "]");
        System.out.println("\uD83C\uDF1F 初始技能:" + hero.getSkills());
//        添加小怪
//        |初级战士|80|15|10|猛击（150%伤害）|
//        |敏捷刺客|60|20|5|快速攻击（2次50%伤害）|
//        |重装坦克|120|10|20|防御姿态（下回合伤害减半） buff（ boolean defendding）|
//        |神秘法师|70|25|8|火球术（180%伤害）|
        ArrayList<badCharacter> badCharacters = new ArrayList<badCharacter>();
        badCharacters.add(new badCharacter("初级战士", 80, 15, 10, "猛击"));
        badCharacters.add(new badCharacter("敏捷刺客", 60, 20, 5, "快速攻击"));
        badCharacters.add(new badCharacter("重装坦克", 120, 10, 20, "防御姿态"));
        badCharacters.add(new badCharacter("神秘法师", 70, 25, 8, "火球术"));
        System.out.println("═══════════════════════════════════════");
        Random r = new Random();
        while (true) {
            if(hero.win>0){
                System.out.println("═══════════════════════════════════════");
            }
            if(hero.win>0){
                for (int i = 0; i < badCharacters.size(); i++) {
                   badCharacter bc =  badCharacters.get(i);
                   bc.maxHP += 10;
                   bc.HP = bc.maxHP;
                   bc.attack += 3;
                   bc.defense += 2;
                   bc.defenseAtt = false;
                }
            }
            int round = 1;
            badCharacter enemy = badCharacters.get(r.nextInt(badCharacters.size()));
            System.out.println("⚔️ 第 " + (hero.win + 1) + " 场战斗开始！对手: " + enemy.name);
            enemy.showStatus();
            System.out.println(getHealthBar(hero.name, hero.HP, hero.maxHP));
            System.out.println(getHealthBar(enemy.name, enemy.HP, enemy.maxHP));
            while (hero.HP > 0 && enemy.HP > 0) {
                System.out.println();

                System.out.println("========================================");
                System.out.println();
                if (round > 1) {
                    System.out.println(getHealthBar(hero.name, hero.HP, hero.maxHP));
                    System.out.println(getHealthBar(enemy.name, enemy.HP, enemy.maxHP));
                }
                System.out.println("======第" + round + "回合======");
                System.out.println("=====你的回合=====");
                Scanner sc = new Scanner(System.in);
                System.out.println("1." + hero.skills.get(0));
                System.out.println("2." + hero.skills.get(1) + " (消耗10HP)");
                System.out.println("3." + hero.skills.get(2) + " (消耗10HP,恢复生命)");
                System.out.println("选择行动(1-3):");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println();
                        System.out.print("\uD83D\uDCA5");
                        System.out.print("你对" + enemy.name + "使用了普通攻击！");
                        int dam = (int) (calculateDamage(hero.attack, enemy.defense));
                        System.out.print("造成" + dam + "点伤害！");
                        enemy.takeDamage(dam);
                    }
                    case 2 -> {
                        System.out.println();
                        System.out.print("\uD83D\uDCA5");
                        if (hero.HP >= 10) {
                            System.out.print("你对" + enemy.name + "使用了会心一击！");
                            hero.HP -= 10;
                            int dam = (int) (calculateDamage((int)(hero.attack*1.8), enemy.defense) );
                            System.out.print("消耗10HP,造成" + dam + "点伤害！");
                            enemy.takeDamage(dam);
                        } else {
                            System.out.println("体力不足，攻击失败");
                        }

                    }
                    case 3 -> {
                        System.out.println();
                        System.out.print("\uD83D\uDCA5");
                        System.out.print("你对" + enemy.name + "使用了生命汲取！");
                        if (hero.HP >= 10) {
                            hero.HP -= 10;
                            int healAmount = r.nextInt(20);
                            hero.heal(healAmount);
                            System.out.print("造成" + (int) (healAmount * 0.8) + "点伤害！");
                            int dam = (int) (healAmount * 0.8);
                            enemy.takeDamage(dam);
                            System.out.print("恢复" + healAmount + "点生命！");
                        } else {
                            System.out.println("体力不足，使用失败");
                        }
                    }
                    default -> {
                        System.out.println("输入错误,默认使用普通攻击！！");
                        int dam = (int) (calculateDamage(hero.attack, enemy.defense));
                        System.out.println();
                        System.out.print("\uD83D\uDCA5");
                        System.out.print("你对" + enemy.name + "使用了普通攻击！");
                        System.out.print("造成" + dam + "点伤害！");
                        enemy.takeDamage(dam);
                    }

                }
                System.out.println();
                if (!enemy.isAlive()) {
                    System.out.println(enemy.name + "已死亡！");
                    System.out.println("恭喜你，击败了" + enemy.name + "！");
                    hero.win++;
                    break;
                }
                enemyTurn(enemy, hero);
                if(!hero.isAlive()){
                    System.out.println();
                    break;
                }else{
                    round++;
                    continue;
                }
            }
            if (hero.isAlive()){
                System.out.println("\uD83D\uDC9A 战斗结束！你恢复了" + heal(hero) + "点生命值!");
                System.out.println("\uD83C\uDFC6 当前胜场:" + hero.win);
                System.out.println("═══════════════════════════════════════");
                Scanner sc = new Scanner(System.in);
                SaveManager.save(hero);
                System.out.print("继续下一场战斗？(y/n)");
                String choose = sc.next();
                if("y".equalsIgnoreCase(choose)){
                    continue;
                }else if ("n".equalsIgnoreCase(choose)){
                    System.out.println("游戏结束！");
                    System.out.println("你的总胜场为" + hero.win);
                    System.out.println("欢迎下次游玩！");
                    System.exit(0);
                }else{
                    System.out.println("未知输入，默认继续下一场战斗！");
                }
            }else{
                System.out.println("===================================================");
                System.out.println("你被" + enemy.name + "击败了！");
                System.out.println("游戏结束！");
                System.out.println("你的总胜场为" + hero.win);
                System.out.println("欢迎下次游玩！");
                System.exit(0);
            }
        }


    }
    public int heal(heroCharacter hero) {
        Random r = new Random();
        int healAmount = r.nextInt(20, 40);
        hero.heal(healAmount);
        return healAmount;
    }
    public void enemyTurn(badCharacter enemy, heroCharacter hero) {
        System.out.println("======敌人回合======");
        Random r = new Random();
        int enemyChoice = r.nextInt(2);
        String action = "普通攻击";
        if (enemyChoice == 0) {
            action = "普通攻击";
        } else {
            action = enemy.skill;
        }
//        猛击（150 % 伤害）|
//        速攻击（2 次50 % 伤害）|
//        防御姿态（下回合伤害减半）buff（boolean defendding）|
//        球术（180 % 伤害）|
        switch (action) {
            case "普通攻击" -> {
                System.out.print("\uD83D\uDCA5");
                System.out.print("敌人对" + hero.name + "使用了普通攻击！");
                int dam = (int) (calculateDamage(enemy.attack, hero.defense));
                System.out.print("造成" + dam + "点伤害！");
                hero.takeDamage(dam);
            }
            case "猛击" -> {
                System.out.print("\uD83D\uDCA5");
                System.out.print("敌人对" + hero.name + "使用了猛击！");
                int dam = (int) (calculateDamage((int)(enemy.attack*1.5), hero.defense) );
                System.out.print("造成" + dam + "点伤害！");
                hero.takeDamage(dam);
            }
            case "快速攻击" -> {
                System.out.print("\uD83D\uDCA5");
                System.out.print("敌人对" + hero.name + "使用了快速攻击！");
                int dam = (int) (calculateDamage((int)(enemy.attack*0.5), hero.defense) );
                System.out.println("共攻击了两次，造成" + dam*2 + "点伤害！");
                hero.takeDamage(dam*2);
            }
            case "防御姿态" -> {
                System.out.print("\uD83D\uDCA5");
                System.out.print("敌人对" + hero.name + "使用了防御姿态！");
                enemy.defenseAtt = true;
                System.out.print("下回合伤害减半！");
            }
            case "火球术" -> {
                System.out.print("\uD83D\uDCA5");
                System.out.print("敌人对" + hero.name + "使用了火球术！");
                int dam = (int) (calculateDamage((int)(enemy.attack*1.8), hero.defense) );
                System.out.print("造成" + dam + "点伤害！");
                hero.takeDamage(dam);
            }
        }
    }

    public heroCharacter createHero(String name) {
        int points = 20;
        Scanner sc = new Scanner(System.in);
        System.out.println("创建角色：" + name);
        String[] Attributes = new String[]{"生命力", "攻击力", "防御力"};
        int[] values = new int[3];
        System.out.println("请分配属性点共(" + points + ")：");
        for (int i = 0; i < Attributes.length; i++) {
            if (points <= 0) {
                System.out.println("属性点已分配完毕");
                break;
            }
            System.out.print("请输入" + Attributes[i] + "：");
            int value = sc.nextInt();
            System.out.println();
            if (value < 0) {
                System.out.println("属性点不能为负数，默认为0点");
                value = 0;
            }
            if (value > points) {
                System.out.println("属性点不能超过总点数，默认全部点数加至该属性");
                value = points;
            }
            points -= value;
            values[i] = value;
        }
        while (true) {

            if (points > 0) {
                System.out.println("剩余点数为：" + points);
                System.out.println("请选择将剩余点数加至哪个属性：1.生命力 2.攻击力 3.防御力");
                int val = sc.nextInt();
                if (val > 3 || val < 1) {
                    System.out.println("输入错误，请重新输入");
                    continue;
                }
                switch (val) {
                    case 1:
                        values[0] += points;
                        break;
                    case 2:
                        values[1] += points;
                        break;
                    case 3:
                        values[2] += points;
                        break;
                }
            }
            break;
        }
        heroCharacter hero = new heroCharacter(
                name,
                100 + values[0] * 10,
                10 + values[1] * 2,
                values[2]
        );
        hero.skills.add("普通攻击");
        hero.skills.add("会心一击");
        hero.skills.add("生命汲取");
        return hero;
    }

    public String getHealthBar(String name, int HP, int maxHP) {
//        zhangsan: [████████████████████] 100/100 HP
//        初级战士: [████████████████████] 80/80 HP
        int HealthBarLength = 20;
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": [");
        for (int i = 0; i < HealthBarLength; i++) {
            if (i < HP * HealthBarLength / maxHP) {
                sb.append("█");
            } else {
                sb.append("░");
            }
        }
        sb.append("] ").append(HP).append("/").append(maxHP).append(" HP");
        return sb.toString();
    }

    public int calculateDamage(int attack, int defense) {
        int damage = attack - defense;
        if (damage < 1) {
            damage = 1;
        }
        return damage;
    }
}