package com.example.event_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final User user=new User(SplashActivity.this);
        Log.d("tag", "Value of admin: " + user.getAdmin());
        Timer timer=new Timer();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(user.getAdmin().equals("true"))
                {
                    Intent intent= new Intent(SplashActivity.this,Home_admin.class);
                    intent.putExtra("name",user.getName());
                    startActivity(intent);
                    finish();
                }
                else if(user.getAdmin().equals("false"))
                {
                    Intent intent= new Intent(SplashActivity.this,HomeScreen.class);
                    intent.putExtra("name",user.getName());
                    startActivity(intent);
                    finish();
                }

                else
                {
                    Intent intent= new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);


    }
}