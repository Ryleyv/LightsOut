package com.example.lightsout;

/**
 *<!--class LightModel-->
 *     this class holds the boolean array that represents the board used in this lights out game
 *     it also allows for the board to be reset and re-randomized
 */

public class LightModel {

    public boolean[][] grid = new boolean[5][5];

    public LightModel() {
        boardSetUp();
    }

    /**
     *  randomly sets each grid square to true or false
     */
    public void boardSetUp() {
        for (int k = 0; k < grid.length; k++) {
            for (int l = 0; l < grid[0].length; l++) {
                double num = Math.random();
                if (num < 0.5) {
                    grid[k][l] = true;
                } else {
                    grid[k][l] = false;
                }
            }
        }
    }
}

