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

    CountDownTimer timer1;
    CountDownTimer timer2;
    CountDownTimer timer3;
    CountDownTimer timer4;
    CountDownTimer timer5;

    TextView display;
    TextView timerDisplay;

    int timer1Duration;
    int timer2Duration;
    int timer3Duration;
    int timer4Duration;
    int timer5Duration;

    String task1Name;
    String task2Name;
    String task3Name;
    String task4Name;
    String task5Name;

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

        //get all the timer duration extras and convert from milliseconds to seconds
        timer1Duration = bundle.getInt("timer1Duration")*1000;
        timer2Duration = bundle.getInt("timer2Duration")*1000;
        timer3Duration = bundle.getInt("timer3Duration")*1000;
        timer4Duration = bundle.getInt("timer4Duration")*1000;
        timer5Duration = bundle.getInt("timer5Duration")*1000;

        //get all string task names
        task1Name = bundle.getString("task1Name");
        task2Name = bundle.getString("task2Name");
        task3Name = bundle.getString("task3Name");
        task4Name = bundle.getString("task4Name");
        task5Name = bundle.getString("task5Name");

        //create the timers and queue each task one after the other
        timer5 = createTimer(timer5Duration, task5Name, null);
        timer4 = createTimer(timer4Duration, task4Name, timer5);
        timer3 = createTimer(timer3Duration, task3Name, timer4);
        timer2 = createTimer(timer2Duration, task2Name, timer3);
        timer1 = createTimer(timer1Duration, task1Name, timer2);

        //start the first timer
        timer1.start();

    }
}