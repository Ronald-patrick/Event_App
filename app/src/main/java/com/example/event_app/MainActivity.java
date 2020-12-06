package com.example.event_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText emailId,password;
    Button btn_login;
    private FirebaseAuth mAuth;
    String email="8897",pass="";
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email_login);
        password = findViewById(R.id.pass_login);
        btn_login = findViewById(R.id.login_btn);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


    }
//    private void authenticate()
//    {
//        mAuth.signInWithEmailAndPassword(email, pass)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            progressBar.setVisibility(View.GONE);
//                            startActivity(new Intent(MainActivity.this,HomeScreen.class));
//                        } else {
//
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//
//                        }
//
//                        // ...
//                    }
//                });
//    }

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
    public void onclicklogin(View view)
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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("roll").equalTo(email);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {

                    String passwordDB = snapshot.child(email).child("password").getValue(String.class);
                    if (passwordDB.equals(pass))
                    {
                        User user= new User(MainActivity.this);
                        user.setName(email);
                        user.setAdmin("false");
                        Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void changeActReg(View view) {
        Intent intent = new Intent(MainActivity.this, Register_Student.class);
        startActivity(intent);
    }
    public void changeActAdmin(View view) {
        Intent intent = new Intent(MainActivity.this, Login_admin.class);
        startActivity(intent);
    }
    public void onBackPressed() {
    }

}