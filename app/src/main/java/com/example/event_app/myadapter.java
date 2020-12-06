package com.example.event_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {

    FirebaseRecyclerOptions options;
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
        this.options=options;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        holder.title.setText(model.getTitle());
        holder.org.setText(model.getOrg());
        holder.date.setText(model.getDate());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecard,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, date, org;
        View view;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(v.getContext(),details.class);
                    i.putExtra("title",getAdapterPosition());
                    v.getContext().startActivity(i);

                }
            });
            img = (ImageView) itemView.findViewById(R.id.card_logo);
            title = (TextView) itemView.findViewById(R.id.card_title);
            date = (TextView) itemView.findViewById(R.id.card_date);
            org = (TextView) itemView.findViewById(R.id.card_org);
            view=itemView;
        }
    }
}
