package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;

public class setTimerScreen extends AppCompatActivity {

    Button startButton;
    Button backButton;
    int timerIDs;
    final int TIMER_OFFSET = 9; // start indexing timers from 1

    private void addTimer()
    {
        LinearLayout ll = (LinearLayout)findViewById(R.id.timerLayout);
        EditText et = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        lp.width = 300;
        et.setLayoutParams(lp);
        et.setHint("Task " + (timerIDs - TIMER_OFFSET));
        et.setGravity(Gravity.CENTER);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        et.setId(timerIDs);
        ll.addView(et);
        timerIDs++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer_screen);
        startButton = (Button) findViewById(R.id.startButton);
        backButton = (Button) findViewById(R.id.backButton);
        final int MAX_NUM_OF_TIMERS = getIntent().getIntExtra("numOfTasks", 0);
        timerIDs = 10; //arbitrary number that is not in the range of the taskIDs to make sure no edittext shares the same id
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setTimerScreen.this, setTaskScreen.class);
                startActivity(intent); // go back to previous screen
            }
        });
        for(int i = 0; i < MAX_NUM_OF_TIMERS; i++)
        {
            addTimer(); // create the number of timers that corresponds to the number of tasks
        }
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();

                final int NUM_OF_TASKS = extras.getInt("numOfTasks", 0);
                Intent intent = new Intent(setTimerScreen.this, ongoingScreen.class);
                intent.putExtra("numOfTasks", NUM_OF_TASKS);
                for(int i = 0; i < NUM_OF_TASKS; i++)
                {
                    String taskName = extras.getString("Task " + (i+1) + " name");
                    intent.putExtra("Task " + (i+1) + " name", taskName); // pass on the task names from the setTaskScreen to the ongoingScreen
                    // this taskName will be displayed on the ongoingScreen.
                    EditText et = (EditText) findViewById(timerIDs-i-1); // get all timer edittext fields
                    String content = et.getText().toString();
                    int duration = Integer.parseInt(content) * 1000; // convert the edittext content from string seconds to int milliseconds
                    intent.putExtra("Task " + i + " timer length", duration); // pass on the duration of the timer to ongoingScreen
                }
                startActivity(intent);
            }
        });

    }
}