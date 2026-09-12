package com.gudf.domain;

public class character {
    public String name;
    public int HP;
    public int maxHP;
    public int attack;
    public int defense;

    public character(String name, int HP, int attack, int defense) {
        this.name = name;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
        this.defense = defense;
    }
    public character(){

    }
    public boolean isAlive(){
        return this.HP > 0;
    }
    public void heal(int amount) {
        this.HP += amount;
        if (this.HP > this.maxHP) {
            this.HP = this.maxHP;
        }
    }
    public void takeDamage(int amount) {
        this.HP -= amount;
        if (this.HP < 0) {
            this.HP = 0;
        }
    }

    public void showStatus() {
        System.out.print("Name: " + this.name);
        System.out.print(",HP: " + this.HP + "/" + this.maxHP);
        System.out.print(",Attack: " + this.attack);
        System.out.print(",Defense: " + this.defense);
        System.out.println();
    }

}
