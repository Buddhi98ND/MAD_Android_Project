package com.example.employeemanagement;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Map;

public class IDDialog extends Dialog {

    private EditText txtID;
    private Button cmdSHowSal;

    public IDDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_id_entry);

        txtID = findViewById(R.id.txtID);
        cmdSHowSal = findViewById(R.id.cmdSHowSal);

        cmdSHowSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtID.getText())){
                    Toast.makeText(getContext(), "Please enter the employee ID", Toast.LENGTH_LONG).show();
                }else{
                    Map<String, Salary> val = FirebaseDBLayer.getInstance().getByEmpId(txtID.getText().toString().trim());
                    if (val == null){
                        Toast.makeText(getContext(), "Employee does not exist", Toast.LENGTH_LONG).show();
                    }else{
                        Intent intent = new Intent(getContext(), addSalary.class);
                        intent.putExtra("mode", "edit");
                        intent.putExtra("emp", (Serializable) val);
                        getContext().startActivity(intent);
                        dismiss();
                    }
                }
            }
        });
    }


}
