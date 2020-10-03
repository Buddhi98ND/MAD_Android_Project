package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VeiweLeaveDetails extends AppCompatActivity {


    EditText editname,editdepartment,editDescription,editStartDate,EditEndDate;
    Button edit,delete;
    DatabaseReference dbref;
    Leave leave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiwe_leave_details);

        edit =    findViewById(R.id.edit);
        delete = findViewById(R.id.delete);

        editname = findViewById(R.id.etname);
        editdepartment = findViewById(R.id.etdepartment);
        editStartDate  = findViewById(R.id.etstartdate);
        EditEndDate = findViewById(R.id.etenddate);
        editDescription = findViewById(R.id.description);


        Intent i = getIntent();
        final String name = i.getStringExtra("name");
        String department = i.getStringExtra("department");
        String startDate = i.getStringExtra("startdate");
        String endDate = i.getStringExtra("enddate");
        String Description = i.getStringExtra("description");


        editname.setText(String.valueOf(name));
        editdepartment.setText(String.valueOf(department));
        editStartDate.setText(String.valueOf(startDate));
        EditEndDate.setText(String.valueOf(endDate));
        editDescription.setText(String.valueOf(Description));


        edit = findViewById(R.id.edit);
       edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (TextUtils.isEmpty(editname.getText().toString()))
                   Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();

               else {
                   dbref = FirebaseDatabase.getInstance().getReference("Leaving/" +editname.getText().toString().trim());
                   dbref.child("/name").setValue(editname.getText().toString().trim());
                   dbref.child("/department").setValue(editdepartment.getText().toString().trim());
                   dbref.child("/startDate").setValue(editStartDate.getText().toString().trim());
                   dbref.child("/endDate").setValue(EditEndDate.getText().toString().trim());
                   dbref.child("/description").setValue(editDescription.getText().toString().trim());

                   Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG).show();

               }
           }
       });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editname.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();
                else{
                    dbref = FirebaseDatabase.getInstance().getReference("Leaving/" +editname.getText().toString().trim());
                    dbref.removeValue();
                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}