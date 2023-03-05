package com.example.myandroidapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

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
                new int[]{R.id.lineName, R.id.lineEmail, R.id.lineUsername, R.id.linePassword})
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView editBtn = v.findViewById(R.id.textViewEditBtn);
                TextView deleteBtn = v.findViewById(R.id.textViewDeleteBtn);

                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(position);
                        HashMap<String, String> user;
                        try {
                            user = (HashMap<String, String>) list.get(position);
                            System.out.println(user);

                            Intent intent = new Intent(UserListActivity.this, RegisterActivity.class);
                            intent.putExtra("user_id", Integer.parseInt(user.get("user_id")));
                            intent.putExtra("user_name", user.get("user_name"));
                            intent.putExtra("user_email", user.get("user_email"));
                            intent.putExtra("user_username", user.get("user_username"));
                            intent.putExtra("user_password", user.get("user_password"));
                            startActivity(intent);
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(position);
                        HashMap<String, String> user;
                        try {
                            user = (HashMap<String, String>) list.get(position);
                            System.out.println("U-------------"+user.get("user_id"));
                            boolean deleted = db.deleteUser(Integer.parseInt(user.get("user_id")));
                            if (deleted){
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                            String message = deleted ? "Successfully Deleted!" : "Failed to delete!";
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                });
                return v;
            }
        };

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