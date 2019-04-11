package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.VictoriaApplication;
import ru.liveproduction.victoria.adapters.PackAdapter;
import ru.liveproduction.victoria.api.PackManager;
import ru.liveproduction.victoria.utils.Utils;

public class ChoseQuestionActivity extends BaseActivity {
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_game);
        new Task(this).execute();
        activity = this;
    }

    class Task extends AsyncTask<Void, Void, PackManager> {
        Activity activity;

        public Task(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected PackManager doInBackground(Void... voids) {
            PackManager packManager = null;
            try {
                packManager = PackManager.fromJson(Utils.get(1,new String[][]{}).getAsJsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return packManager;
        }

        @Override
        protected void onPostExecute(PackManager aVoid) {
            super.onPostExecute(aVoid);
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            ListView listView = findViewById(R.id.chosePackListView);
            if (aVoid != null)
            VictoriaApplication.packList = aVoid.getPackList();
            else Toast.makeText(activity, "Ошибка сети", Toast.LENGTH_SHORT).show();
            listView.setAdapter(new PackAdapter(this.activity));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null || data.hasExtra("pack")) {
                activity.setResult(Activity.RESULT_OK, data);
                activity.finish();
            }
        }
    }
}
