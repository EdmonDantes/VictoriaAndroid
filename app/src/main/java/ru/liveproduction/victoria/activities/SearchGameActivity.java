package ru.liveproduction.victoria.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ru.liveproduction.victoria.R;

public class SearchGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_game);
    }

    public void go_back(View v){
        onBackPressed();// возврат на предыдущий activity
    }
}