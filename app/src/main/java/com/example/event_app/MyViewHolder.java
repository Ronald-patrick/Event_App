package com.example.event_app;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView title, date, org;
    View view;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.card_logo);
        title = (TextView) itemView.findViewById(R.id.card_title);
        date = (TextView) itemView.findViewById(R.id.card_date);
        org = (TextView) itemView.findViewById(R.id.card_org);
        view=itemView;
    }

}
