package ru.liveproduction.victoria;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import ru.liveproduction.victoria.api.Pack;
import ru.liveproduction.victoria.api.User;

public class VictoriaApplication extends Application {

    public static volatile List<Pack> packList = null;
    public static volatile User user = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
