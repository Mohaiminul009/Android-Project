package com.example.myandroidapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    ArrayList list;
    SimpleAdapter sa;
    ListView lv;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Database db = new Database(getApplicationContext(), "TO-LET", null, 1);
        list = db.getUser();
        System.out.println(list.size());


        sa = new SimpleAdapter(this,
                list,
                R.layout.user_list,
                new String[]{"user_name", "user_email", "user_username", "user_password"},
                new int[]{R.id.lineName, R.id.lineEmail, R.id.lineUsername, R.id.linePassword});

        lv = findViewById(R.id.viewUserList);
        lv.setAdapter(sa);

        btn = findViewById(R.id.backHomeBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserListActivity.this, MainActivity.class));
            }
        });

    }
}