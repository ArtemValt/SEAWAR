package com.example.bot.battleLogic.utils;

public class Utils {
    public String arrayToStr(int[][] field) {
        StringBuilder matrix = new StringBuilder();
        matrix.append("    0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 \n");
        for (int i = 0; i < 10; i++) {
            matrix.append(i + " ");
            for (int j = 0; j < 10; j++) {
                if (field[j][i] == 0) {
                    matrix.append("| - ");
                } else if (field[j][i] == 1) {
                    matrix.append("| ⛴");
                } else if (field[j][i] == 6) {
                    matrix.append("| ⚑ ");
                } else if (field[j][i] == 3) {
                    matrix.append("| ⚐ ");
                }
            }
            matrix.append("\n");
        }
        return matrix.toString();
    }

    public String incognitoArray(int[][] field) {
        StringBuilder matrix = new StringBuilder();
        matrix.append("    0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 \n");
        for (int i = 0; i < 10; i++) {
            matrix.append(i);
            for (int j = 0; j < 10; j++) {
                if (field[j][i] == 0) {
                    matrix.append("| ? ");
                } else if (field[j][i] == 1) {
                    matrix.append("| ? ");
                } else if (field[j][i] == 6) {
                    matrix.append("| ⚑ ");
                } else if (field[j][i] == 3) {
                    matrix.append("| ⚐ ");
                }
            }
            matrix.append("\n");
        }
        return matrix.toString();
    }
}
