package com.example.event_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login_admin extends AppCompatActivity {
    EditText emailId,password;
    Button btn_login;
    private FirebaseAuth mAuth;
    String email="",pass="";
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        mAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email_login);
        password = findViewById(R.id.pass_login);
        btn_login = findViewById(R.id.login_btn);
        progressBar=findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

    }
    private Boolean validateUsername()
    {
        String val=emailId.getText().toString();
        if(val.isEmpty()) {
            emailId.setError("Field cannot be empty");
            return false;
        }
        else{
            emailId.setError(null);
            return true;
        }
    }
    private Boolean validatepassword()
    {
        String val=password.getText().toString();
        if(val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }
    public void onclickadmin(View view)
    {
        if(!validateUsername()|!validatepassword())
            return;
        else{
            isUser();
        }
    }

    private void isUser()
    {
        progressBar.setVisibility(View.VISIBLE);
        email = emailId.getText().toString().trim();
        pass = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admins");
        Query checkUser = reference.orderByChild("user").equalTo(email);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String passwordDB = snapshot.child(email).child("pass").getValue(String.class);
                    if (passwordDB.equals(pass))
                    {
                        User user= new User(Login_admin.this);
                        user.setName(email);
                        user.setAdmin("true");
                        Toast.makeText(Login_admin.this,"Login Success",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(Login_admin.this, Home_admin.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(Login_admin.this,"Login failed",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void changeActStudent(View view) {
        Intent intent = new Intent(Login_admin.this, MainActivity.class);
        startActivity(intent);
    }
}


