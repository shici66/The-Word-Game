package com.gudf.ui;

import com.gudf.domain.heroCharacter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveManager {
    public static void save(heroCharacter hero){
        try{
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("save_"+hero.name+".txt")
            );
            bw.write("name:"+hero.name);
            bw.newLine();
            bw.write("HP:"+hero.HP);
            bw.newLine();
            bw.write("maxHP:"+hero.maxHP);
            bw.newLine();
            bw.write("attack:"+hero.attack);
            bw.newLine();
            bw.write("defense:"+hero.defense);
            bw.newLine();
            bw.write("win:"+hero.win);
            bw.newLine();
            bw.close();
            System.out.println("自动存档成功");
        }catch(IOException e ){
            System.out.println("存档失败:"+e.getMessage());
        }
    }
}
