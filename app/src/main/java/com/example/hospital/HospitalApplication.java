package com.example.hospital;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.hospital.actividades.ui.login.LoginActivity;

public class HospitalApplication extends Application {
    private static HospitalApplication instance;
    public static HospitalApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        instance = this;

        SharedPreferences preferences = getSharedPreferences("Preferencia", MODE_PRIVATE);
        if(preferences.getBoolean("login", false))
        {
            //Creamos el Intent
            Intent intent =
                    new Intent(this, LinearLayoutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Iniciamos la nueva actividad
            startActivity(intent);
        }
        else
        {
            //Creamos el Intent
            Intent intent =
                    new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Iniciamos la nueva actividad
            startActivity(intent);
        }
    }
}
