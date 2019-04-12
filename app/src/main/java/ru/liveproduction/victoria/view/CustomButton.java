package ru.liveproduction.victoria.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import ru.liveproduction.victoria.R;


public class CustomButton extends android.support.v7.widget.AppCompatButton {

    boolean check = true;

    public void init(){
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check) {
                    view.setBackgroundResource(R.drawable.new_game_layout_button_green_background);
                }else {
                    view.setBackgroundResource(R.drawable.new_game_layout_button_gray_background);
                }

                check = !check;

            }
        });
    }

    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public boolean isCheck() {
        return !check;
    }
}
