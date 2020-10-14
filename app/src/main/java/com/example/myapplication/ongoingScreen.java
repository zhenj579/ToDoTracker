package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class ongoingScreen extends Activity {

    TextView display;
    TextView timerDisplay;

    private CountDownTimer createTimer(final long milis, final String taskName, final CountDownTimer next)
    {
        return new CountDownTimer(milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                display.setText(taskName);
                //edit the timerDisplay to reflect on how many seconds left
                timerDisplay.setText("seconds left: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), alarmSound);
                mediaPlayer.start(); // play android notif sound when timer finishes
                if(next != null) next.start(); // start the next queued timer
                else timerDisplay.setText("no more tasks left"); // when there are no more tasks, change edit text to show that there are no more tasks left
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ongoing);

        timerDisplay = (TextView) findViewById(R.id.timerDisplay);
        display = (TextView) findViewById(R.id.display);

        Bundle bundle = getIntent().getExtras();

        final int NUM_OF_TASKS = bundle.getInt("numOfTasks", 0);

        CountDownTimer next = null;

        for(int i = NUM_OF_TASKS; i >= 1; i--) // create timers starting from last task moving backwards to the first task
        {
            int millis = bundle.getInt("Task " + i + " timer length", 0); // retrieve all information from previous activites
            String taskName = bundle.getString("Task " + i + " name");
            next = createTimer(millis, taskName, next); // timers will be created in a queue-like system.
        }
        // -> = invokes
        // task 1 timer -> task 2 timer -> task 3 timer -> ...
        next.start(); // start the first task's timer
    }
}