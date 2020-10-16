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
    Button removeTaskButton;
    Button removeAllButton;
    LinearLayout ll;

    int taskIDS;
    final int MAX_TASKS = 10;

    protected boolean tasksExists()
    {
        return taskIDS > 0;
    }

    private void removeAllTaskEditText()
    {
        ll.removeAllViews();
    }

    private void addTaskEditText()
    {
        EditText et = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(p);
        et.setHint("Task " + (taskIDS + 1));
        et.setId(taskIDS);
        taskIDS++;
        ll.addView(et);
    }

    private void removeTaskEditText()
    {
        EditText et = (EditText) findViewById(taskIDS-1);
        ll.removeView(et);
        taskIDS--;
    }

    private Toast generateToast(CharSequence msg)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        return Toast.makeText(context, msg, duration);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskIDS = 0;
        setButton = (Button) findViewById(R.id.setButton);
        addTaskButton = (Button) findViewById(R.id.addTaskButton);
        removeTaskButton = (Button) findViewById(R.id.removeEditTextButton);
        removeAllButton = (Button) findViewById(R.id.clearButton);
        ll = (LinearLayout) findViewById(R.id.taskLayout);

        removeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tasksExists())
                {
                    removeAllTaskEditText();
                    generateToast("Cleared").show();
                }
                else
                {
                    generateToast("No tasks to clear").show();
                }
            }
        });
        removeTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskIDS > 0)
                {
                    removeTaskEditText();
                }
                else
                {
                    generateToast("No tasks to remove").show();
                }
            }
        });
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskIDS < MAX_TASKS) // the screen can only fit 10 tasks before it goes out of screen
                {
                    addTaskEditText();//if screen can fit another edittext, add another.
                }
                else
                {
                    generateToast("Exceeded task limit").show();
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