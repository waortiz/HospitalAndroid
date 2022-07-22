package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hospital.databinding.ActivitySaludoBinding;

public class SaludoActivity extends AppCompatActivity {

    private ActivitySaludoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySaludoBinding.inflate(getLayoutInflater());

        setContentView(R.layout.activity_saludo);
    }
}