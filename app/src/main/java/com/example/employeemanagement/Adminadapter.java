package com.example.employeemanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adminadapter extends FirebaseRecyclerAdapter<Leave , Adminadapter.myviewholder> {

    private Adminadapter.RecyclerViewClickListener listener;

    public Adminadapter(@NonNull FirebaseRecyclerOptions<Leave> options , Adminadapter.RecyclerViewClickListener listener) {

        super(options);
        this.listener = listener;
    }
    @Override
    protected void onBindViewHolder(@NonNull Adminadapter.myviewholder holder, int position, @NonNull Leave model) {
        holder.textView1.setText(model.getName());
        holder.textView.setText(model.getDepartment());
        holder.textView2.setText(model.getStartDate());
        holder.textView3.setText(model.getEndDate());
        holder.textView4.setText(model.getDescription());

    }


    @NonNull
    @Override
    public Adminadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminsinglerow,parent,false);
        return new Adminadapter.myviewholder(view);
    }

    class  myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView, textView1 , textView2 , textView3 , textView4;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.LeaveName);
            textView = (TextView) itemView.findViewById(R.id.department);
            textView2 = (TextView) itemView.findViewById(R.id.startdate);
            textView3 = (TextView) itemView.findViewById(R.id.enddate);
            textView4 = (TextView) itemView.findViewById(R.id.description);

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
