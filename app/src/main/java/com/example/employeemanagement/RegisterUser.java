package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressbar;
    EditText EmpEmail , Pw1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        EmpEmail = (EditText) findViewById(R.id.EmpEmail);
        Pw1 = (EditText) findViewById(R.id.Pw1);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.btn1).setOnClickListener(this);
    }

    private  void resisterUser(){

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

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                   progressbar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(RegisterUser.this, User_Dash.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext() , "You are already registered" , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext() , task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                resisterUser();
            break;

            case R.id.textViewLogin:
       startActivity(new Intent(this, MainActivity.class));
        break;
        }
    }
}