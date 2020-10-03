package com.example.employeemanagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addSalary extends AppCompatActivity {

    private EditText txtID, txtJobTitle, txtBasicSalary, txtOtRate, txtDeduction;
    private Button btnAdd, btnCANCEL1, cmdDelButton;
    private DatabaseReference danRef;
    private Salary sal;
    private String mode;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_salary);

        txtID = findViewById(R.id.txtID);
        txtJobTitle = findViewById(R.id.txtJobTitle);
        txtBasicSalary = findViewById(R.id.txtBasicSalary);
        txtOtRate = findViewById(R.id.txtOtRate);
        txtDeduction = findViewById(R.id.txtDeduction);

        btnAdd = findViewById(R.id.btnAdd);
        btnCANCEL1 = findViewById(R.id.btnCANCEL1);
        cmdDelButton = findViewById(R.id.cmdDelete);

        mode = getIntent().getStringExtra("mode");

        if (null == mode || mode.trim().equals("")) {
            sal = new Salary();
            cmdDelButton.setVisibility(View.INVISIBLE);
        }else{
            Map<String, Salary> rcv = (Map<String, Salary>) getIntent().getSerializableExtra("emp");
            for (String ky : rcv.keySet()){
                key = ky;
                break;
            }
            sal = rcv.get(key);
            btnAdd.setText("Update");
            fillControls();

            cmdDelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(addSalary.this);
                    builder.setMessage("Are you sure you want to delete this record ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            danRef = FirebaseDatabase.getInstance().getReference().child("Salary");
                            danRef.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    finish();
                                }
                            });

                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                }
            });
        }


    }

    private void fillControls() {
        txtID.setText(sal.getID());
        txtJobTitle.setText(sal.getJobTitle());
        txtBasicSalary.setText(""+sal.getBasicSalary());
        txtOtRate.setText(""+sal.getOtRate());
        txtDeduction.setText(""+sal.getDeduction());
    }


    protected void onResume(){
        super.onResume();


        btnCANCEL1 = findViewById(R.id.btnCANCEL1);
        btnCANCEL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        danRef = FirebaseDatabase.getInstance().getReference().child("Salary");


                        try {
                            if (TextUtils.isEmpty(txtID.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter ID", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(txtJobTitle.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter Job Title", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(txtBasicSalary.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter Basic Salary", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(txtOtRate.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter OT Rate", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(txtDeduction.getText().toString()))
                                Toast.makeText(getApplicationContext(), "Please Enter Deductions", Toast.LENGTH_SHORT).show();
                            else {
                                if (null == mode || mode.trim().equals("")) {
                                    sal.setID(txtID.getText().toString().trim());
                                }
                                sal.setJobTitle(txtJobTitle.getText().toString().trim());
                                sal.setBasicSalary(Double.parseDouble(txtBasicSalary.getText().toString().trim()));
                                sal.setOtRate(Double.parseDouble(txtOtRate.getText().toString().trim()));
                                sal.setDeduction(Double.parseDouble(txtDeduction.getText().toString().trim()));

                                if (null == mode || mode.trim().equals("")) {
                                    if (FirebaseDBLayer.getInstance().isEmployeeExists(txtID.getText().toString().trim())){
                                        Toast.makeText(getApplicationContext(), "Employee already exists", Toast.LENGTH_SHORT).show();
                                    }else {
                                        danRef.push().setValue(sal);
                                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                                        clearControls();
                                    }
                                }else{
                                    Map<String, Object> u = new HashMap<>();
                                    u.put(key, sal);
                                    danRef.updateChildren(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(addSalary.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }




                            }
                        } catch (NumberFormatException e) {
                        }



                    }
                }
        );
    }


    private void clearControls(){
        txtID.setText("");
        txtJobTitle.setText("");
        txtBasicSalary.setText("");
        txtOtRate.setText("");
        txtDeduction.setText("");
    }

}