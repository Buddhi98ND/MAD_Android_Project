package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class EditTask extends AppCompatActivity {


    TextView taskNum,addTask,AddDescription,addStartDate,addEndDate,addEmployee;
    Button editTaskBtn,delete;
    NewTask task;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        delete = findViewById(R.id.delete);
        editTaskBtn = findViewById(R.id.editTaskBtn);
        taskNum = findViewById(R.id.taskNum);
        addTask = findViewById(R.id.updateTaskName);
        AddDescription = findViewById(R.id.updateDescription);
        addStartDate = findViewById(R.id.updateStartDate);
        addEndDate = findViewById(R.id.updateEndDate);
        addEmployee = findViewById(R.id.updateEmployeeName);

        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);

        addStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" +month+ "/" +year;
                        addStartDate.setText(date);
                    }
                }, year , month , day);
                datePickerDialog.show();
            }
        });

        addEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" +month+ "/" +year;
                        addEndDate.setText(date);
                    }
                }, year , month , day);
                datePickerDialog.show();
            }
        });


        Intent i = getIntent();

        String tNum = i.getStringExtra("taskNumber");
        String task = i.getStringExtra("task");
        String tDes = i.getStringExtra("description");
        String tStart = i.getStringExtra("startDate");
        String tEnd = i.getStringExtra("endDate");
        String tEmp = i.getStringExtra("employee");

        taskNum.setText(String.valueOf(tNum));
        addTask.setText(String.valueOf(task));
        AddDescription.setText(String.valueOf(tDes));
        addStartDate.setText(String.valueOf(tStart));
        addEndDate.setText(String.valueOf(tEnd));
        addEmployee.setText(String.valueOf(tEmp));



        editTaskBtn = findViewById(R.id.editTaskBtn);
        editTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(taskNum.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Check your number", Toast.LENGTH_SHORT).show();
                else{
                    databaseReference = FirebaseDatabase.getInstance().getReference();

                    databaseReference.child("NewTask/"+taskNum.getText().toString().trim()+"/taskNumber").setValue((taskNum.getText().toString().trim()));
                    databaseReference.child("NewTask/"+taskNum.getText().toString().trim()+"/task").setValue((addTask.getText().toString().trim()));
                    databaseReference.child("NewTask/"+taskNum.getText().toString().trim()+"/description").setValue((AddDescription.getText().toString().trim()));
                    databaseReference.child("NewTask/"+taskNum.getText().toString().trim()+"/startDate").setValue((addStartDate.getText().toString().trim()));
                    databaseReference.child("NewTask/"+taskNum.getText().toString().trim()+"/endDate").setValue((addEndDate.getText().toString().trim()));
                    databaseReference.child("NewTask/"+taskNum.getText().toString().trim()+"/employee").setValue((addEmployee.getText().toString().trim()));

                    Toast.makeText(getApplicationContext(),"Successfully Updated", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(getApplicationContext(), AdminStatus.class);
                startActivity(intent);
            }
        });



    }
}
