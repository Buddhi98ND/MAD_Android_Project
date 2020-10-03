package com.example.employeemanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class myadapter extends FirebaseRecyclerAdapter<Employee , myadapter.myviewholder>
{
    private RecyclerViewClickListener listener;

    public myadapter(@NonNull FirebaseRecyclerOptions<Employee> options , RecyclerViewClickListener listener) {

        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Employee model) {
        holder.textView1.setText(model.getEmpName());
        holder.textView.setText(model.getEmpID());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single,parent,false);
        return new myviewholder(view);
    }




    class  myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView , textView1;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            textView1 = (TextView)itemView.findViewById(R.id.etname);
            textView = (TextView)itemView.findViewById(R.id.etID);

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


