package csci448.tictactoe;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
 
// Cross Cell type to represent circle player moves  
public class Cross extends Cell {
 
    public Cross(int x, int y) {
        super(x, y);
    }
 
    // Draws Circle image in Cell
    public void draw(Canvas g, Resources res, int x, int y, int w, int h) {
        Bitmap im = BitmapFactory.decodeResource(res, R.drawable.cross);
        g.drawBitmap(im, null, new Rect(x*w, y*h, (x*w)+w, (y*h)+h), new Paint());
    }
 
    // Checks if there is a cross in a particular cell
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cross) {
            return true;
        } else {
            return false;
        }
    }
 
    @Override
    public String toString() {
        return "X";
    }
}
