package com.example.employeemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_Dash extends AppCompatActivity {


    Button btnsalaryDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__dash);

        btnsalaryDash = findViewById(R.id.button8);
        btnsalaryDash.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),Salary_Dash.class);
                startActivity(i);
            }
        });



    }
}