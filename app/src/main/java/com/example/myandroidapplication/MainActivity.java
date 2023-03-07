package com.example.myandroidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
//import androidx.cardview.widget.CardView;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//import android.view.View;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView cUserList, cProfile, cLogout;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String uName = sp.getString("username", "").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+uName+"!", Toast.LENGTH_SHORT).show();

        cUserList = findViewById(R.id.cardUserList);
        cProfile = findViewById(R.id.cardProfile);
        cUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
            }
        });

        cProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        cLogout = findViewById(R.id.cardLogout);
        builder = new AlertDialog.Builder(this);

        cLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);
                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Action Dialog");
                alert.show();
            }
        });
    }
}