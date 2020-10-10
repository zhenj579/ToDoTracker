package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;

public class setTimerScreen extends AppCompatActivity {

    Button startButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer_screen);
        startButton = (Button) findViewById(R.id.startButton);
        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(setTimerScreen.this, setTaskScreen.class);
                startActivity(intent);

            }
        });
        //grab all timer edittexts
        final EditText timer1EditText = (EditText) findViewById(R.id.timer1EditText);
        final EditText timer2EditText = (EditText) findViewById(R.id.timer2EditText);
        final EditText timer3EditText = (EditText) findViewById(R.id.timer3EditText);
        final EditText timer4EditText = (EditText) findViewById(R.id.timer4EditText);
        final EditText timer5EditText = (EditText) findViewById(R.id.timer5EditText);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the numbers from the edittexts and convert to integer
                final int timer1Duration = Integer.parseInt(timer1EditText.getText().toString());
                final int timer2Duration = Integer.parseInt(timer2EditText.getText().toString());
                final int timer3Duration = Integer.parseInt(timer3EditText.getText().toString());
                final int timer4Duration = Integer.parseInt(timer4EditText.getText().toString());
                final int timer5Duration = Integer.parseInt(timer5EditText.getText().toString());
                Bundle extras = getIntent().getExtras();


                String task1Name = extras.getString("task1Name");
                String task2Name = extras.getString("task2Name");
                String task3Name = extras.getString("task3Name");
                String task4Name = extras.getString("task4Name");
                String task5Name = extras.getString("task5Name");

                Intent intent = new Intent(setTimerScreen.this, ongoingScreen.class);
                intent.putExtra("timer1Duration", timer1Duration);
                intent.putExtra("timer2Duration", timer2Duration);
                intent.putExtra("timer3Duration", timer3Duration);
                intent.putExtra("timer4Duration", timer4Duration);
                intent.putExtra("timer5Duration", timer5Duration);

                intent.putExtra("task1Name",task1Name);
                intent.putExtra("task2Name",task2Name);
                intent.putExtra("task3Name",task3Name);
                intent.putExtra("task4Name",task4Name);
                intent.putExtra("task5Name",task5Name);

                startActivity(intent);
            }
        });

    }
}