package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import ru.liveproduction.victoria.R;

public class ChoseQuestionActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chosepack);

        findViewById(R.id.chosePackListView);
    }
    public void go_back(View v){
        onBackPressed();// возврат на предыдущий activity
    }
}
