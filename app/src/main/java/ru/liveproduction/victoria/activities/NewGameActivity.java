package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import ru.liveproduction.victoria.R;

public class NewGameActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
    }

    public void choseQuestion(View view) {
        Intent in = new Intent(this, ChoseQuestionActivity.class);
        startActivity(in);
    }

   // public void go_back(){
     //   DataTablePage DTP = new DataTablePage(int id, int parms);

//    }
    public void go_back(View v){
        onBackPressed();// возврат на предыдущий activity
    }
}
