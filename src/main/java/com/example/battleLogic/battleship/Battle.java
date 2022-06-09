package com.example.battleLogic.battleship;


import com.example.battleLogic.user.User;
import com.example.battleLogic.battleship.Ships.Ship;
import lombok.Getter;
import lombok.Setter;

public class Battle {
    public int countTrueShots = 0;
    final static int i = 10;
    @Getter
    @Setter
    int[][] battlefield1 = new int[i][i];
    @Getter
    @Setter
    int[][] battlefield2 = new int[i][i];

    public void placePlayerShips(User user, Ship ship) {

        if (ship.getLocation() == null) {
            return;
        }
        int deck = ship.getType();
        boolean loc = checkPos(ship, user);
        if (loc == true) {
            if (user.getName().equals("1")) {
                battlefield1 = makeTurn(deck, battlefield1, ship);
                user.setBatllefield(battlefield1);
            } else if (user.getName().equals("2")) {
                battlefield2 = makeTurn(deck, battlefield2, ship);
                user.setBatllefield(battlefield2);
            }
            user.countShips--;
        }
    }


    public boolean checkPos(Ship ship, User user) {
        int deck = ship.getType();
        boolean loc = true;
        for (int i = 0; i < deck; i++) {
            if (ship.getLocation().equals("v")) {
                if (user.getName().equals("1")) {
                    if (!chekTurn(ship.getLocaX(), ship.getLocaY() + i, battlefield1)) {
                        loc = false;
                        break;
                    } else if (user.getName().equals("2")) {
                        if (!chekTurn(ship.getLocaX(), ship.getLocaY() + i, battlefield2)) {
                            loc = false;
                            break;
                        }
                    }
                }
            } else if (ship.getLocation().equals("x")) {
                if (user.getName().equals("1")) {
                    if (!chekTurn(ship.getLocaX(), ship.getLocaY() + i, battlefield1)) {
                        loc = false;
                        break;
                    }
                } else if (user.getName().equals("2")) {
                    if (!chekTurn(ship.getLocaX(), ship.getLocaY() + i, battlefield2)) {
                        loc = false;
                        break;
                    }
                }
            }
        }

        return loc;
    }


    //Нужно исправить баг с выходом за границы массива
    public boolean chekTurn(int x, int y, int[][] field) {
        try {
            if (field[x][y] == 1 || field[x - 1][y] == 1 || field[x][y + 1] == 1
                    || field[x + 1][y] == 1 || field[x][y - 1] == 1
                    || field[x - 1][y + 1] == 1 || field[x - 1][y - 1] == 1
                    || field[x + 1][y - 1] == 1 || field[x + 1][y + 1] == 1)
                return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
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

    public int[][] makeShoot(int x, int y, int[][] field, User user) {
        countTrueShots = 0;
        if (field[x][y] == 1) {
            field[x][y] = 6;
            countTrueShots++;
        } else if (field[x][y] == 6)
            field[x][y] = 6;
        else {
            field[x][y] = 3;
        }
        if (user.getName().equals("1"))
            setBattlefield2(field);
        else if (user.getName().equals("2"))
            setBattlefield1(field);
        return field;
    }


}
