package csci448.tictactoe;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
 

// Generic Cell class to be touched and changed in gameplay (can be empty, a cross, or a circle)
// Extends Point so we can make a grid of Cells easily
public abstract class Cell extends Point {
 
    public Cell(int x, int y) {
        super(x, y);
    }
 
    abstract public void draw(Canvas g,Resources res, int x, int y, int w, int h);
}
