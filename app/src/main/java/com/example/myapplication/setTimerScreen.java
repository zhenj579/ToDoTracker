package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class setTimerScreen extends AppCompatActivity {

    Button startButton;

    private CountDownTimer createTimer(final long millisInFuture, final TextView display, final String taskName)
    {
        return new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                display.setText("seconds left: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                display.setText("The task: " + taskName + " has been completed, starting next task in a few seconds...");
                new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Context context = getApplicationContext();
                        CharSequence text = "starting next task in: " + millisUntilFinished/1000;
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer_screen);
        startButton = (Button) findViewById(R.id.startButton);

        final EditText timer1EditText = (EditText) findViewById(R.id.timer1EditText);
        final EditText timer2EditText = (EditText) findViewById(R.id.timer2EditText);
        final EditText timer3EditText = (EditText) findViewById(R.id.timer3EditText);
        final EditText timer4EditText = (EditText) findViewById(R.id.timer4EditText);
        final EditText timer5EditText = (EditText) findViewById(R.id.timer5EditText);





        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int timer1Duration = Integer.parseInt(timer1EditText.toString());
                final int timer2Duration = Integer.parseInt(timer2EditText.toString());
                final int timer3Duration = Integer.parseInt(timer3EditText.toString());
                final int timer4Duration = Integer.parseInt(timer4EditText.toString());
                final int timer5Duration = Integer.parseInt(timer5EditText.toString());
                HashMap<String, CountDownTimer> tasks = new HashMap<>();
                Bundle extras = getIntent().getExtras();
                if(extras != null)
                {
                    String task1Name = extras.getString("task1Name");
//                    tasks.put(task1Name, createTimer(timer1Duration, )
                }
            }
        });
    }
}