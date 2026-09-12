package com.gudf.domain;

import java.util.ArrayList;

public class heroCharacter extends character{
    public ArrayList<String> skills;
    public int win;
    public heroCharacter(String name, int HP, int attack, int defense) {
        super(name, HP, attack, defense);
        this.skills = new ArrayList<String>();
        this.win = 0;
    }

    public heroCharacter() {
        super();
        this.skills = new ArrayList<String>();
        this.win = 0;
    }

    public String getSkills() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < skills.size(); i++) {
            sb.append(skills.get(i));
            if (i < skills.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
