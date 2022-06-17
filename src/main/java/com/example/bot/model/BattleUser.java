package com.example.bot.model;

import lombok.Getter;
import lombok.Setter;


public class BattleUser {
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private String name;
    @Setter
    @Getter
    public int whoWin = 0;
    @Getter
    @Setter
    private int[][] batllefield = new int[10][10];
    //3 для тестов , потом поставить 9
    @Getter
    @Setter
    public int countShips = 9;
    @Getter
    @Setter
    int countFourShip = 1;
    @Getter
    @Setter
    int countFreeShip = 2;
    @Getter
    @Setter
    int countDobleShip = 3;
    @Getter
    @Setter
    int countOneShip = 4;
    @Getter
    @Setter
    int countTrueShots = 1;


    public BattleUser(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public BattleUser() {
    }


}
