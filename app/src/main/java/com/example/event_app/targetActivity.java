package com.example.event_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class targetActivity extends AppCompatActivity {

    TextView titleview,dateview,orgview,descview,delview,phoneview;
    Button reg_btn;
    DatabaseReference ref;
    DatabaseReference ref2;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        titleview=findViewById(R.id.click_title);
        dateview=findViewById(R.id.click_date);
        orgview=findViewById(R.id.click_org);
        descview=findViewById(R.id.click_desc);
        delview=findViewById(R.id.click_del);
        phoneview=findViewById(R.id.click_phone);
        final User user=new User(targetActivity.this);
        final String curruser=user.getName();
        reg_btn=findViewById(R.id.event_reg_btn);
        String key=getIntent().getStringExtra("key");
        ref= FirebaseDatabase.getInstance().getReference().child("Events");

        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                title = snapshot.child("title").getValue().toString();
                String org = snapshot.child("org").getValue().toString();
                String date = snapshot.child("date").getValue().toString();
                String desc=snapshot.child("des").getValue().toString();
                String phone=snapshot.child("number").getValue().toString();
                String deadline=snapshot.child("deadline").getValue().toString();
                titleview.setText(title);
                dateview.setText(date);
                orgview.setText(org);
                descview.setText(desc);
                delview.setText(deadline);
                phoneview.setText(phone);

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
                        if(list.contains(curruser))
                        {
                            reg_btn.setText("Already Registered");
                        }
                        else
                            reg_btn.setEnabled(true);
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
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int seconds = c.get(Calendar.SECOND);
                int minutes = c.get(Calendar.MINUTE);
                int hour = c.get(Calendar.HOUR);
                String time = hour + ":" + minutes + ":" + seconds;


                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                String date = day + "/" + month + "/" + year;

                ref2.child(curruser).child("Roll").setValue(curruser);
                ref2.child(curruser).child("Time").setValue(time);
                ref2.child(curruser).child("Date").setValue(date);
                Toast.makeText(targetActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                reg_btn.setEnabled(false);
                reg_btn.setText("Already Registered");
            }
        });
    }

}