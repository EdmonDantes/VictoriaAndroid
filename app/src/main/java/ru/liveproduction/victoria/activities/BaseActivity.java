package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class BaseActivity extends Activity {

    protected Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    public void go_back(View v){
        onBackPressed();// возврат на предыдущий activity
    }
}
