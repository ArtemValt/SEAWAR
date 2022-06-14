package com.example.bot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class Ship {
    @Getter @Setter
    private int type;
    @Getter @Setter
    private String location;
    @Getter @Setter
    private int locaX;
    @Getter @Setter
    private int locaY;


    public Ship() {
    }


    public Ship(int type, String location, int locaX, int locaY) {
        this.type = type;
        this.location = location;
        this.locaX = locaX;
        this.locaY = locaY;


    }

    @Override
    public String toString() {
        return "Ship{" +
                "type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", locaX=" + locaX +
                ", locaY=" + locaY +
                '}';
    }


}
