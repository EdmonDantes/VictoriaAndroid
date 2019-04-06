package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import ru.liveproduction.victoria.R;

public class NewGameActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgame);
    }

    public void choseQuestion(View view) {
        Intent in = new Intent(this, ChoseQuestionActivity.class);
        startActivity(in);
    }
}
