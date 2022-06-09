package com.example.battleLogic.user;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private String name;
    private String password;
    @Getter
    @Setter
    private int[][] batllefield = new int[10][10];
    //3 для тестов , потом поставить 9
    @Getter
    @Setter
    public int countShips = 3;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
