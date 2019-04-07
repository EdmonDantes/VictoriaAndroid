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

public class InfoPackActivity extends BaseActivity {

    Pack pack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_of_pack);

        pack = (Pack)getIntent().getSerializableExtra("pack");

        ((TextView) findViewById(R.id.packName)).setText("Пак №" + pack.getId());

        boolean easy = false, middle = false, hard = false;

        for (Map.Entry<String, List<Question>> tmp : pack.getData().entrySet()) {
            easy = easy | tmp.getValue().stream().anyMatch(new Predicate<Question>() {
                @Override
                public boolean test(Question question) {
                    return question.getPrice() > 0 && question.getPrice() <= 40;
                }
            });

            middle = middle | tmp.getValue().stream().anyMatch(new Predicate<Question>() {
                @Override
                public boolean test(Question question) {
                    return question.getPrice() > 40 && question.getPrice() <= 80;
                }
            });

            hard = hard | tmp.getValue().stream().anyMatch(new Predicate<Question>() {
                @Override
                public boolean test(Question question) {
                    return question.getPrice() > 80;
                }
            });
        }

        TextView textView = findViewById(R.id.packInfo);
        textView.setText("");
        textView.append("Категории:\n");
        for (String str : pack.getCategories()) {
            textView.append(str);
            textView.append("\n");
        }
        textView.append("Сложности:\n");
        if (easy)
            textView.append("Легко\n");
        if (middle)
            textView.append("Средне\n");
        if (hard)
            textView.append("Сложно\n");
    }

    public void chosePack(View view) {
        Intent intent = new Intent();
        intent.putExtra("pack", pack);
        setResult(43, intent);
        finish();
    }
}
