package ru.liveproduction.victoria.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.liveproduction.victoria.R;

public class LobbyActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_game);
    }
}
