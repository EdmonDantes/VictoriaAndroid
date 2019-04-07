package ru.liveproduction.victoria.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.api.Pack;
import ru.liveproduction.victoria.api.Question;

public class QuetionTimeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Question question = (Question) getIntent().getSerializableExtra("q");

        ((TextView) findViewById(R.id.quetion)).setText(question.getQuestion());

    }
}
