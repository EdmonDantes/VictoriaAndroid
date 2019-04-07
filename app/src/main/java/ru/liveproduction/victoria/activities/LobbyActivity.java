package ru.liveproduction.victoria.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.VictoriaApplication;
import ru.liveproduction.victoria.adapters.UsersAdapter;
import ru.liveproduction.victoria.api.Lobby;
import ru.liveproduction.victoria.api.Pack;

public class LobbyActivity extends BaseActivity {

    Pack pack;
    Lobby lobby;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_game);

        pack = (Pack) getIntent().getSerializableExtra("pack");
        lobby = (Lobby) getIntent().getSerializableExtra("lobby");

        ListView listView = findViewById(R.id.listView);
        UsersAdapter adapter = new UsersAdapter(this, lobby);
        adapter.addUser(VictoriaApplication.user);
        listView.setAdapter(adapter);
    }
}


