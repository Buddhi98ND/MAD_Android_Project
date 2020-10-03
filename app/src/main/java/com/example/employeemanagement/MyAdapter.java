package com.example.employeemanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class MyAdapter extends FirebaseRecyclerAdapter<NewTask,MyAdapter.myviewholder> {
    private RecyclerViewClickListener listener;

    public MyAdapter(@NonNull FirebaseRecyclerOptions<NewTask> options,RecyclerViewClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull NewTask NewTask) {

        holder.tasknum.setText(NewTask.getTaskNumber());



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tasknum;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            tasknum = (TextView) itemView.findViewById(R.id.tasknum);

            itemView.setOnClickListener(this);
        }
        @Override

        public void onClick(View view){
            listener.onClick(view, getAdapterPosition());
            }

        }
        @Override

        public int getItemCount() {
            return super.getItemCount();
        }
        public interface RecyclerViewClickListener{
            void onClick(View v,int position);
        }
}


