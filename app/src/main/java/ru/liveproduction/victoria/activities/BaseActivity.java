package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.view.View;

public class BaseActivity extends Activity {
    public void go_back(View v){
        onBackPressed();// возврат на предыдущий activity
    }
}
