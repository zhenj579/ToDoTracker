package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class setTaskScreen extends Activity {

    Button setButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButton = (Button) findViewById(R.id.setButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get all the edit text fields
                EditText task1EditText = (EditText) findViewById(R.id.task1EditText);
                EditText task2EditText = (EditText) findViewById(R.id.task2EditText);
                EditText task3EditText = (EditText) findViewById(R.id.task3EditText);
                EditText task4EditText = (EditText) findViewById(R.id.task4EditText);
                EditText task5EditText = (EditText) findViewById(R.id.task5EditText);
                //extract their text content and convert to a string
                String task1Name = task1EditText.toString();
                String task2Name = task2EditText.toString();
                String task3Name = task3EditText.toString();
                String task4Name = task4EditText.toString();
                String task5Name = task5EditText.toString();
                //pass this information to the next activity(setTimerScreen)
                Intent intent = new Intent(setTaskScreen.this, setTimerScreen.class);
                intent.putExtra("task1Name", task1Name);
                intent.putExtra("task2Name", task2Name);
                intent.putExtra("task3Name", task3Name);
                intent.putExtra("task4Name", task4Name);
                intent.putExtra("task5Name", task5Name);
                startActivity(intent);

            }
        });
    }

}