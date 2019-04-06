package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.liveproduction.victoria.R;

public class NewGameActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgame);
    }
}
