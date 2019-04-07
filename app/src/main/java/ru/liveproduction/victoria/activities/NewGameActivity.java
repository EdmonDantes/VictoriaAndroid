package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.io.IOException;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.api.Pack;
import ru.liveproduction.victoria.utils.Utils;
import ru.liveproduction.victoria.view.CustomButton;

public class NewGameActivity extends BaseActivity {

    Pack pack;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgame);

        final SeekBar seekBar = (SeekBar) findViewById(R.id.players);
        final TextView textView = (TextView) findViewById(R.id.countPlayersTextView);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar seekBarTimeRead = (SeekBar) findViewById(R.id.timer);
        final TextView textViewTimeRead = (TextView) findViewById(R.id.countTimeTextView);
        seekBarTimeRead.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewTimeRead.setText(String.valueOf(i)+"c");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void choseQuestion(View view) {
        Intent in = new Intent(this, ChoseQuestionActivity.class);
        startActivityForResult(in, 43);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 43 && resultCode == RESULT_OK) {
            findViewById(R.id.choseComplex).setVisibility(View.VISIBLE);
            findViewById(R.id.l1).setVisibility(View.VISIBLE);
            findViewById(R.id.l2).setVisibility(View.VISIBLE);
            findViewById(R.id.l3).setVisibility(View.VISIBLE);
            ((Button) findViewById(R.id.choseQuest)).setText("Пак №" + ((Pack)data.getSerializableExtra("pack")).getId());
        }
    }

    public void createGame(View view) {
        TextView textView = findViewById(R.id.name);
        CustomButton easy = findViewById(R.id.easy);
        CustomButton middle = findViewById(R.id.middle);
        CustomButton hard = findViewById(R.id.hard);


        SeekBar players = findViewById(R.id.players);
        SeekBar timeRead = findViewById(R.id.timer);
        SeekBar timeWrite = findViewById(R.id.timeWrite);

        if (textView.getText().toString().length() > 0 && (easy.isCheck() || middle.isCheck() || hard.isCheck())) {
            try {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("easy", easy.isCheck());
                jsonObject.addProperty("middle", middle.isCheck());
                jsonObject.addProperty("hard", hard.isCheck());

                Utils.get(0, new String[][]{
                        new String[]{"name", textView.getText().toString()},
                        new String[]{"countPlayers", String.valueOf(players.getProgress())},
                        new String[]{"packId", String.valueOf(pack.getId())},
                        new String[]{"complex", jsonObject.toString()},
                        new String[]{"timeRead", String.valueOf(timeRead.getProgress())},
                        new String[]{"timeWrite", String.valueOf(timeWrite.getProgress())}
                }).getAsInt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
