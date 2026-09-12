package com.gudf.domain;

public class badCharacter extends character{
    public boolean defenseAtt;
    public String skill;
    public badCharacter(String name, int HP, int attack, int defense, String skill) {
        super(name, HP, attack, defense);
        this.skill = skill;
    }

    public badCharacter() {

    }

    @Override
    public void takeDamage(int amount) {
        if(defenseAtt){
            amount = amount /2;
            if(amount < 1 ) amount = 1;
            defenseAtt = false;
        }
        super.takeDamage(amount);
    }
}
