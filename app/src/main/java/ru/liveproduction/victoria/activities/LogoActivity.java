package ru.liveproduction.victoria.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import ru.liveproduction.victoria.R;

public class LogoActivity extends BaseActivity {

    private float alpha = 0.01f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_layout);

        findViewById(R.id.logo_image_view).post(new Runnable() {
            @Override
            public void run() {
                new CountDownTimer(4000, 40){
                    @Override
                    public void onTick(long l) {
                        alpha += 0.01f;
                        findViewById(R.id.logo_image_view).setAlpha(alpha);
                        findViewById(R.id.logo_text).setAlpha(alpha);
                    }

                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(activity, MainActivity.class);
                        startActivity(intent);
                    }
                }.start();
            }
        });
    }
}
