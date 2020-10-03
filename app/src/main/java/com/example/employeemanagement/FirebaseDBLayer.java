package com.example.employeemanagement;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDBLayer {
    private static FirebaseDBLayer instance;
    private DatabaseReference firebaseDatabase;

    private Map<String, Salary> db;

    private FirebaseDBLayer(){
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Salary");

        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                db = new HashMap<>();
                synchronized (this) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        System.out.println(snapshot);
                        db.put(snapshot.getKey(), snapshot.getValue(Salary.class));
                    }
                    this.notify();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        firebaseDatabase.addValueEventListener(valueEventListener);
    }

    public Map<String, Salary> getByEmpId(String id){
        Map<String, Salary> ret = null;
        for (String key : db.keySet()){
            Salary salary = db.get(key);
            if (salary.getID().trim().toLowerCase().equals(id.trim().toLowerCase())){
                ret = new HashMap<>();
                ret.put(key, salary);
                break;
            }
        }
        return ret;
    }

    public boolean isEmployeeExists(String id){
        return null != getByEmpId(id);
    }


    public static FirebaseDBLayer getInstance(){
        if (null == instance){
            instance = new FirebaseDBLayer();
        }
        return instance;
    }


}
