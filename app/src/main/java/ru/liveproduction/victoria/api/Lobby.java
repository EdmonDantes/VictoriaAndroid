package ru.liveproduction.victoria.api;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

public class Lobby implements Serializable {

    String name;
    int packId;
    int maxPlayers;
    int timeRead;
    int timeWrite;
    int owner;
    List<Map.Entry<User, Boolean>> players;
    boolean easy, middle, hard;

    public Lobby(String name, int packId, int maxPlayers, int timeWrite, int timeRead, boolean easy, boolean middle, boolean hard, int owner) {
        this.name = name;
        this.packId = packId;
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>(maxPlayers);
        this.timeRead = timeRead;
        this.timeWrite = timeWrite;
        this.easy = easy;
        this.middle = middle;
        this.hard = hard;
        this.owner = owner;
    }

    public int getOwner() {
        return owner;
    }

    public int addUserToLobby(User user) {
        if (players.size() < maxPlayers) {
            for (Map.Entry<User, Boolean> player : players) {
                if (player.getKey().equals(user)) return -1;
            }
            players.add(new AbstractMap.SimpleEntry<>(user, false));
        }
        return maxPlayers - players.size();
    }

    public boolean exitFromLobby(final User user){
        players.removeIf(new Predicate<Map.Entry<User, Boolean>>() {
            @Override
            public boolean test(Map.Entry<User, Boolean> obj) {
                return obj.getKey().equals(user);
            }
        });
        return players.size() > 0;
    }

    public boolean readyUser(User user) {
        return setReadyUser(user, true);
    }

    public boolean getReadyUser(User user){
        for (Map.Entry<User, Boolean> pair : players) {
            if (pair.getKey().equals(user)){
                return pair.getValue();
            }
        }
        return false;
    }

    public boolean setReadyUser(User user, boolean value) {
        for (Map.Entry<User, Boolean> pair : players) {
            if (pair.getKey().equals(user)){
                pair.setValue(value);
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getPackId() {
        return packId;
    }

    public int getTimeRead() {
        return timeRead;
    }

    public int getTimeWrite() {
        return timeWrite;
    }

    public List<Map.Entry<User, Boolean>> getPlayers() {
        return players;
    }

    public JsonObject toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("packId", packId);
        jsonObject.addProperty("maxPlayers", maxPlayers);
        jsonObject.addProperty("addedPlayers", players.size());
        jsonObject.addProperty("timeReader", timeRead);
        jsonObject.addProperty("timeWriter", timeWrite);
        return jsonObject;
    }

}
