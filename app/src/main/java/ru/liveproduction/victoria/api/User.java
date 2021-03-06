/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 05.04.19 22:33
*/

package ru.liveproduction.victoria.api;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private int id;
    private String name;
    private String token;
    private List<Integer> packsIds;
    private long registerTime;

    public User(int id, String name, String token, long regTime) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.registerTime = regTime;
        packsIds = new ArrayList<>();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getIdentify(){
        return token;
    }

    public boolean isPackAdded(int packId) {
        return packsIds.contains(packId);
    }

    public long getRegistrationTime(){
        return registerTime;
    }

    public JsonObject toJson(boolean useToken) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", name);
        if (useToken)
            jsonObject.addProperty("token", token);
        jsonObject.addProperty("registerTime", registerTime);
        return jsonObject;
    }

    public static User fromJson(JsonObject jsonObject){
        return new User(jsonObject.get("id").getAsInt(), jsonObject.get("name").getAsString(), jsonObject.get("token").getAsString(), jsonObject.get("registerTime").getAsLong());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User)
            return this.id == ((User) obj).id;
        else
            return false;
    }
}
