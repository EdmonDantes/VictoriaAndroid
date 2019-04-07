package ru.liveproduction.victoria.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ru.liveproduction.victoria.R;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void newGame(View view) {
        Intent in = new Intent(this, NewGameActivity.class);
        startActivity(in);
    }
    public void connected(View view) {
        Intent in = new Intent(this, SearchGameActivity.class);
        startActivity(in);
    }

    public void go_back(View v) {
        onBackPressed();// возврат на предыдущий activity
    }
}
