package com.example.event_app;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    Context context;
    private String name;
    private String admin;
    SharedPreferences shrd;

    public void remove()
    {
        shrd.edit().clear().commit();
    }
    public String getName()
    {
       name=shrd.getString("userdata","");
       return name;

    }
    public String getAdmin()
    {
        admin=shrd.getString("admin","");
        return admin;
    }

    public void setName(String name)
    {
        this.name=name;
        shrd.edit().putString("userdata",name).commit();
    }

    public void setAdmin(String admin)
    {
        this.admin=admin;
        shrd.edit().putString("admin",admin).commit();
    }

    public User(Context context)
    {
        this.context=context;
        shrd=context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
    }
}
