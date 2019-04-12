package ru.liveproduction.victoria.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class LogoTextView extends android.support.v7.widget.AppCompatTextView {
    public LogoTextView(Context context) {
        super(context);
    }

    public LogoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed)
            getPaint().setShader(new LinearGradient(0, 0, getWidth(), 0, Color.argb(255, 0,172,219), Color.argb(255, 0, 184, 78), Shader.TileMode.CLAMP));
    }
}
