package csci448.tictactoe;

import android.os.Bundle;
import android.app.Activity;
 
public class MainActivity extends Activity {
    private Game ticTacToeGame;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticTacToeGame = new Game(this);
        setContentView(ticTacToeGame);
    }
}
