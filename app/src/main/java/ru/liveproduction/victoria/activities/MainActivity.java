package ru.liveproduction.victoria.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.api.PackManager;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            }
        }
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
