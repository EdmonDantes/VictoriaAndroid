package ru.liveproduction.victoria.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Utils {

    public static JsonElement get(int type, String[][] args) throws IOException {
        URL url = new URL("http://yoururl.com");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(4000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("typeRequest", type);

        

        JsonObject jsonObject1
        jsonObject.addProperty("typeRequest", type);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(getQuery(params));
        writer.flush();
        writer.close();
        os.close();

        conn.connect();
    }
}
