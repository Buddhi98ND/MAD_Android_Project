package com.example.employeemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Salary_Dash extends AppCompatActivity {


    private Button btnAdd, btnBACK, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary__dash);


        btnAdd = findViewById(R.id.addSalary);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),addSalary.class);
                startActivity(i);
            }
        });

        btnSearch = findViewById(R.id.searchSalary);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IDDialog(Salary_Dash.this).show();
            }
        });

        btnBACK = findViewById(R.id.btnBACK);
        btnBACK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });



    }
}