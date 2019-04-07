package ru.liveproduction.victoria.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ru.liveproduction.victoria.R;
import ru.liveproduction.victoria.api.Lobby;
import ru.liveproduction.victoria.api.User;

public class UsersAdapter extends BaseAdapter {

    Lobby lobby;
    Context context;
    LayoutInflater layoutInflater;

    public UsersAdapter(Context context, Lobby lobby) {
        this.lobby = lobby;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lobby.getPlayers().size();
    }

    @Override
    public Object getItem(int i) {
        return lobby.getPlayers().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        if (view == null)
            view = layoutInflater.inflate(R.layout.room_game_check_adapter, viewGroup, false);

        ((TextView) view.findViewById(R.id.playerName)).setText(lobby.getPlayers().get(i).getKey().getName());
        return view;
    }

    public void changeUserStatus(User user) {
        lobby.setReadyUser(user, !lobby.getReadyUser(user));
    }

    public void addUser(User user) {
        lobby.addUserToLobby(user);
    }

    public void deleteUser(User user) {
        lobby.exitFromLobby(user);
    }
}
