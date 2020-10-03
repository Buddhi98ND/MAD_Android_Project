package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Request_List extends AppCompatActivity {

    RecyclerView recyclerView;

    Adminadapter adminadapter;
    FirebaseRecyclerOptions<Leave> options;
    Adminadapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__list);


        recyclerView = (RecyclerView)findViewById(R.id.recView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options =
                new FirebaseRecyclerOptions.Builder<Leave>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Leaving"), Leave.class)
                        .build();

        adminadapter = new Adminadapter(options,listener);
        recyclerView.setAdapter(adminadapter);

    }
    @Override
    protected void onStart(){
        super.onStart();
        adminadapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adminadapter.stopListening();
    }
}