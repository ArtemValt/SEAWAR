package com.example.bot.battleLogic.battleship;


import com.example.bot.model.BattleUser;
import com.example.bot.model.Ship;
import lombok.Getter;
import lombok.Setter;

public class Battle {
    private static int deck = 4;
    @Getter
    @Setter
    private int countTrueShots = 3;

    private int i1 = 0;
    private int i2 = 0;
    private int i3 = 0;
    private int i4 = 0;


    final static int i = 10;
    @Getter
    @Setter
    int[][] battlefield1 = new int[i][i];
    @Getter
    @Setter
    int[][] battlefield2 = new int[i][i];


    public void placePlayerShips(BattleUser battleUser, Ship ship) {

        if (ship.getLocation() == null) {
            return;
        }
        int shipType = ship.getType();
        boolean loc;
        loc = isAvailable(ship.getLocaX(), ship.getLocaY(), shipType, ship.getLocation(), battleUser.getBatllefield());

        if (loc == true) {
            loc = chekCountShips(battleUser, ship);
            if (loc == true) {
                if (battleUser.countShips != 0) {
                    if (battleUser.getType().equals("1")) {
                        battlefield1 = makeTurn(shipType, battlefield1, ship);
                        battleUser.setBatllefield(battlefield1);
                    } else if (battleUser.getType().equals("2")) {
                        battlefield2 = makeTurn(shipType, battlefield2, ship);
                        battleUser.setBatllefield(battlefield2);
                    }
                }
                battleUser.countShips--;
            }
        }
    }

    public boolean chekCountShips(BattleUser battleUser, Ship ship) {
        switch (ship.getType()) {
            case 1 -> {
                i1 = battleUser.getCountOneShip();
                battleUser.setCountOneShip(++i1);
                if (battleUser.getCountOneShip() <= 4) {
                } else
                    return false;

            }
            case 2 -> {
                i2 = battleUser.getCountDobleShip();
                battleUser.setCountDobleShip(++i2);
                if (battleUser.getCountDobleShip() <= 3) {
                } else
                    return false;

            }
            case 3 -> {
                i3 = battleUser.getCountFreeShip();
                battleUser.setCountFreeShip(++i3);
                if (battleUser.getCountFreeShip() <= 2) {
                } else
                    return false;

            }
            case 4 -> {
                i4 = battleUser.getCountFourShip();
                battleUser.setCountFourShip(++i4);
                if (battleUser.getCountFourShip() <= 1) {
                } else
                    return false;

            }
            default -> {
                return false;
            }
        }
        return true;
    }

    public int[][] makeTurn(int deck, int[][] field, Ship ship) {
        for (int z = 0; z < deck; z++) {
            if (ship.getLocation().equals("v")) {
                field[(ship.getLocaX())][(ship.getLocaY() + z)] = 1;
            } else if (ship.getLocation().equals("x"))
                field[(ship.getLocaX() + z)][(ship.getLocaY())] = 1;
        }
        return field;
    }

    public int[][] makeShoot(int x, int y, int[][] field, BattleUser battleUser) {
        if (field[x][y] == 1) {
            field[x][y] = 6;
            countTrueShots = 1;
            battleUser.whoWin++;
            battleUser.setCountTrueShots(countTrueShots);
        } else if (field[x][y] == 6) {
            field[x][y] = 6;
            countTrueShots = 0;
        } else {
            field[x][y] = 3;
            countTrueShots = 0;
            battleUser.setCountTrueShots(countTrueShots);
        }
        if (battleUser.getType().equals("1"))
            setBattlefield2(field);
        else if (battleUser.getType().equals("2"))
            setBattlefield1(field);
        return field;
    }

    public boolean whoWin(BattleUser battleUser) {
        if (battleUser.whoWin == 20)
            return true;
        return false;
    }

    public static boolean isAvailable(int x, int y, int deck, String str, int[][] battlefield) {
        if (str.equals("v")) {
            if (y + deck > battlefield.length) {
                return false;
            }
        }
        if (str.equals("x")) {
            if (x + deck > battlefield[0].length) {
                return false;
            }
        }

        while (deck != 0) {
            for (int i = 0; i < deck; i++) {
                int xi = 0;
                int yi = 0;
                if (str.equals("v")) {
                    yi = i;
                } else {
                    xi = i;
                }
                if (x + 1 + xi < battlefield.length && x + 1 + xi >= 0) {
                    if (battlefield[x + 1 + xi][y + yi] != 0) {
                        return false;
                    }
                }
                if (x - 1 + xi < battlefield.length && x - 1 + xi >= 0) {
                    if (battlefield[x - 1 + xi][y + yi] != 0) {
                        return false;
                    }
                }
                if (y + 1 + yi < battlefield.length && y + 1 + yi >= 0) {
                    if (battlefield[x + xi][y + 1 + yi] != 0) {
                        return false;
                    }
                }
                if (y - 1 + yi < battlefield.length && y - 1 + yi >= 0) {
                    if (battlefield[x + xi][y - 1 + yi] != 0) {
                        return false;
                    }
                }
            }
            deck--;
        }
        return true;
    }

}
