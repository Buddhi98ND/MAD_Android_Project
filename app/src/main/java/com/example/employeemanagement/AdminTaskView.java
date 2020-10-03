package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminTaskView extends AppCompatActivity {

    TextView taskNum,editTask,editDescription,editStartDate,EditEndDate,EditEmployee;
    Button edit,delete,cancel1;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_task_view);



        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        cancel1 = findViewById(R.id.cancel1);
        taskNum = findViewById(R.id.admineditTaskNum);
        editTask = findViewById(R.id.admintask);
        editDescription = findViewById(R.id.adminDescription);
        editStartDate = findViewById(R.id.adminStartDate);
        EditEndDate = findViewById(R.id.adminEndDate);
        EditEmployee = findViewById(R.id.adminEmployee);



        Intent i = getIntent();
        final String tNum = i.getStringExtra("taskNumber");
        final String task = i.getStringExtra("task");
        final String tDes = i.getStringExtra("description");
        final String tStart = i.getStringExtra("startDate");
        final String tEnd = i.getStringExtra("endDate");
        final String tEmp = i.getStringExtra("employee");

        taskNum.setText(String.valueOf(tNum));
        editTask.setText(String.valueOf(task));
        editDescription.setText(String.valueOf(tDes));
        editStartDate.setText(String.valueOf(tStart));
        EditEndDate.setText(String.valueOf(tEnd));
        EditEmployee.setText(String.valueOf(tEmp));

        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminStatus.class);
                startActivity(intent);
            }
        });

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditTask.class);
                intent.putExtra("taskNumber", tNum);
                intent.putExtra("task", task);
                intent.putExtra("description", tDes);
                intent.putExtra("startDate", tStart);
                intent.putExtra("endDate", tEnd);
                intent.putExtra("employee", tEmp);

                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(taskNum.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Check You number", Toast.LENGTH_LONG).show();

                else {
                    databaseReference = FirebaseDatabase.getInstance().getReference("NewTask/" + taskNum.getText().toString().trim());
                    databaseReference.removeValue();
                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                }
            }
        });



    }


}