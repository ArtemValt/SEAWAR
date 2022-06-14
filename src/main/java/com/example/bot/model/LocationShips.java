package com.example.bot.model;

public class LocationShips {
    private static int countFourShip =1;
    private static int countFreeShip =2;
    private static int countDobleShip =3;
    private static int countOneShip =4;

    public static int getCountFourShip() {
        return countFourShip;
    }

    public static void setCountFourShip(int countFourShip) {
        LocationShips.countFourShip = countFourShip;
    }

    public static int getCountFreeShip() {
        return countFreeShip;
    }

    public static void setCountFreeShip(int countFreeShip) {
        LocationShips.countFreeShip = countFreeShip;
    }

    public static int getCountDobleShip() {
        return countDobleShip;
    }

    public static void setCountDobleShip(int countDobleShip) {
        LocationShips.countDobleShip = countDobleShip;
    }

    public static int getCountOneShip() {
        return countOneShip;
    }

    public static void setCountOneShip(int countOneShip) {
        LocationShips.countOneShip = countOneShip;
    }
}
