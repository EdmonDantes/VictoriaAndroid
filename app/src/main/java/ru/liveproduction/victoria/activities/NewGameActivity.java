package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

import ru.liveproduction.victoria.R;

public class NewGameActivity extends BaseActivity {

int g;
    Timer  timer;
    TimerTask mTimerTask;
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
        startActivity(in);
    }
}
