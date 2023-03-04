package com.example.myandroidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView cUserList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String uName = sp.getString("username", "").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+uName+"!", Toast.LENGTH_SHORT).show();

        cUserList = findViewById(R.id.cardUserList);
        cUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
            }
        });
    }
}