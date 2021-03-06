package ru.liveproduction.victoria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
            view = layoutInflater.inflate(R.layout.lobby_player_list_adapter_layout, viewGroup, false);

        ((TextView) view.findViewById(R.id.playerName)).setText(lobby.getPlayers().get(i).getKey().getName());

        if (lobby.getPlayers().get(i).getValue()) {
            view.findViewById(R.id.checkbox).setBackgroundResource(R.drawable.check_lobby_layout);
        }
        return view;
    }

    public void changeUserStatus(User user) {
        lobby.setReadyUser(user, !lobby.getReadyUser(user));
        notifyDataSetInvalidated();
    }

    public void addUser(User user) {
        lobby.addUserToLobby(user);
        notifyDataSetInvalidated();
    }

    public void deleteUser(User user) {
        lobby.exitFromLobby(user);
        notifyDataSetInvalidated();
    }
}
