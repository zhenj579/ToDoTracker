package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class setTaskScreen extends Activity {

    Button setButton;
    Button addTaskButton;
    int taskIDS;
    final int MAX_TASKS = 10;

    public final int gettaskIDCount()
    {
        return taskIDS;
    }
    private void addTaskEditText()
    {
        LinearLayout ll = (LinearLayout)findViewById(R.id.taskLayout);
        EditText et = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(p);
        et.setHint("Task " + (taskIDS + 1));
        et.setId(taskIDS);
        taskIDS++;
        ll.addView(et);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskIDS = 0;
        setButton = (Button) findViewById(R.id.setButton);
        addTaskButton = (Button) findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskIDS < MAX_TASKS) // the screen can only fit 10 tasks before it goes out of screen
                {
                    addTaskEditText();//if screen can fit another edittext, add another.
                }
                else
                {
                    Context context = getApplicationContext(); // alert user that no more edittext can fit the screen.
                    CharSequence text = "Cannot make anymore tasks";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(setTaskScreen.this, setTimerScreen.class);
                intent.putExtra("numOfTasks", taskIDS);

                //retrieving all edittexts with tasks and putting their names as an extra for the next activity
                for(int i = 1; i <= taskIDS; i++)
                {
                    EditText et = (EditText) findViewById(i-1);
                    String content = et.getText().toString();
                    intent.putExtra("Task " + i + " name", content);
                }
                startActivity(intent);

            }
        });
    }

}