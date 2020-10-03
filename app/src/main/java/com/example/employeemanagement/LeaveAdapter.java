package com.example.employeemanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeaveAdapter extends FirebaseRecyclerAdapter<Leave , LeaveAdapter.myviewholder>
{
    private RecyclerViewClickListener listener;

    public LeaveAdapter(@NonNull FirebaseRecyclerOptions<Leave> options , RecyclerViewClickListener listener) {

        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Leave model) {
               holder.textView1.setText(model.getName());
        holder.textView.setText(model.getDepartment());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowleave,parent,false);
        return new myviewholder(view);
    }




    class  myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView , textView1;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            textView1 = (TextView)itemView.findViewById(R.id.LeaveName);
            textView = (TextView)itemView.findViewById(R.id.department);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
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

