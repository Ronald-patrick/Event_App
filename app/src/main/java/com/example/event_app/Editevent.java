package com.example.event_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Editevent extends AppCompatActivity {
    EditText title;
    EditText org;
    EditText date;
    EditText number;
    EditText deadline;
    EditText desc;
    TextView filename;
    Button upload;
    Button btn_create;
    String t="",o="",d="",num="",del="",des="",file="";
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    FirebaseStorage storage;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mAuth;
    String keys="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editevent);
        title=findViewById(R.id.c_title);
        org=findViewById(R.id.c_org);
        date=findViewById(R.id.c_date);
        number=findViewById(R.id.c_phone);
        deadline=findViewById(R.id.c_deadline);
        desc=findViewById(R.id.c_desc);
        filename=findViewById(R.id.c_filename);
        upload=findViewById(R.id.c_upload);
        btn_create=findViewById(R.id.c_btn_create);
        keys = getIntent().getStringExtra("event");

        storage=FirebaseStorage.getInstance();
        rootnode=FirebaseDatabase.getInstance();
        reference = rootnode.getReference("Events");
        reference.child(keys).addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                t= snapshot.child("title").getValue().toString();
                d= snapshot.child("date").getValue().toString();
                o= snapshot.child("org").getValue().toString();
                des= snapshot.child("des").getValue().toString();
                num= snapshot.child("number").getValue().toString();
                del= snapshot.child("deadline").getValue().toString();
                title.setText(t);
                org.setText(o);
                date.setText(d);
                number.setText(num);
                deadline.setText(del);
                desc.setText(des);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));



        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(pdfUri!=null)
//                    uploadFile(pdfUri);
                StorageReference storageReference=storage.getReference();
                reference = rootnode.getReference("Events");

                t=title.getText().toString();
                o=org.getText().toString();
                d=date.getText().toString();
                num=number.getText().toString();
                del=deadline.getText().toString();
                des=desc.getText().toString();
                UserhelperCreate u=new UserhelperCreate(t,o,d,num,del,des);
                reference.child(t).setValue(u);
                Toast.makeText(Editevent.this,"Updated successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Editevent.this, Home_admin.class);
                startActivity(intent);
            }
        });
    }
}