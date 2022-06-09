package com.example.battleLogic.battleship.Ships;

import org.springframework.stereotype.Component;

@Component
public class Ship {

    private int type;
    private String location;
    private int locaX;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLocaX() {
        return locaX;
    }

    public void setLocaX(int locaX) {
        this.locaX = locaX;
    }

    public int getLocaY() {
        return locaY;
    }

    public void setLocaY(int locaY) {
        this.locaY = locaY;
    }
}
