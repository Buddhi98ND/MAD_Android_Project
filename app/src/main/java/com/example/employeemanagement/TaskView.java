package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TaskView extends AppCompatActivity {

    TextView editTaskNum,editTask,editDescription,editStartDate,editEndDate,editEmployee;
    Button cancel1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);


        editTaskNum = findViewById(R.id.admineditTaskNum);
        editTask = findViewById(R.id.editTask);
        editDescription= findViewById(R.id.adminStartDate);
        editStartDate = findViewById(R.id.editStartDate);
        editEndDate = findViewById(R.id.adminEndDate);
        editEmployee = findViewById(R.id.adminEmployee);

        Intent i = getIntent();
        String tNum = i.getStringExtra("taskNumber");
        String task = i.getStringExtra("task");
        String tDes = i.getStringExtra("description");
        String tStart = i.getStringExtra("startDate");
        String tEnd = i.getStringExtra("endDate");
        String tEmp = i.getStringExtra("employee");

        editTaskNum.setText(String.valueOf(tNum));
        editTask.setText(String.valueOf(task));
        editDescription.setText(String.valueOf(tDes));
        editStartDate.setText(String.valueOf(tStart));
        editEndDate.setText(String.valueOf(tEnd));
        editEmployee.setText(String.valueOf(tEmp));

        cancel1 = findViewById(R.id.cancel1);
       cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TaskStatus.class);
                startActivity(intent);
            }
        });



    }
}