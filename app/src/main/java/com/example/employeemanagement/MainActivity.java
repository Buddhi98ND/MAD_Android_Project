package com.example.employeemanagement;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressBar progressbar;
    Button button2;
    TextView TextView;
    EditText EmpEmail , Pw1;

    FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Salary");
        FirebaseDBLayer.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        EmpEmail = (EditText) findViewById(R.id.EmpEmail);
        Pw1 = (EditText) findViewById(R.id.Pw1);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        findViewById(R.id.button1).setOnClickListener(this);


        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),Admin_Login.class);
                startActivity(i);
            }
        });

        TextView = findViewById(R.id.textView);
        TextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),RegisterUser.class);
                startActivity(i);
            }
        });

    }

    private void userLogin(){

        String email = EmpEmail.getText().toString().trim();
        String password = Pw1.getText().toString().trim();

        if(email.isEmpty()){
            EmpEmail.setError("Email is required");
            EmpEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EmpEmail.setError("Please enter correct email");
            EmpEmail.requestFocus();
            return;
        }

        if(password.length()<6){
            Pw1.setError("Minimum Length of password should be 6");
            Pw1.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Pw1.setError("Password is required");
            Pw1.requestFocus();
            return;
        }

        progressbar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                   Intent intent = new Intent(MainActivity.this, User_Dash.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                userLogin();

            break;
        }

    }
}