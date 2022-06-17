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
    @Getter
    @Setter
    public int countShips = 10;
    @Getter
    @Setter
    int countFourShip = 0;
    @Getter
    @Setter
    int countFreeShip = 0;
    @Getter
    @Setter
    int countDobleShip = 0;
    @Getter
    @Setter
    int countOneShip = 0;
    @Getter
    @Setter
    int countTrueShots = 3;


    public BattleUser(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public BattleUser() {
    }


}
