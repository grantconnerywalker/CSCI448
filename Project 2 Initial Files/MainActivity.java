package com.example.andrew.tictactoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private TicTacToeGame game1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game1 = new TicTacToeGame(this);
        setContentView(game1);
    }
}
