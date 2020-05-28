package ru.liveproduction.victoria;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ru.liveproduction.victoria.activities.AnswerActivity;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws InterruptedException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Intent intent = new Intent().setClass(appContext, AnswerActivity.class);
        intent.putExtra(AnswerActivity.QUESTION_STRING_FIELD, "Test question");
        intent.putExtra(AnswerActivity.TIMER_FOR_READING_FIELD, 5000l);
        intent.putExtra(AnswerActivity.TIMER_FOR_WRITING_FIELD, 80000l);
        appContext.startActivity(intent);
        Thread.sleep(10000);

        assertEquals("ru.liveproduction.victoria", appContext.getPackageName());
    }
}
