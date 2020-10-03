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

public class AdminStatus extends AppCompatActivity {

    RecyclerView recView;
    MyAdapter adapter;
    MyAdapter.RecyclerViewClickListener listener;
    FirebaseRecyclerOptions<NewTask>options;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_status);
        setOnClickListener();

        FloatingActionButton add_envelope_button = findViewById(R.id.add_new_task_button);
        add_envelope_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddNewTask.class);
                startActivity(i);
            }
        });

        recView=(RecyclerView)findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        options =
                new FirebaseRecyclerOptions.Builder<NewTask>().setQuery(FirebaseDatabase.getInstance().getReference().child("NewTask"),NewTask.class).build();

        adapter=new MyAdapter(options,listener);
        recView.setAdapter(adapter);
    }

    private  void setOnClickListener() {
        listener = new MyAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), AdminTaskView.class);
                intent.putExtra("taskNumber", options.getSnapshots().get(position).getTaskNumber());
                intent.putExtra("task", options.getSnapshots().get(position).getTask());
                intent.putExtra("description", options.getSnapshots().get(position).getDescription());
                intent.putExtra("startDate", options.getSnapshots().get(position).getStartDate());
                intent.putExtra("endDate", options.getSnapshots().get(position).getEndDate());
                intent.putExtra("employee", options.getSnapshots().get(position).getEmployee());
                startActivity(intent);


            }

        };
    }




    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}