package ru.liveproduction.victoria.api;

import com.google.gson.JsonObject;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {

    public static int ids = 0;
    int id = ids++;
    int round = 1;
    List<Map.Entry<User, Integer>> players;
    User starting;
    List<Map.Entry<String, List<Question>>> questions = null;
    int timeRead;
    int timeWrite;

    Map.Entry<Question, Integer> nowQuestion = null;

    public Game(List<User> players, List<Map.Entry<String, List<Question>>> questions, int timeRead, int timeWrite) {
        this.players = new ArrayList<>(players.size());
        for (int i = 0; i < players.size(); i++){
            this.players.add(new AbstractMap.SimpleEntry<User, Integer>(players.get(i), 0));
        }

        starting = players.get(new Random().nextInt(players.size() - 1));

        this.questions = questions;
        this.timeRead = timeRead;
        this.timeWrite = timeWrite;
    }

    public int getId() {
        return id;
    }

    public int getRound() {
        return round;
    }

    public int getTimeRead() {
        return timeRead;
    }

    public int getTimeWrite() {
        return timeWrite;
    }

    public int getCountPlayers(){
        return players.size();
    }

    public boolean checkAnswer(int id, String answer){
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getKey().getId() == id) {
                if (nowQuestion.getKey().isRigthAnswer(answer)) {
                    players.set(i, new AbstractMap.SimpleEntry<User, Integer>(players.get(i).getKey(), players.get(i).getValue() + nowQuestion.getValue()));
                    starting = players.get(i).getKey();
                    return true;
                } else {
                    players.set(i, new AbstractMap.SimpleEntry<User, Integer>(players.get(i).getKey(), players.get(i).getValue() - nowQuestion.getValue()));
                    return false;
                }
            }
        }
        return false;
    }

    public List<Map.Entry<User, Integer>> getPlayers(){
        return players;
    }

    public JsonObject toJSON(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        return jsonObject;
    }
}
