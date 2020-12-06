package com.example.event_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class details extends AppCompatActivity {
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title=findViewById(R.id.details_title);
        Intent i=getIntent();
        String titlemain=i.getStringExtra("title");
        title.setText(titlemain);
    }
}