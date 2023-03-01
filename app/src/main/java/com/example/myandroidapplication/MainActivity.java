package com.example.myandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String uName = sp.getString("username", "").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+uName+"!", Toast.LENGTH_SHORT).show();
    }
}