package ru.liveproduction.victoria.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.VictoriaApplication;
import ru.liveproduction.victoria.adapters.UsersAdapter;
import ru.liveproduction.victoria.api.Lobby;
import ru.liveproduction.victoria.api.Pack;
import ru.liveproduction.victoria.utils.Utils;

public class LobbyActivity extends BaseActivity {

    Pack pack;
    Lobby lobby;
    UsersAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_game);

        pack = (Pack) getIntent().getSerializableExtra("pack");
        lobby = (Lobby) getIntent().getSerializableExtra("lobby");

        ListView listView = findViewById(R.id.linearLayout3);
        adapter = new UsersAdapter(this, lobby);
        adapter.addUser(VictoriaApplication.user);
        listView.setAdapter(adapter);
    }

    public void ready(View view) {
        adapter = new UsersAdapter(this, lobby);
        adapter.changeUserStatus(VictoriaApplication.user);
        ListView listView = findViewById(R.id.linearLayout3);
        listView.setAdapter(adapter);
        ((ListView) findViewById(R.id.linearLayout3)).invalidateViews();
    }

    public void exit(View view) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("nameLobby", lobby.getName());
        new Task(jsonObject).execute();
    }

    class Task extends AsyncTask<Void, Void, Void> {

        JsonObject obj;

        public Task(JsonObject jsonObject){
            this.obj = jsonObject;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Utils.get(5, obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            onBackPressed();
        }
    }
}


