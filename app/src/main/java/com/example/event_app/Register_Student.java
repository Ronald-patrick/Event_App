package com.example.event_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Student extends AppCompatActivity {
    EditText emailId,password1,rollno,password2,names;
    Button btn_reg;
    FirebaseAuth mAuth;
    String email="",pass="",pass2="",roll="",name="";
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__student);
        emailId = findViewById(R.id.email_reg);
        password1= findViewById(R.id.pass);
        btn_reg = findViewById(R.id.btn_reg);
        names=findViewById(R.id.name);
        rollno=findViewById(R.id.roll);
        password2=findViewById(R.id.pass2);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode=FirebaseDatabase.getInstance();
                reference = rootnode.getReference("Users");

                email=emailId.getText().toString();
                pass=password1.getText().toString();
                pass2=password2.getText().toString();
                roll=rollno.getText().toString();
                name=names.getText().toString();
                Userhelper helper=new Userhelper(name,roll,email,pass,pass2);
                reference.child(roll).setValue(helper);
                Toast.makeText(Register_Student.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register_Student.this, Login_admin.class);
                startActivity(intent);

            }
        });
    }
    public void changeActReg_admin(View view) {
        Intent intent = new Intent(Register_Student.this, Login_admin.class);
        startActivity(intent);
    }
    public void changeActReg_student(View view) {
        Intent intent = new Intent(Register_Student.this, MainActivity.class);
        startActivity(intent);
    }
}