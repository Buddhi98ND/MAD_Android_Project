package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmpDetails extends AppCompatActivity {

    EditText Ename, EID, Edep, emailE, Ephone;
    Button save;
    DatabaseReference reference;
    Employee employee;
    FirebaseDatabase rootNode;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);
        Ename=(EditText)findViewById(R.id.etname);
        EID = (EditText)findViewById(R.id.etID);
        Edep = (EditText)findViewById(R.id.etdep);
        emailE = (EditText)findViewById(R.id.etemail);
        Ephone = (EditText)findViewById(R.id.etphone);
        save = (Button) findViewById(R.id.save);
        employee = new Employee();
//        reference = FirebaseDatabase.getInstance().getReference().child("Employee");
       save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Employee");

                //get all values
                String name = Ename.getText().toString();
                String id = EID.getText().toString();
                String department = Edep.getText().toString();
                String email = emailE.getText().toString();
                String phone = Ephone.getText().toString();

                Employee employee = new Employee(name , id , department , email, phone);

                reference.child(name).setValue(employee);
                Toast.makeText(EmpDetails.this , "data inserted successfully",Toast.LENGTH_SHORT ).show();
//                Intent i = new Intent(getApplicationContext() , Employee_list.class);
//                startActivity(i);
//                employee.setEmpName(empName.getText().toString().trim());
//                employee.setEmpID(empID.getText().toString().trim());
//                employee.setDepartment(department.getText().toString().trim());
//                employee.setEmail(email.getText().toString().trim());
//                employee.setPhone(phone.getText().toString().trim());
//                dbRef.child("Employee1").setValue(employee);
//                Toast.makeText(EmpDetails.this , "data inserted successfully",Toast.LENGTH_SHORT ).show();
            }
        });
    }

}