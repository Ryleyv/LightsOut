package com.example.lightsout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * <!--class LightView-->
 *     this class is a custom surface view that draws the board according to
 *     the grid in LightModel
 *
 * @author Ryley Vargas
 */

public class LightView extends SurfaceView {

    private LightModel model = new LightModel();
    private Paint onSquare = new Paint();
    private Paint offSquare = new Paint();
    //the coordinates for all of the corners are the
    //same because the board is made up entirely of squares
    private float[] points = {0, 240, 480, 720, 960, 1200};

    /**
     * creates a new LightView, a custom surface view that draws a 5x5 grid of tiles.
     *
     * @param context Context object passed from SurfaceView
     * @param attrs AttributeSet object passed from SurfaceView
     */
    public LightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //why is this true by default?
        setWillNotDraw(false);
        //canvas set-up
        onSquare.setColor(Color.BLACK);
        onSquare.setStyle(Paint.Style.STROKE);
        offSquare.setColor(Color.BLACK);
        offSquare.setStyle(Paint.Style.FILL_AND_STROKE);
        //better than the default black
        setBackgroundColor(Color.WHITE);
    }
    //getters
    public LightModel getModel() {
        return model;
    }

    public float[] getCords() {
        return points;
    }


    //draws the grid using LightModel and the two paint objects above
    @Override
    public void onDraw(Canvas canvas) {
        for(int x = 0; x < model.grid.length; x++) {//iterate through each column
            for (int y = 0; y < model.grid[0].length; y++) {//iterate through each row
                if (model.grid[x][y] == true) {
                    //draw a lit-up square
                    canvas.drawRect(points[x], points[y], points[x+1], points[y+1], onSquare);
                } else {
                    //draw a filled-in square
                    canvas.drawRect(points[x], points[y], points[x+1], points[y+1], offSquare);
                }
            }
        }



    }
}
