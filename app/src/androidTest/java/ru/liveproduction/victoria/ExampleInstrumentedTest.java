package ru.liveproduction.victoria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Instant;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    public static class TestActivity extends Activity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.answer_new_layout);
        }

        public void send(View view) {
            ((TextView) findViewById(R.id.questionTextView)).setText(((EditText)findViewById(R.id.answerInput)).getText());
        }
    }

    @Test
    public void useAppContext() throws InterruptedException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        appContext.startActivity(new Intent().setClass(appContext, TestActivity.class));
        Thread.sleep(10000);

        assertEquals("ru.liveproduction.victoria", appContext.getPackageName());
    }
}
