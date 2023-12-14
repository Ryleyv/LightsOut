package com.example.lightsout;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
/**
 <!--Class LightController-->

 this class manages the user input and game logic for this lights out game
 @Author Ryley Vargas
 */
public class LightController implements View.OnTouchListener, View.OnClickListener{

    private LightView view;
    private LightModel model;
    private boolean gameOver = false;
    private float[] cords;

    /**
     *creates a LightController object and assings it the proper model and view
     *
     * @param lightView the lightView currently active in the xml layout
     */
    public LightController(LightView lightView) {
        view = lightView;
        model = view.getModel();
        cords = view.getCords();
    }

    @Override
    public void onClick(View v) {
        reset();
    }
    /**
     * when the surface view is touched, if the game is still going, calculate which square was touched
     * and use that information to update the model
     *
     * @param event MotionEvent passed from OnTouchListener
     *
     * @param v View passed from OnTouchListener
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //can't interact with the LightView if the game is over
        if (gameOver) {
            return false;
        }
        //check for where on the grid there was a click
        //update the corresponding values in grid
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            for (int k = 5; k > 0; k--) {//iterate through columns
                for (int l = 5; l > 0; l--) {//iterate through rows
                    if (x < cords[k] && x > cords[k - 1]) {
                        // Log.e("X placement: ", "x: " + k);
                        if (y < cords[l] && y > cords[l - 1]) {
                            flipTiles(k-1, l-1);
                            view.invalidate();
                        }
                    }
                }

            }
        }
        if (!checkWin()) {
            gameWin();
        }
        return true;
    }

    /**
     * takes in the coordinates of which tile was clicked and updates the model appropriately
     * @param x which column of the grid was tapped
     * @param y which row of the grid was tapped
     */
    private void flipTiles(int x, int y) {
        if (model.grid[x][y] == false) {
            model.grid[x][y] = true;
        } else {
            model.grid[x][y] = false;
        }
        if (y-1 >= 0 && model.grid[x][y-1] == false) {
            model.grid[x][y-1] = true;
        } else if (y-1 >= 0)  {
            model.grid[x][y-1] = false;
        }
        if (y+1 < model.grid[0].length && model.grid[x][y+1] == false) {
            model.grid[x][y+1] = true;
        } else if (y+1 < model.grid[0].length){
            model.grid[x][y+1] = false;
        }
        if (x-1 >= 0 && model.grid[x-1][y] == false) {
            model.grid[x-1][y] = true;
        } else if (x-1 >= 0) {
            model.grid[x-1][y] = false;
        }
        if (x+1 < model.grid.length && model.grid[x+1][y] == false) {
            model.grid[x+1][y] = true;
        } else if (x+1 < model.grid.length){
            model.grid[x+1][y] = false;
        }
    }

    /**
     * checks if there are any lit tiles left on the board
     * @return returns true if there are lit tiles and false if not
     */
    private boolean checkWin() {
        //testCase is false unless there is still an element of the grid that is still true
        boolean testCase = false;
        for (int k = 0; k < model.grid.length; k++) {
            for (int l = 0; l < model.grid[0].length; l++) {
                if (model.grid[k][l] == true) {
                    testCase = true;
                }
            }
        }
        return testCase;
    }

    /**
     * once the game is won update the graphics and freeze the board
     */
    private void gameWin() {
        gameOver = true;
        view.setBackgroundColor(Color.GREEN);
    }

    /**
     * resets the grid tiles, resets the graphics, un-freezes the board
     */
    private void reset() {
        model.boardSetUp();
        view.setBackgroundColor(Color.WHITE);
        view.invalidate();
        gameOver = false;
    }
}
