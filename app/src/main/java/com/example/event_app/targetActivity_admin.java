package com.example.event_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class targetActivity_admin extends AppCompatActivity {

    TextView titleview, dateview, orgview;
    Button reg_btn,edit_btn;
    DatabaseReference ref;
    DatabaseReference ref2;
    String title;
    ListView listview;
    ArrayList<String> list = new ArrayList<>();
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_admin);
        titleview = findViewById(R.id.click_title);
        dateview = findViewById(R.id.click_date);
        orgview = findViewById(R.id.click_org);
        listview=(ListView)findViewById(R.id.listofpart);
        final User user = new User(targetActivity_admin.this);
        final String curruser = user.getName();
        key = getIntent().getStringExtra("key");

        reg_btn = findViewById(R.id.event_reg_btn);
        edit_btn=findViewById(R.id.edit_admin);

        ref = FirebaseDatabase.getInstance().getReference().child("Events");

        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                title = snapshot.child("title").getValue().toString();
                String org = snapshot.child("org").getValue().toString();
                String date = snapshot.child("date").getValue().toString();
                titleview.setText(title);
                dateview.setText(date);
                orgview.setText(org);


                ref2 = FirebaseDatabase.getInstance().getReference().child("Registered").child(title);
                ref2.addValueEventListener(new ValueEventListener() {
                    int flag = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String roll = ds.child("Roll").getValue(String.class);
                            list.add(roll);
                        }
                        Log.d("TargetActivity","List- "+list);
//                        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(targetActivity_admin.this, android.R.layout.simple_list_item_1,list);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (targetActivity_admin.this, android.R.layout.simple_list_item_1, list){
                            @Override
                            public View getView(int position, View convertView, ViewGroup parent){
                                View view = super.getView(position, convertView, parent);
                                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                                tv.setTextColor(Color.WHITE);
                                return view;
                            }
                        };
                        listview.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Editevent.class);
                intent.putExtra("event",key);
                startActivity(intent);
            }
        });


    }
}