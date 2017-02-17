package csci448.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
 
// Game class holds all the logic for the TicTacToe gameplay
public class Game extends View {
 
    private boolean isDrawn = false;
    private int playerwin = 3;
    private Paint painObj;
    private Cell[][] particularCell = null;
    int x = 3;
    int y = 3;
    private int l;
    private int a;
 
    Handler handler = new Handler() {
    public Game(Context context) {
        super(context);
 
        painObj = new Paint();
 
        this.painObj.setARGB(255, 0, 0, 0);
        this.painObj.setAntiAlias(true);
        this.painObj.setStyle(Style.STROKE);
        this.painObj.setStrokeWidth(5);
 
        l = this.getWidth();
        a = this.getHeight();
 
        particularCell = new Cell[x][y];
 
        int xss = l / x;
        int yss = a / y;
 
        for (int z = 0; z < y; z++) {
            for (int i = 0; i < x; i++) {
                particularCell[z][i] = new Empty(xss * i, z * yss);
            }
        }
    }
        // @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 0:
                invalidate();
                break;
            case 1:
                Toast.makeText(getContext(), "Player Circle Wins!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "Player Cross Wins!", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getContext(), "No One Wins - Muhahaha!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
            }
 
            super.handleMessage(msg);
        }
    };
 
    public int getGameSize() {
        return x;
    }
 
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int xNew = (int) (event.getX() / (this.getWidth() / x));
        int yNew = (int) (event.getY() / (this.getHeight() / y));
        drawImage(xNew, yNew);
        return super.onTouchEvent(event);
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < particularCell.length; i++) {
            for (int j = 0; j < particularCell[0].length; j++) {
                particularCell[i][j].draw(canvas, getResources(), j, i, (this
                        .getWidth() + 3)
                        / particularCell.length, this.getHeight()
                        / particularCell[0].length);
            }
        }
 
        int xs = this.getWidth() / x;
        int ys = this.getHeight() / y;
        for (int i = 0; i <= x; i++) {
            canvas.drawLine(xs * i, 0, xs * i, this.getHeight(), painObj);
        }
        for (int i = 0; i <= y; i++) {
            canvas.drawLine(0, ys * i, this.getWidth(), ys * i, painObj);
        }
 
        super.onDraw(canvas);
    }
 
 
    public String getShape(int player) {
        switch (player) {
        case 1:
            return "x";
        case -1:
            return "o";
        }
        return null;
    }
 
    public void drawImage(int xNew, int yNew) {
        Cell c = null;
        if (isDrawn) 
        {
            c = new Cross(particularCell[xNew][yNew].x,particularCell[xNew][yNew].y);
            isDrawn = false;
        } 
        else
        {
            c = new Circle(particularCell[xNew][yNew].x, particularCell[xNew][yNew].y);
            isDrawn = true;
        }
 
        particularCell[yNew][xNew] = c;
 
        handler.sendMessage(Message.obtain(handler, 0));
 
        if (validate_game()) {
            if (isDrawn) {
                System.out.println("You Win");
                handler.sendMessage(Message.obtain(handler, 1));
            } else {
                System.out.println("Computer Win");
                handler.sendMessage(Message.obtain(handler, 2));
            }
            resizegame(x);
             
        } else if (isFull()) {
            System.out.println("Loose");
            handler.sendMessage(Message.obtain(handler, 3));
            resizegame(x);
         
        }
    }
 
    private boolean validate_game() {
        int anInt = 0;
        Cell inner = null;
 
        for (int i = 0; i < particularCell.length; i++) {
            for (int j = 0; j < particularCell[0].length; j++) {
                System.out.print(particularCell[i][j]);
                if (!particularCell[i][j].equals(inner)
                        || particularCell[i][j] instanceof Empty) {
 
                    inner = particularCell[i][j];
                    anInt = 0;
                } else {
                    anInt++;
                }
                if (anInt >= getPlayerwin() - 1) {
                    return true;
                }
 
            }
            System.out.println("");
            inner = null;
            anInt = 0;
        }
 
        inner = null;
        for (int j = 0; j < particularCell[0].length; j++) {
            for (int i = 0; i < particularCell.length; i++) {
                System.out.print(particularCell[i][j]);
                if (!particularCell[i][j].equals(inner)
                        || particularCell[i][j] instanceof Empty) {
                    inner = particularCell[i][j];
                    anInt = 0;
                } else {
                    anInt++;
                }
 
                if (anInt >= getPlayerwin() - 1) {
                    return true;
                }
 
            }
            System.out.println("");
            inner = null;
            anInt = 0;
        }
 
        inner = null;
        for (int j = particularCell[0].length - 1; j >= 0; j--) {
            int yau = 0;
            for (int z = j; z < particularCell[0].length; z++) {
                if (!particularCell[yau][z].equals(inner)
                        || particularCell[yau][z] instanceof Empty) {
                    inner = particularCell[yau][z];
                    anInt = 0;
                } else {
                    anInt++;
                }
 
                if (anInt >= getPlayerwin() - 1) {
                    return true;
                }
                yau++;
            }
            anInt = 0;
            inner = null;
        }
 
        inner = null;
        for (int j = 0; j < particularCell[0].length; j++) {
            int yau = 0;
            for (int z = j; z >= 0; z--) {
                if (!particularCell[yau][z].equals(inner)
                        || particularCell[yau][z] instanceof Empty) {
                    inner = particularCell[yau][z];
                    anInt = 0;
                } else {
                    anInt++;
                }
 
                if (anInt >= getPlayerwin() - 1) {
                    return true;
                }
 
                yau++;
            }
            anInt = 0;
            inner = null;
        }
        return false;
    }
 
    public boolean isFull() {
        for (int i = 0; i < particularCell.length; i++) {
            for (int j = 0; j < particularCell[0].length; j++) {
                if (particularCell[i][j] instanceof Empty) {
                    return false;
                }
            }
        }
        return true;
    }
 
    public void resizegame(int s) {
        x = s;
        y = s;
 
        particularCell = new Cell[x][y];
 
        int xss = l / x;
        int yss = a / y;
 
        for (int z = 0; z < y; z++) {
            for (int i = 0; i < x; i++) {
                particularCell[z][i] = new Empty(xss * i, z * yss);
            }
        }
        handler.sendMessage(Message.obtain(handler, 0));
    }
     
    public int getPlayerwin() {
        return playerwin;
    }
}
