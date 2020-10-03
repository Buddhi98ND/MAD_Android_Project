package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Request_Leave extends AppCompatActivity {

    EditText txtName , txtDepartment;
    EditText etStartDate , etEndDate , etDiscription;

    Button submit;
    DatabaseReference reference;
    FirebaseDatabase rootNode;

    DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__leave);

        submit = findViewById(R.id.submit);
        txtName = findViewById(R.id.txtName);
        txtDepartment = findViewById(R.id.txtDepartment);
        etStartDate = findViewById(R.id.et_StartDate);
        etEndDate = findViewById(R.id.et_endDate);
        etDiscription = findViewById(R.id.etReasonDet);



        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);

        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Request_Leave.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" +month+ "/" +year;
                        etStartDate.setText(date);
                    }
                }, year , month , day);
                datePickerDialog.show();
            }
        });

        etEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Request_Leave.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" +month+ "/" +year;
                        etEndDate.setText(date);
                    }
                }, year , month , day);
                datePickerDialog.show();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Leaving");

                //get all values
                String name = txtName.getText().toString();
                String department = txtDepartment.getText().toString();
                String startDate = etStartDate.getText().toString();
                String endDate =  etEndDate.getText().toString();
                String Description = etDiscription.getText().toString();


                Leave leave = new Leave(name, department,startDate,endDate,Description );


                reference.child(name).setValue(leave);

                Intent i = new Intent(getApplicationContext(),Manage_Leave.class);
                startActivity(i);


            }
        });

    }


    }

