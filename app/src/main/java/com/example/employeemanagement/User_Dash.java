package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_Dash extends AppCompatActivity {

    Button leavebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__dash);

        leavebutton = findViewById(R.id.leavebtn);

        leavebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Manage_Leave.class);
                startActivity(i);
            }
        });
    }
}