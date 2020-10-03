package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Admin_Login extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button button12;
    private int counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login);

        Name= (EditText)findViewById(R.id.Name);
        Password = (EditText)findViewById(R.id.Password);
        button12 = (Button) findViewById(R.id.button12);

        button12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate(String Name , String Password){
        if((Name == "Admin" ) && (Password == "1234")){
            Intent intent = new Intent(Admin_Login.this, Admin_Dash.class);
            startActivity(intent);
        }else {
           counter--;

           if(counter == 0){
               button12.setEnabled(false);
           }
        }
    }
}