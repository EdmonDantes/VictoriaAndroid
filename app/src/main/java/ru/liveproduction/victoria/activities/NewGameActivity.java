package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.VictoriaApplication;
import ru.liveproduction.victoria.api.Lobby;
import ru.liveproduction.victoria.api.Pack;
import ru.liveproduction.victoria.api.Question;
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

        final SeekBar seekBarTimeWrite = (SeekBar) findViewById(R.id.timeWrite);
        final TextView textViewTimeWrite = (TextView) findViewById(R.id.countTimeReadTextView);
        seekBarTimeWrite.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewTimeWrite.setText(String.valueOf(i)+"c");
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
            pack = (Pack) data.getSerializableExtra("pack");

            findViewById(R.id.choseComplex).setVisibility(View.VISIBLE);
            findViewById(R.id.l1).setVisibility(View.VISIBLE);
            findViewById(R.id.l2).setVisibility(View.VISIBLE);
            findViewById(R.id.l3).setVisibility(View.VISIBLE);
            ((Button) findViewById(R.id.choseQuest)).setText("Пак №" + ((Pack)data.getSerializableExtra("pack")).getId());

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

            if (!easy) findViewById(R.id.easy).setVisibility(View.GONE);
            if (!middle) findViewById(R.id.middle).setVisibility(View.GONE);
            if (!hard) findViewById(R.id.hard).setVisibility(View.GONE);
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
            Lobby lobby = new Lobby(textView.getText().toString(), pack.getId(), players.getProgress(),  timeWrite.getProgress(), timeRead.getProgress(), easy.isCheck(), middle.isCheck(), hard.isCheck(), VictoriaApplication.user.getId());
            new Task(activity, lobby).execute();
        } else {
            Toast.makeText(this, "Неправильные данные", Toast.LENGTH_SHORT).show();
        }
    }

    class Task extends AsyncTask<Void, Void, Integer> {

        Activity activity;
        Lobby lobby;

        public Task(Activity activity, Lobby lobby){
            this.activity = activity;
            this.lobby = lobby;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                JsonElement tmp = Utils.get(0, lobby.toJson());
                if (tmp != null)
                    return tmp.getAsInt();
                else return 0;
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer < 0) Toast.makeText(activity, "ERROR@##", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent(activity, LobbyActivity.class);
                intent.putExtra("pack", pack);
                intent.putExtra("lobby", lobby);
                intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                startActivity(intent);
            }
            super.onPostExecute(integer);
        }
    }
}

