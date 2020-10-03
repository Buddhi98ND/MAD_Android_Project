package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddNewTask extends AppCompatActivity {
//declare the variable
    EditText taskNum,addTask,AddDescription,addStartDate,addEndDate,addEmployee;
    Button save1;
    DatabaseReference databaseReference;
    NewTask task;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    private void clearControls(){

        taskNum.setText("");
        addTask.setText("");
        AddDescription.setText("");
        addStartDate.setText("");
        addEndDate.setText("");
        addEmployee.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        taskNum = findViewById(R.id.taskNum);
        addTask = findViewById(R.id.addtask);
        AddDescription = findViewById(R.id.addDesscription);
        addStartDate = findViewById(R.id.addStartDate);
        addEndDate = findViewById(R.id.addEndDate);
        addEmployee = findViewById(R.id.addEmployee);


        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);

        addStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddNewTask.this, new DatePickerDialog.OnDateSetListener() {
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
                        AddNewTask.this, new DatePickerDialog.OnDateSetListener() {
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



        save1 = findViewById(R.id.save1);



        task = new NewTask();

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("NewTask");
                try{
                    if (TextUtils.isEmpty(taskNum.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Number", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(addTask.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Task", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(AddDescription.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Description", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(addStartDate.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Start date", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(addEndDate.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter End date", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(addEmployee.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Employee", Toast.LENGTH_SHORT).show();

                    else{
                        task.setTaskNumber(taskNum.getText().toString().trim());
                        task.setTask(addTask.getText().toString().trim());
                        task.setDescription(AddDescription.getText().toString().trim());
                        task.setStartDate(addStartDate.getText().toString().trim());
                        task.setEndDate(addEndDate.getText().toString().trim());
                        task.setEmployee(addEmployee.getText().toString().trim());

                        //databaseReference.push().setValue(task);

                        databaseReference.child(taskNum.getText().toString().trim()).setValue(task);
                        //databaseReference.push().setValue(task);

                        Toast.makeText(getApplicationContext(),"Data saved Successfully",Toast.LENGTH_SHORT).show();
                        clearControls();

                       Intent intent = new Intent(getApplicationContext(),AdminStatus.class);
                       startActivity(intent);

                    }


                }

                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Invalid Start date or End date", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}