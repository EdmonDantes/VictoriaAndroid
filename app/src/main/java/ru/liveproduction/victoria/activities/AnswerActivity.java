package ru.liveproduction.victoria.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

import ru.liveproduction.victoria.R;

public class AnswerActivity extends Activity {

    public static final String QUESTION_STRING_FIELD = "QuestionString";
    public static final String TIMER_FOR_READING_FIELD = "TimerReading";
    public static final String TIMER_FOR_WRITING_FIELD = "TimerWriter";

    private ScheduledFuture<?> waitingFuture = null;
    private ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(4);

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_new_layout);

        String questionString = getIntent().getStringExtra(QUESTION_STRING_FIELD);
        long timeForReadInMs = getIntent().getLongExtra(TIMER_FOR_READING_FIELD, -1);
        final long timeForWriteInMs = getIntent().getLongExtra(TIMER_FOR_WRITING_FIELD, -1);

        if (StringUtils.isEmpty(questionString)) {
            //FUTURE: execute on action result with null result or something
            throw new RuntimeException("Wrong question string");
        }

        if (timeForReadInMs < 0) {
            //FUTURE: execute on action result with null result or something
            throw new RuntimeException("Wrong time for read");
        }

        if (timeForWriteInMs < 0) {
            //FUTURE: execute on action result with null result or something
            throw new RuntimeException("Wrong time for write");
        }

        ((TextView) findViewById(R.id.questionTextView)).setText(questionString);
        ((TextView) findViewById(R.id.timeViewer)).setText(String.format("%s:%s", TimeUnit.MINUTES.convert(timeForReadInMs, TimeUnit.MILLISECONDS), TimeUnit.SECONDS.convert(timeForReadInMs, TimeUnit.MILLISECONDS)));

        scheduledService.schedule(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.answerInput).setVisibility(View.VISIBLE);
                        findViewById(R.id.sendButton).setVisibility(View.VISIBLE);
                    }
                });
                waitingFuture = scheduledService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        //FUTURE: result to start activity
                    }
                }, timeForWriteInMs, TimeUnit.MILLISECONDS);
            }
        }, timeForReadInMs, TimeUnit.MILLISECONDS);
    }

    public void send(View view) {

        waitingFuture.cancel(true);

        CharSequence text = ((TextView) findViewById(R.id.answerInput)).getText();
        CompletableFuture<Boolean> answerWait = sendAnswerTextToBackEnd(text.toString());
        answerWait.handleAsync(new BiFunction<Boolean, Throwable, Object>() {
            @Override
            public Object apply(Boolean aBoolean, Throwable throwable) {
                if (aBoolean) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.sendButton).setClickable(false);
                            ((Button) findViewById(R.id.sendButton)).setText(R.string.rightAnswer);
                        }
                    });
                    scheduledService.schedule(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //FUTURE: run on activity result
                                }
                            });
                        }
                    }, 2, TimeUnit.SECONDS);
                }
                return null;
            }
        });
    }

    private CompletableFuture<Boolean> sendAnswerTextToBackEnd(String toString) {
        //FUTURE: create code for rest api
        return CompletableFuture.completedFuture(true);
    }

    private class WriterWaiter extends AsyncTask<Void, Void, Boolean> {

        private long timeForWriteInMs;

        public WriterWaiter(long timeForWriteInMs) {
            this.timeForWriteInMs = timeForWriteInMs;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return null;
        }
    }
}
