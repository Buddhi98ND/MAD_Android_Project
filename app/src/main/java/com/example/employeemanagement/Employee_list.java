package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Employee_list extends AppCompatActivity {


    RecyclerView recyclerView;

    myadapter MyAdapter;
    FirebaseRecyclerOptions<Employee>options;
    myadapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

      setOnClickListener();

        recyclerView = (RecyclerView)findViewById(R.id.review);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options =
                new FirebaseRecyclerOptions.Builder<Employee>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Employee"), Employee.class)
                        .build();

        MyAdapter = new myadapter(options,listener);
        recyclerView.setAdapter(MyAdapter);




    }


    private  void  setOnClickListener(){

        listener = new myadapter.RecyclerViewClickListener(){

            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getApplicationContext(),EmpDelete.class);

                intent.putExtra("name", options.getSnapshots().get(position).getEmpName());
                intent.putExtra("department", options.getSnapshots().get(position).getDepartment());
                intent.putExtra("email", options.getSnapshots().get(position).getEmail());
                intent.putExtra("empID", options.getSnapshots().get(position).getEmpID());
                intent.putExtra("phone", options.getSnapshots().get(position).getPhone());

                startActivity(intent);


            }
        };
    }
    @Override
    protected void onStart(){
        super.onStart();
        MyAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        MyAdapter.stopListening();
    }

}