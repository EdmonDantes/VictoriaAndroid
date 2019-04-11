package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.liveproduction.victoria.R;

public class TableActivity extends Activity {

    String string;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_table_layout);

    }

}

