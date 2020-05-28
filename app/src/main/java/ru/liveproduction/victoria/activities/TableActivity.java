package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;
import java.util.Map;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.api.Category;
import ru.liveproduction.victoria.api.Question;

public class TableActivity extends Activity {

    String string;

    private List<Category> list;
    private Map<Category, List<Question>> map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_table_layout);

    }

    public void chosedifficult(View view) {
        String[] array = ((String) view.getTag()).split(";");

        int row = Integer.valueOf(array[0]);
        int column = Integer.valueOf(array[1]);

        openQuestion(row, column);
    }

    private void openQuestion(int row, int column) {
        Question question = map.get(list.get(row)).get(column);

        sendQuestionId(question.getId());
    }

    private void sendQuestionId(int id) {

    }
}

