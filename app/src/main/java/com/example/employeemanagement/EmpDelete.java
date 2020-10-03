package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmpDelete extends AppCompatActivity {

    EditText editName, editId, editdep, editemail, editphone;
    Button delete;
    DatabaseReference reference;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_delete);

        delete = findViewById(R.id.delete);


        editName = findViewById(R.id.etname);
        editId = findViewById(R.id.etID);
        editdep = findViewById(R.id.etdep);
        editemail = findViewById(R.id.etemail);
        editphone = findViewById(R.id.etphone);

        Intent i = getIntent();
        final String empName = i.getStringExtra("name");
        String department = i.getStringExtra("department");
        String email = i.getStringExtra("email");
        String empID = i.getStringExtra("empID");
        String phone = i.getStringExtra("phone");

        editName.setText(String.valueOf(empName));
        editId.setText(String.valueOf(empID));
        editemail.setText(String.valueOf(email));
        editphone.setText(String.valueOf(phone));
        editdep.setText(String.valueOf(department));

      delete.setOnClickListener(new View.OnClickListener() {
          @Override
         public void onClick(View view) {
                if(TextUtils.isEmpty(editName.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Check your Name" , Toast.LENGTH_SHORT).show();
                else {
                    reference = FirebaseDatabase.getInstance().getReference("Employee/" +editName.getText().toString().trim() );
                    reference.removeValue();
                    Toast.makeText(getApplicationContext(),"Deleted" , Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}