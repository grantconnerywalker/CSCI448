package com.example.grantwalker.tictactoe;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by Andrew on 3/19/2015.
 */
public abstract class Cell extends Point  {
    public Cell(int x, int y) {
        super(x,y);
    }

    abstract public void draw(Canvas g,Resources res, int x, int y, int w, int h);
}
