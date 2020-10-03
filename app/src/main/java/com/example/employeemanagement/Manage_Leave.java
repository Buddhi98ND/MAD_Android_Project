package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Manage_Leave extends AppCompatActivity {


    RecyclerView recyclerView;

    LeaveAdapter leaveAdapter;
    FirebaseRecyclerOptions<Leave>options;
    LeaveAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__leave);

        setOnClickListner();

        recyclerView = (RecyclerView)findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options =
                new FirebaseRecyclerOptions.Builder<Leave>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Leaving"), Leave.class)
                .build();

        leaveAdapter = new LeaveAdapter(options,listener);
        recyclerView.setAdapter(leaveAdapter);



        FloatingActionButton add_envelope_button = findViewById(R.id.add_leave);
        add_envelope_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Request_Leave.class);
                startActivity(i);
            }
        });



  }


  private  void  setOnClickListner(){

      listener = new LeaveAdapter.RecyclerViewClickListener(){

            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getApplicationContext(), VeiweLeaveDetails.class);

                intent.putExtra("name", options.getSnapshots().get(position).getName());
                intent.putExtra("department", options.getSnapshots().get(position).getDepartment());
                intent.putExtra("startdate", options.getSnapshots().get(position).getStartDate());
                intent.putExtra("enddate", options.getSnapshots().get(position).getEndDate());
                intent.putExtra("description", options.getSnapshots().get(position).getDescription());

                startActivity(intent);


            }
        };
  }
  @Override
    protected void onStart(){
        super.onStart();
        leaveAdapter.startListening();
  }

  @Override
    protected void onStop(){
        super.onStop();
        leaveAdapter.stopListening();
  }

}