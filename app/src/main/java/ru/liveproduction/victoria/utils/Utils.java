package ru.liveproduction.victoria.utils;

import android.widget.Button;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import ru.liveproduction.victoria.VictoriaApplication;
import ru.liveproduction.victoria.api.User;

public class Utils {

    public static JsonElement get(int type, String[][] args) throws IOException {
        JsonObject jsonObject = new JsonObject();
        for (String[] s : args) {
            jsonObject.addProperty(s[0], s[1]);
        }
        return get(type, jsonObject);
    }

    public static JsonElement get(int type, JsonObject post) throws IOException {
        URL url = new URL("http://80.211.26.238:8080/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(4000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        if (VictoriaApplication.user == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("typeRequest", 2);
            jsonObject.add("data", new JsonObject());

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonObject.toString());
            writer.flush();
            writer.close();
            os.close();

            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder  sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            VictoriaApplication.user = User.fromJson(new JsonParser().parse(sb.toString()).getAsJsonObject());

            return get(type, post);
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("typeRequest", type);
        JsonObject data = new JsonObject();
        data.addProperty("userId", VictoriaApplication.user.getId());
        data.addProperty("token", VictoriaApplication.user.getIdentify());
        jsonObject.addProperty("typeRequest", type);
        jsonObject.add("data", data);

        for (Map.Entry<String, JsonElement> elem : post.entrySet()) {
            jsonObject.add(elem.getKey(), elem.getValue());
        }

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();

        conn.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder  sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        JsonObject jsonObject1 = new JsonParser().parse(sb.toString()).getAsJsonObject();

        if (jsonObject1.has("error")){
            return jsonObject1.get("error");
        }else {
            if (jsonObject1 != null && jsonObject1.get("response") != null)
                return jsonObject1.get("response").getAsJsonObject();
            else return null;
        }
    }
}
