package ru.liveproduction.victoria.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

public class AutoScaleTextButton extends android.support.v7.widget.AppCompatButton {

    public void init(){
        this.post(new Runnable() {
            @Override
            public void run() {
                float textSize = 10f;
                while (canUseOneLine(getText().toString(), textSize)) {
                    textSize++;
                }
                setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize - 1);
            }
        });
    }

    public AutoScaleTextButton(Context context) {
        super(context);
        init();
    }

    public AutoScaleTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoScaleTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected boolean canUseOneLine(String charSequence, float textSizeInPixel) {
        Rect bounds = new Rect();
        Paint paint = new TextPaint(getPaint());
        paint.setTextSize(textSizeInPixel);
        paint.getTextBounds(charSequence, 0, charSequence.length(), bounds);

        return bounds.width() < (getWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight());
    }
}
